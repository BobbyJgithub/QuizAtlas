package com.example.qa.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Toast;

import com.example.qa.models.CountryDataLoader;
import com.example.qa.models.ListCountries;
import com.example.qa.models.Player;

/**
 * LaunchActivity is the first activity that is launched when the app is started.
 * It checks if the app is started for the first time and shows the onboarding process if it is.
 * If the app is not started for the first time, it shows the main activity.
 * It also loads the countries from a CSV file and loads the player data.
 */
public class LaunchActivity extends AppCompatActivity {

    /**
     * Loads the countries from a CSV file and loads the player data.
     * If the app is started for the first time, it shows the onboarding process.
     * If the app is not started for the first time, it shows the main activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LaunchActivity", "App launched");
        super.onCreate(savedInstanceState);

        // Load countries from CSV
        loadData();

        // Load player data
        Player.getInstance().loadPlayerData(this);
        Log.d("LaunchActivity", "Player data loaded: " + Player.getInstance().toString());

        // Check if app is started for the first time
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        // Show onboarding or main activity
        if (firstStart) {
            showOnboarding();
        } else {
            showMain();
        }

    }

    /**
     * Calls the CountryDataLoader to load the countries from a CSV file.
     * Shows a toast message if the countries could not be loaded.
     *
     */
    public void loadData() {
        try {
            Log.d("LaunchActivity", "Try to load countries from CSV");
            CountryDataLoader.loadCountriesFromCSV(this, "countrydata.csv");
            if (ListCountries.getCountries().isEmpty()) {
                Log.e("LaunchActivity", "No countries loaded from CSV");
                Toast.makeText(this, "No countries loaded from CSV", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            Log.e("LaunchActivity", "Error loading countries from CSV: " + e.getMessage());
            Toast.makeText(this, "Error loading countries from CSV", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /**
     * Shows the onboarding process.
     * Sets the firstStart flag to false in the shared preferences.
     *
     */
    public void showOnboarding() {
        Log.d("LaunchActivity", "Onboarding process started");
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

    /**
     * Shows the main activity.
     *
     */
    private void showMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}