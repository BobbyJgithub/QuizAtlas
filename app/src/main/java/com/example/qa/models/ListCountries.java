package com.example.qa.models;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents a list of countries.
 * Contains a list of countries and methods to access and modify the list.
 */
public class ListCountries extends Application {
    /**
     * The list of countries.
     */
    private static List<Country> listOfCountries = new ArrayList<>();

    /**
     * The current country.
     * Used to supply a random country to the game activities.
     */
    private static Country currentCountry;

    /**
     * Returns the list of countries.
     *
     * @return The list of countries.
     */
    public static List<Country> getCountries() {
        return listOfCountries;
    }

    /**
     * Adds a country to the list of countries.
     * Used by the CountryDataLoader class to populate.
     *
     * @param country The country to add.
     */
    public static void addCountry(Country country) {
        listOfCountries.add(country);
    }

    /**
     * Returns a random country from the list of countries.
     * Used by the game activities to get a random country.
     *
     * @return A random country.
     */
    public static Country getRandomCountry() {
        if (listOfCountries != null && !listOfCountries.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(listOfCountries.size());
            currentCountry = listOfCountries.get(randomIndex);
        } else {
            Log.e("ListCountries", "Countries list is empty or null");
        }
        return currentCountry;
    }

}