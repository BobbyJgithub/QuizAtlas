package com.example.qa.models;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class that loads country data from a CSV file.
 */
public class CountryDataLoader {

    /**
     * Loads country data from a CSV file and adds it to the list of countries.
     *
     * @param context    The context of the application.
     * @param csvFileName The name of the CSV file containing the country data.
     */
    public static void loadCountriesFromCSV(Context context, String csvFileName) {
        // Read data from CSV file
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(csvFileName)))) {
            reader.readLine(); // Skip the header line
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String name = parts[0];
                String description = parts[1];
                String culturalInfo = parts[2];
                String historicalInfo = parts[3];
                String landmarkInfo = parts[4];
                String landmarkImg = parts[5];
                String language = parts[6];
                String transcript = parts[7];
                String flag = parts[8];

                // Create a country object and add it to the list of countries
                Country country = new Country(name, description, culturalInfo, historicalInfo, landmarkInfo, landmarkImg, language, transcript, flag);
                ListCountries.addCountry(country);
            }
        } catch (IOException e) {
            Log.e("CountryDataLoader", "Error reading data from CSV file", e);
        }

        if (ListCountries.getCountries().isEmpty()) {
            Log.e("CountryDataLoader", "No countries loaded from CSV");
        } else {
            Log.d("CountryDataLoader", "Countries loaded from CSV: " + ListCountries.getCountries());
        }
    }
}