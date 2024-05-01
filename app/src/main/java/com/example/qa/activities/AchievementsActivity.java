package com.example.qa.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qa.models.Player;
import com.example.qa.R;

/**
 * The activity that displays the user's achievements.
 * Uses the player data to display the user's discoveries and stats.
 *
 */
public class AchievementsActivity extends AppCompatActivity {

    /**
     * Sets up the achievements activity and its buttons.
     * Displays the user's discovered countries and languages based off player data in grid format.
     * Displays the user's correct and incorrect guesses.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AchievementsActivity", "Activity entered");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_achievements);

        // Initialize buttons and views
        ImageView help_menu_button = findViewById(R.id.help_menu_button);
        ImageView achievements_button = findViewById(R.id.achievements_button);
        ImageView home_button = findViewById(R.id.home_button);
        ImageView country_guesser_button = findViewById(R.id.country_guesser_button);
        ImageView flag_guesser_button = findViewById(R.id.flag_guesser_button);
        ImageView language_guesser_button = findViewById(R.id.language_guesser_button);

        // Initialize flags views
        ImageView flag1 = findViewById(R.id.flag1);
        ImageView flag2 = findViewById(R.id.flag2);
        ImageView flag3 = findViewById(R.id.flag3);
        ImageView flag4 = findViewById(R.id.flag4);
        ImageView flag5 = findViewById(R.id.flag5);
        ImageView flag6 = findViewById(R.id.flag6);
        ImageView flag7 = findViewById(R.id.flag7);
        ImageView flag8 = findViewById(R.id.flag8);
        ImageView flag9 = findViewById(R.id.flag9);

        // Initialize languages views
        TextView language1 = findViewById(R.id.language1);
        TextView language2 = findViewById(R.id.language2);
        TextView language3 = findViewById(R.id.language3);
        TextView language4 = findViewById(R.id.language4);
        TextView language5 = findViewById(R.id.language5);
        TextView language6 = findViewById(R.id.language6);
        TextView language7 = findViewById(R.id.language7);
        TextView language8 = findViewById(R.id.language8);
        TextView language9 = findViewById(R.id.language9);

        // Initialize stats views
        TextView correct_guesses = findViewById(R.id.correct_guesses);
        TextView incorrect_guesses = findViewById(R.id.incorrect_guesses);

        // Get the player instance
        Player player = Player.getInstance();

        // Sends the user to the HelpMenuActivity when the help_menu_button is clicked
        help_menu_button.setOnClickListener(v -> {
            Intent intent = new Intent(AchievementsActivity.this, HelpMenuActivity.class);
            startActivity(intent);
        });

        // Sends the user to the MainActivity when the home_button is clicked
        home_button.setOnClickListener(v -> {
            Intent intent = new Intent(AchievementsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Sends the user to the CountryGuesserActivity when the country_guesser_button is clicked
        country_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(AchievementsActivity.this, CountryGuesserActivity.class);
            startActivity(intent);
        });

        // Sends the user to the FlagGuesserActivity when the flag_guesser_button is clicked
        flag_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(AchievementsActivity.this, FlagGuesserActivity.class);
            startActivity(intent);
        });

        // Sends the user to the LanguageGuesserActivity when the language_guesser_button is clicked
        language_guesser_button.setOnClickListener(v -> {
            Intent intent = new Intent(AchievementsActivity.this, LanguageGuesserActivity.class);
            startActivity(intent);
        });

        // Based on the countries and languages discovered by the player, display the corresponding flags and languages in the grid layouts
        for (String country : player.getCountriesDiscovered()) {
            switch (country) {
                case "United States":
                    flag1.setImageResource(R.drawable.us);
                    break;
                case "Japan":
                    flag2.setImageResource(R.drawable.jp);
                    break;
                case "Germany":
                    flag3.setImageResource(R.drawable.de);
                    break;
                case "France":
                    flag4.setImageResource(R.drawable.fr);
                    break;
                case "Italy":
                    flag5.setImageResource(R.drawable.it);
                    break;
                case "China":
                    flag6.setImageResource(R.drawable.cn);
                    break;
                case "Brazil":
                    flag7.setImageResource(R.drawable.br);
                    break;
                case "India":
                    flag8.setImageResource(R.drawable.in);
                    break;
                case "Russia":
                    flag9.setImageResource(R.drawable.ru);
                    break;
            }
        }

        for (String language : player.getLanguagesDiscovered()) {
            switch (language) {
                case "english":
                    language1.setText("English");
                    break;
                case "japanese":
                    language2.setText("Japanese");
                    break;
                case "german":
                    language3.setText("German");
                    break;
                case "french":
                    language4.setText("French");
                    break;
                case "italian":
                    language5.setText("Italian");
                    break;
                case "mandarin":
                    language6.setText("Mandarin");
                    break;
                case "portuguese":
                    language7.setText("Portuguese");
                    break;
                case "hindi":
                    language8.setText("Hindi");
                    break;
                case "russian":
                    language9.setText("Russian");
                    break;
            }
        }

        // Display the player's correct and incorrect guesses
        correct_guesses.setText(String.valueOf(player.getCorrectGuesses()));
        incorrect_guesses.setText(String.valueOf(player.getIncorrectGuesses()));
    }
}
