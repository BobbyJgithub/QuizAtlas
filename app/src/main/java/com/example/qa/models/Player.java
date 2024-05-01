package com.example.qa.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A class that represents the player.
 * Contains information about the player, such as the countries and languages discovered.
 */
public class Player {
    /**
     * The instance of the player.
     * Allows for a single instance of the player to be used across the application.
     */
    private static Player instance = null;

    /**
     * The list of countries discovered by the player.
     */
    private List<String> countriesDiscovered;

    /**
     * The list of languages discovered by the player.
     */
    private List<String> languagesDiscovered;

    /**
     * The number of correct guesses made by the player.
     */
    private int correctGuesses;

    /**
     * The number of incorrect guesses made by the player.
     */
    private int incorrectGuesses;


    /**
     * The name of the shared preferences file where player data is stored.
     * This file is used to persist player data across application launches.
     */
    private static final String PREFS_FILE = "PlayerData";

    /**
     * The key used to store and retrieve the list of countries discovered by the player from shared preferences.
     */
    private static final String KEY_COUNTRIES_DISCOVERED = "countriesDiscovered";

    /**
     * The key used to store and retrieve the list of languages discovered by the player from shared preferences.
     */
    private static final String KEY_LANGUAGES_DISCOVERED = "languagesDiscovered";

    /**
     * The key used to store and retrieve the number of correct guesses made by the player from shared preferences.
     */
    private static final String KEY_CORRECT_GUESSES = "correctGuesses";

    /**
     * The key used to store and retrieve the number of incorrect guesses made by the player from shared preferences.
     */
    private static final String KEY_INCORRECT_GUESSES = "incorrectGuesses";

    /**
     * Constructs a player with default values.
     */
    private Player() {
        countriesDiscovered = new ArrayList<>();
        languagesDiscovered = new ArrayList<>();
        correctGuesses = 0;
        incorrectGuesses = 0;
    }

    /**
     * Returns the instance of the player.
     * If the instance does not exist, it creates a new instance.
     *
     * @return The instance of the player.
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    /**
     * Saves the player data to shared preferences.
     *
     * @param context The context of the application.
     */
    public void savePlayerData(Context context) {
        // Get the shared preferences file
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        // Get the shared preferences editor
        SharedPreferences.Editor editor = prefs.edit();

        // Save the player data to shared preferences by converting the lists to sets
        editor.putStringSet(KEY_COUNTRIES_DISCOVERED, new HashSet<>(countriesDiscovered));
        editor.putStringSet(KEY_LANGUAGES_DISCOVERED, new HashSet<>(languagesDiscovered));

        // Save the number of correct and incorrect guesses
        editor.putInt(KEY_CORRECT_GUESSES, correctGuesses);
        editor.putInt(KEY_INCORRECT_GUESSES, incorrectGuesses);

        // Apply the changes
        editor.apply();
    }

    /**
     * Loads the player data from shared preferences.
     *
     * @param context The context of the application.
     */
    public void loadPlayerData(Context context) {
        // Get the shared preferences file
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);

        // Load the player data from shared preferences
        countriesDiscovered = new ArrayList<>(prefs.getStringSet(KEY_COUNTRIES_DISCOVERED, new HashSet<>()));
        languagesDiscovered = new ArrayList<>(prefs.getStringSet(KEY_LANGUAGES_DISCOVERED, new HashSet<>()));
        correctGuesses = prefs.getInt(KEY_CORRECT_GUESSES, 0);
        incorrectGuesses = prefs.getInt(KEY_INCORRECT_GUESSES, 0);
    }

    /**
     * Gets the list of countries discovered by the player.
     *
     * @return The list of countries discovered by the player.
     */
    public List<String> getCountriesDiscovered() {
        return countriesDiscovered;
    }

    /**
     * Gets the list of languages discovered by the player.
     *
     * @return The list of languages discovered by the player.
     */
    public List<String> getLanguagesDiscovered() {
        return languagesDiscovered;
    }

    /**
     * Gets the number of correct guesses made by the player.
     *
     * @return The number of correct guesses made by the player.
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }

    /**
     * Sets the number of correct guesses made by the player.
     *
     * @param correctGuesses The number of correct guesses made by the player.
     */
    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    /**
     * Gets the number of incorrect guesses made by the player.
     *
     * @return The number of incorrect guesses made by the player.
     */
    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    /**
     * Sets the number of incorrect guesses made by the player.
     *
     * @param incorrectGuesses The number of incorrect guesses made by the player.
     */
    public void setIncorrectGuesses(int incorrectGuesses) {
        this.incorrectGuesses = incorrectGuesses;
    }

    /**
     * Represents the player data as a string.
     *
     * @return The string representation of the player data.
     */
    @NonNull
    @Override
    public String toString() {
        return "Player{" +
                "countriesDiscovered=" + countriesDiscovered +
                ", languagesDiscovered=" + languagesDiscovered +
                ", correctGuesses=" + correctGuesses +
                ", incorrectGuesses=" + incorrectGuesses +
                '}';
    }

}