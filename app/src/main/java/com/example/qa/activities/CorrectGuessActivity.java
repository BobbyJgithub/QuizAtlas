package com.example.qa.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qa.R;

/**
 * The activity that displays the correct guess screen.
 * Displays the correct answer and description of the answer.
 *
 */
public class CorrectGuessActivity extends AppCompatActivity{

    /**
     * Sets up the correct guess activity and its buttons.
     * Displays the correct answer and description of the answer.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CorrectGuessActivity", "Activity entered");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_correct_guess);

        // Initialize buttons and views
        ImageView help_menu_button = findViewById(R.id.help_menu_button);
        ImageView achievements_button = findViewById(R.id.achievements_button);
        ImageView home_button = findViewById(R.id.home_button);
        ImageView country_guesser_button = findViewById(R.id.country_guesser_button);
        ImageView flag_guesser_button = findViewById(R.id.flag_guesser_button);
        ImageView language_guesser_button = findViewById(R.id.language_guesser_button);
        ImageView correct_image = findViewById(R.id.correct_image);
        TextView correct_name = findViewById(R.id.correct_name);
        TextView correct_description = findViewById(R.id.correct_description);
        String correctAnswer = getIntent().getStringExtra("correctAnswer");
        String description = getIntent().getStringExtra("description");
        String flag = getIntent().getStringExtra("flag");
        String gamemode = getIntent().getStringExtra("gameMode");

        // Sends users to the HelpMenuActivity when the help_menu_button is clicked
        help_menu_button.setOnClickListener(v -> {
            Intent intent = new Intent(CorrectGuessActivity.this, HelpMenuActivity.class);
            startActivity(intent);
        });

        // Sends users to the AchievementsActivity when the achievements_button is clicked
        achievements_button.setOnClickListener(v -> {
            Intent intent = new Intent(CorrectGuessActivity.this, AchievementsActivity.class);
            startActivity(intent);
        });

        // Sends users to the MainActivity when the home_button is clicked
        home_button.setOnClickListener(v -> {
            Intent intent = new Intent(CorrectGuessActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Sends users to the CountryGuesserActivity when the country_guesser_button is clicked
        country_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(CorrectGuessActivity.this, CountryGuesserActivity.class);
            startActivity(intent);
        });

        // Sends users to the FlagGuesserActivity when the flag_guesser_button is clicked
        flag_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(CorrectGuessActivity.this, FlagGuesserActivity.class);
            startActivity(intent);
        });

        // Sends users to the LanguageGuesserActivity when the language_guesser_button is clicked
        language_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(CorrectGuessActivity.this, LanguageGuesserActivity.class);
            startActivity(intent);
        });

        // Set the correct image, name, and description of the correctly guessed answer
        int drawableResourceId = getResources().getIdentifier(flag, "drawable", getPackageName());
        if (drawableResourceId != 0) {
            correct_image.setImageResource(drawableResourceId);
        } else {
            Log.e("FlagGuesserActivity", "Drawable resource not found for flag");
        }

        correct_name.setText("The " + gamemode + " was " + Character.toUpperCase(correctAnswer.charAt(0)) + correctAnswer.substring(1) + ".");
        correct_description.setText(description);
    }
}
