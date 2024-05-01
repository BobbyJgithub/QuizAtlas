package com.example.qa.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qa.models.Country;
import com.example.qa.models.Player;
import com.example.qa.R;
import com.example.qa.models.ListCountries;

/**
 * Activity for the Language Guesser game mode
 * It retrieves a random language from the ListCountries class and displays clues about the language.
 * The user can input their guess in the EditText box and submit it by pressing the enter key.
 * The user can also listen to an audio clip of the language by pressing the play button.
 */
public class LanguageGuesserActivity extends AppCompatActivity {

    /**
     * The player instance.
     * Used to store player data.
     */
    private Player player = Player.getInstance();

    /**
     * Boolean to keep track of whether the audio is playing.
     */
    private boolean isPlaying = false;

    /**
     * The MediaPlayer instance.
     * Used to play audio clips.
     */
    private MediaPlayer mediaPlayer;

    /**
     * The progress bar that shows the progress of the audio clip.
     */
    private ProgressBar progressBar;

    /**
     * Retrieves a random language from the ListCountries class.
     * Sets up the Language Guesser activity and its buttons.
     * Initializes the game with this game modes unique clue: language sample.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LanguageGuesserActivity", "Activity entered");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_language_game);

        // Get a random country from the ListCountries class
        Country currentCountry = ListCountries.getRandomCountry();
        // Get the language of the current country
        String currentLanguage = currentCountry.getLanguage();
        Log.d("LanguageGuesserActivity", "Current language: " + currentLanguage);

        // Add the current language to the player's list of discovered languages
        if (!player.getLanguagesDiscovered().contains(currentLanguage)) {
            player.getLanguagesDiscovered().add(currentLanguage);
        }

        // Initialize buttons and views
        ImageView help_menu_button = findViewById(R.id.help_menu_button);
        ImageView achievements_button = findViewById(R.id.achievements_button);
        progressBar = findViewById(R.id.audio_progress_bar);
        ImageView play_button = findViewById(R.id.play_button);
        Button transcript_clue_button = findViewById(R.id.transcript_clue);
        TextView transcript_clue_text = findViewById(R.id.transcript_clue_text);
        Button historical_clue_button = findViewById(R.id.historical_clue);
        TextView historical_clue_text = findViewById(R.id.historical_clue_text);
        EditText guess_box = findViewById(R.id.guess_box);
        ImageView home_button = findViewById(R.id.home_button);
        ImageView country_guesser_button = findViewById(R.id.country_guesser_button);
        ImageView flag_guesser_button = findViewById(R.id.flag_guesser_button);
        ImageView language_guesser_button = findViewById(R.id.language_guesser_button);

        // Sends the user to the HelpMenuActivity when the help_menu_button is clicked
        help_menu_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageGuesserActivity.this, HelpMenuActivity.class);
            startActivity(intent);
        });

        // Sends the user to the AchievementsActivity when the achievements_button is clicked
        achievements_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageGuesserActivity.this, AchievementsActivity.class);
            startActivity(intent);
        });

        // Sets header text to darker color, indicating that the clue has been revealed. Calls revealClue method to display the clue.
        transcript_clue_button.setOnClickListener(v -> {
            transcript_clue_button.setTextColor(getResources().getColor(R.color.primary_grey, null));
            revealClue("transcript", currentCountry, transcript_clue_text);
        });

        // Sets header text to darker color, indicating that the clue has been revealed. Calls revealClue method to display the clue.
        historical_clue_button.setOnClickListener(v -> {
            historical_clue_button.setTextColor(getResources().getColor(R.color.primary_grey, null));
            revealClue("historical", currentCountry, historical_clue_text);
        });

        // Set an OnEditorActionListener for the guess_box to check the user's guess when the enter key is pressed
        guess_box.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                checkGuess(guess_box.getText().toString(), currentCountry.getLanguage(), currentCountry.getDescription(), currentCountry.getFlag());
                return true;
            }
            return false;
        });

        // Sends the user to the MainActivity when the home_button is clicked
        home_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageGuesserActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Sends the user to the CountryGuesserActivity when the country_guesser_button is clicked
        country_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageGuesserActivity.this, CountryGuesserActivity.class);
            startActivity(intent);
        });

        // Sends the user to the FlagGuesserActivity when the flag_guesser_button is clicked
        flag_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageGuesserActivity.this, FlagGuesserActivity.class);
            startActivity(intent);
        });

        // Plays the audio clip of the language when the play button is clicked
        play_button.setOnClickListener(v -> {
            // Retrieve the audio file name from the current country
            String audioFile = currentCountry.getLanguage();
            // Get the resource ID of the audio file
            int audioResourceId = getResources().getIdentifier(audioFile, "raw", getPackageName());
            // When the button is clicked...
            if (!isPlaying) {
                // start playback if the audio is not already playing
                startPlayback(audioResourceId, play_button);
            } else {
                // pause playback if the audio is already playing
                pausePlayback(play_button);
            }
        });

    }

    /**
     * Displays the clue of the given type for the current country.
     *
     * @param clueType       The type of clue to reveal.
     * @param currentCountry The current country.
     * @param clueText       The TextView to display the clue.
     */
    private void revealClue(String clueType, Country currentCountry, TextView clueText) {
        if (clueType.equals("culture")) {
            clueText.setText(currentCountry.getCulturalInfo());
        } else if (clueType.equals("historical")) {
            clueText.setText(currentCountry.getHistoricalInfo());
        } else if (clueType.equals("transcript")) {
            clueText.setText(currentCountry.getTranscript());
        }
    }

