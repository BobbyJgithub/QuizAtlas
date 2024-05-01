package com.example.qa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qa.models.Country;
import com.example.qa.models.Player;
import com.example.qa.R;
import com.example.qa.models.ListCountries;

/**
 * Activity for the Flag Guesser game mode
 * It retrieves a random country from the ListCountries class and displays clues about the country.
 * The user can input their guess in the EditText box and submit it by pressing the enter key.
 *
 */
public class FlagGuesserActivity extends AppCompatActivity {

    /**
     * The player instance.
     * Used to store player data.
     */
    private Player player = Player.getInstance();

    /**
     * Retrieves a random country from the ListCountries class.
     * Sets up the Flag Guesser activity and its buttons.
     * Initializes the game with this game modes unique clue: flag.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("FlagGuesserActivity", "Activity entered");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_flag_game);

        // Get a random country from the ListCountries class
        Country currentCountry = ListCountries.getRandomCountry();
        String currentCountryName = currentCountry.getName();
        Log.d("FlagGuesserActivity", "Current country: " + currentCountryName);

        // Add the current country to the player's list of discovered countries
        if (!player.getCountriesDiscovered().contains(currentCountryName)) {
            player.getCountriesDiscovered().add(currentCountryName);
        }

        // Initialize buttons and views
        ImageView help_menu_button = findViewById(R.id.help_menu_button);
        ImageView achievements_button = findViewById(R.id.achievements_button);
        ImageView flag_image = findViewById(R.id.flag);
        Button culture_clue_button = findViewById(R.id.culture_clue);
        TextView culture_clue_text = findViewById(R.id.culture_clue_text);
        Button historical_clue_button = findViewById(R.id.historical_clue);
        TextView historical_clue_text = findViewById(R.id.historical_clue_text);
        EditText guess_box = findViewById(R.id.guess_box);
        ImageView home_button = findViewById(R.id.home_button);
        ImageView country_guesser_button = findViewById(R.id.country_guesser_button);
        ImageView flag_guesser_button = findViewById(R.id.flag_guesser_button);
        ImageView language_guesser_button = findViewById(R.id.language_guesser_button);

        // Sends the user to the HelpMenuActivity when the help_menu_button is clicked
        help_menu_button.setOnClickListener(v -> {
            Intent intent = new Intent(FlagGuesserActivity.this, HelpMenuActivity.class);
            startActivity(intent);
        });

        // Sends the user to the AchievementsActivity when the achievements_button is clicked
        achievements_button.setOnClickListener(v -> {
            Intent intent = new Intent(FlagGuesserActivity.this, AchievementsActivity.class);
            startActivity(intent);
        });

        // Sets header text to darker color, indicating that the clue has been revealed. Calls revealClue method to display the clue.
        culture_clue_button.setOnClickListener(v -> {
            culture_clue_button.setTextColor(getResources().getColor(R.color.primary_grey));
            revealClue("culture", currentCountry, culture_clue_text);
        });

        // Sets header text to darker color, indicating that the clue has been revealed. Calls revealClue method to display the clue.
        historical_clue_button.setOnClickListener(v -> {
            historical_clue_button.setTextColor(getResources().getColor(R.color.primary_grey));
            revealClue("historical", currentCountry, historical_clue_text);
        });

        // Set an OnEditorActionListener for the guess_box to check the user's guess when the enter key is pressed
        guess_box.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                checkGuess(guess_box.getText().toString(), currentCountry.getName(), currentCountry.getDescription(), currentCountry.getFlag());
                return true;
            }
            return false;
        });

        // Sends the user to the MainActivity when the home_button is clicked
        home_button.setOnClickListener(v -> {
            Intent intent = new Intent(FlagGuesserActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Sends the user to the CountryGuesserActivity when the country_guesser_button is clicked
        country_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(FlagGuesserActivity.this, CountryGuesserActivity.class);
            startActivity(intent);
        });

        // Sends the user to the LanguageGuesserActivity when the language_guesser_button is clicked
        language_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(FlagGuesserActivity.this, LanguageGuesserActivity.class);
            startActivity(intent);
        });

        // Load the flag image for the current country
        try {
            // Get the drawable resource ID for the flag image
            int drawableResourceId = getResources().getIdentifier(currentCountry.getFlag(), "drawable", getPackageName());
            if (drawableResourceId != 0) {
                flag_image.setImageResource(drawableResourceId);
            } else {
                Log.e("FlagGuesserActivity", "Drawable resource not found for flag: " + currentCountry.getFlag());
            }
        } catch (Exception e) {
            Log.e("FlagGuesserActivity", "Error loading flag image: " + e.getMessage());
            Toast.makeText(this, "Error loading flag image", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Displays a clue about the current country.
     *
     * @param clueType The type of clue to reveal (culture or historical).
     * @param currentCountry The current country.
     * @param clueText The TextView to display the clue.
     */
    private void revealClue(String clueType, Country currentCountry, TextView clueText) {
        if (clueType.equals("culture")) {
            clueText.setText(currentCountry.getCulturalInfo());
        } else if (clueType.equals("historical")) {
            clueText.setText(currentCountry.getHistoricalInfo());
        }
    }

    /**
     * Checks the user's guess against the correct answer.
     * Displays a toast message indicating if the guess was correct or incorrect.
     *
     * @param guess The user's guess.
     * @param correctAnswer The correct answer.
     * @param description The description of the current country.
     * @param flag The flag of the current country.
     */
    private void checkGuess(String guess, String correctAnswer, String description, String flag) {
        Log.d("FlagGuesserActivity", "Checking guess: " + guess);
        correctAnswer = correctAnswer.toLowerCase();
        guess = guess.toLowerCase();
        if (guess.equals(correctAnswer)) {
            Log.d("CountryGuesserActivity", "Current country is correct");
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();

            // Update player data when the guess is correct
            player.setCorrectGuesses(player.getCorrectGuesses() + 1);

            // Send the user to the CorrectGuessActivity with the correct answer, description, flag, and game mode info to be displayed
            Intent intent = new Intent(FlagGuesserActivity.this, CorrectGuessActivity.class);
            intent.putExtra("correctAnswer", correctAnswer);
            intent.putExtra("description", description);
            intent.putExtra("flag", flag);
            intent.putExtra("gameMode", "country");
            startActivity(intent);

        } else {
            Log.d("CountryGuesserActivity", "Current country is incorrect");
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();

            // Update player data when the guess is incorrect
            player.setIncorrectGuesses(player.getIncorrectGuesses() + 1);
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
        Log.d("Flag", "Player data saved: " + Player.getInstance().toString());
    }
}
