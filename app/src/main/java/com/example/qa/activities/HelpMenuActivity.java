package com.example.qa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qa.R;

/**
 * The activity that displays the help menu.
 * Displays information on the game and screens.
 *
 */
public class HelpMenuActivity extends AppCompatActivity{

    /**
     * Sets up the help menu activity and its buttons.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HelpMenuActivity", "Activity entered");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_help_menu);

        // Initialize buttons
        ImageView help_menu_button = findViewById(R.id.help_menu_button);
        ImageView achievements_button = findViewById(R.id.achievements_button);
        ImageView home_button = findViewById(R.id.home_button);
        ImageView country_guesser_button = findViewById(R.id.country_guesser_button);
        ImageView flag_guesser_button = findViewById(R.id.flag_guesser_button);
        ImageView language_guesser_button = findViewById(R.id.language_guesser_button);

        // Sends users to the AchievementsActivity when the achievements_button is clicked
        achievements_button.setOnClickListener(v -> {
            Intent intent = new Intent(HelpMenuActivity.this, AchievementsActivity.class);
            startActivity(intent);
        });

        // Sends users to the MainActivity when the home_button is clicked
        home_button.setOnClickListener(v -> {
            Intent intent = new Intent(HelpMenuActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Sends users to the CountryGuesserActivity when the country_guesser_button is clicked
        country_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(HelpMenuActivity.this, CountryGuesserActivity.class);
            startActivity(intent);
        });

        // Sends users to the FlagGuesserActivity when the flag_guesser_button is clicked
        flag_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(HelpMenuActivity.this, FlagGuesserActivity.class);
            startActivity(intent);
        });

        // Sends users to the LanguageGuesserActivity when the language_guesser_button is clicked
        language_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(HelpMenuActivity.this, LanguageGuesserActivity.class);
            startActivity(intent);
        });

    }
}
