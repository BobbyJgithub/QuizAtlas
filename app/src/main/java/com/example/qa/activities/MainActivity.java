package com.example.qa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qa.R;

/**
 * The main activity of the app, which contains buttons to navigate to the other activities.
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Sets up the main activity and its buttons.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "Activity entered");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button country_guesser_button = findViewById(R.id.country_guesser_button);
        Button flag_guesser_button = findViewById(R.id.flag_guesser_button);
        Button language_guesser_button = findViewById(R.id.language_guesser_button);
        Button achievements_button = findViewById(R.id.achievements_button);
        Button help_menu_button = findViewById(R.id.help_menu_button);

        // Sends the user to the CountryGuesserActivity when the country_guesser_button is clicked
        country_guesser_button.setOnClickListener(v -> {
            Log.d("MainActivity", "Country Guesser button clicked");
            Intent intent = new Intent(MainActivity.this, CountryGuesserActivity.class);
            startActivity(intent);
        });

        // Sends the user to the FlagGuesserActivity when the flag_guesser_button is clicked
        flag_guesser_button.setOnClickListener(v -> {
            Log.d("MainActivity", "Flag Guesser button clicked");
            Intent intent = new Intent(MainActivity.this, FlagGuesserActivity.class);
            startActivity(intent);
        });

        // Sends the user to the LanguageGuesserActivity when the language_guesser_button is clicked
        language_guesser_button.setOnClickListener(v -> {
            Log.d("MainActivity", "Language Guesser button clicked");
            Intent intent = new Intent(MainActivity.this, LanguageGuesserActivity.class);
            startActivity(intent);
        });

        // Sends the user to the AchievementsActivity when the achievements_button is clicked
        achievements_button.setOnClickListener(v -> {
            Log.d("MainActivity", "Achievements button clicked");
            Intent intent = new Intent(MainActivity.this, AchievementsActivity.class);
            startActivity(intent);
        });

        // Sends the user to the HelpMenuActivity when the help_menu_button is clicked
        help_menu_button.setOnClickListener(v -> {
            Log.d("MainActivity", "Help Menu button clicked");
            Intent intent = new Intent(MainActivity.this, HelpMenuActivity.class);
            startActivity(intent);
        });

    }

}