    /**
     * Checks the user's guess against the correct answer.
     * Displays a toast message indicating if the guess was correct or incorrect.
     * If the guess was correct, the user is sent to the CorrectGuessActivity.
     *
     * @param guess         The user's guess.
     * @param correctAnswer The correct answer.
     * @param description   The description of the current country.
     * @param flag          The flag of the current country.
     */
    private void checkGuess(String guess, String correctAnswer, String description, String flag) {
        correctAnswer = correctAnswer.toLowerCase();
        guess = guess.toLowerCase();
        if (guess.equals(correctAnswer)) {
            Log.d("CountryGuesserActivity", "Current country is correct");
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();

            // Update the player data when the guess is correct
            player.setCorrectGuesses(player.getCorrectGuesses() + 1);

            // Send the user to the CorrectGuessActivity with the correct answer, description, flag, and game mode info to be displayed
            Intent intent = new Intent(LanguageGuesserActivity.this, CorrectGuessActivity.class);
            intent.putExtra("correctAnswer", correctAnswer);
            intent.putExtra("description", description);
            intent.putExtra("flag", flag);
            intent.putExtra("gameMode", "language");
            startActivity(intent);

        } else {
            Log.d("CountryGuesserActivity", "Current country is incorrect");
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();

            // Update the player data when the guess is incorrect
            player.setIncorrectGuesses(player.getIncorrectGuesses() + 1);
        }
    }

    /**
     * Handler is used to schedule and execute tasks either immediately or at some future time.
     * In this case, it's used to schedule a Runnable task that updates the progress bar every 10 milliseconds.
     * This creates a loop that continues as long as the audio is playing.
     */
    private Handler handler = new Handler();

    /**
     * Runnable task that updates the progress bar every 10 milliseconds.
     */
    private Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            // If the MediaPlayer is not null and is playing
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                // Calculate the current progress as a percentage of the total duration
                // and update the progress bar
                progressBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
                // Schedule the Runnable to run again after 10 milliseconds
                handler.postDelayed(this, 10);
            }
        }
    };

    /**
     * Method to start audio playback.
     * It creates a new MediaPlayer, sets a listener that will be called when the audio finishes playing,
     * sets isPlaying to true, changes the play button image to the pause icon, starts the MediaPlayer,
     * and starts the progress update loop.
     *
     * @param audioResourceId The resource ID of the audio file to play.
     * @param play_button     The play button ImageView.
     */
    private void startPlayback(int audioResourceId, ImageView play_button) {
        try {
            // If the MediaPlayer is null
            if (mediaPlayer == null) {
                // Create a new MediaPlayer and set a listener that will be called when the audio finishes playing
                mediaPlayer = MediaPlayer.create(this, audioResourceId);
                mediaPlayer.setOnCompletionListener(mp -> {
                    // When the audio finishes playing, set isPlaying to false, change the play button image to the play icon,
                    // release the MediaPlayer resources, and set mediaPlayer to null
                    isPlaying = false;
                    play_button.setImageResource(R.drawable.language_play);
                    mediaPlayer.release();
                    mediaPlayer = null;
                });
            }
            // Set isPlaying to true, change the play button image to the pause icon, start the MediaPlayer,
            // and start the progress update loop
            isPlaying = true;
            play_button.setImageResource(R.drawable.language_pause);
            mediaPlayer.start();

            // Start the progress update loop to update the progress bar every 10 milliseconds
            handler.postDelayed(updateProgress, 10);
        } catch (Exception e) {
            Log.e("LanguageGuesserActivity", "Error playing audio: " + e.getMessage());
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to pause audio playback.
     * It sets isPlaying to false, changes the play button image to the play icon, and pauses the MediaPlayer.
     * It also stops the progress update loop.
     *
     * @param play_button The play button ImageView.
     */
    private void pausePlayback(ImageView play_button) {
        // If the MediaPlayer is not null and is playing
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            // Set isPlaying to false, change the play button image to the play icon, and pause the MediaPlayer
            isPlaying = false;
            play_button.setImageResource(R.drawable.language_play);
            mediaPlayer.pause();
        }

        // Stop the progress update loop
        handler.removeCallbacks(updateProgress);
    }

    /**
     * Releases the MediaPlayer resources when the activity is destroyed.
     * (Stops the audio clip if it is playing)
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    /**
     * Saves the player data when the activity is stopped.
     * This ensures the player data is saved when the app is closed and can be loaded again when the app is reopened.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Player.getInstance().savePlayerData(this);
        Log.d("Language", "Player data saved: " + Player.getInstance().toString());
    }
}
