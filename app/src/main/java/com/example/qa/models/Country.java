package com.example.qa.models;

import androidx.annotation.NonNull;

/**
 * A class that represents a country.
 * Contains information about the country, such as its name, description, and landmarks.
 */
public class Country {
    /**
     * The name of the country.
     */
    private String name;
    /**
     * A brief description of the country.
     */
    private String description;
    /**
     * Information about the country's culture.
     * Used as a clue.
     */
    private String culturalInfo;
    /**
     * Information about the country's history.
     * Used as a clue.
     */
    private String historicalInfo;
    /**
     * Information about the country's landmarks.
     * Used as a clue.
     */
    private String landmarkInfo;
    /**
     * The image file name of the country's landmark.
     * Used as a clue.
     */
    private String landmarkImg;
    /**
     * The language spoken in the country.
     * Also the mp3 file name of the language.
     */
    private String language;
    /**
     * The transcript of the language spoken in the country.
     * Used as a clue.
     */
    private String transcript;
    /**
     * The image file name of the country's flag.
     * Used as a clue.
     */
    private String flag;

    /**
     * Constructs a country with the given information.
     *
     * @param name The name of the country.
     * @param description A brief description of the country.
     * @param culturalInfo Information about the country's culture.
     * @param historicalInfo Information about the country's history.
     * @param landmarkInfo Information about the country's landmarks.
     * @param landmarkImg The image file name of the country's landmark.
     * @param language The language spoken in the country.
     * @param transcript The transcript of the language spoken in the country.
     * @param flag The image file name of the country's flag.
     */
    public Country(String name, String description, String culturalInfo, String historicalInfo, String landmarkInfo, String landmarkImg, String language, String transcript, String flag) {
        this.name = name;
        this.description = description;
        this.culturalInfo = culturalInfo;
        this.historicalInfo = historicalInfo;
        this.landmarkInfo = landmarkInfo;
        this.landmarkImg = landmarkImg;
        this.language = language;
        this.transcript = transcript;
        this.flag = flag;
    }

    /**
     * Gets the name of the country.
     *
     * @return The name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the country.
     *
     * @return The description of the country.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the cultural clue of the country.
     *
     * @return Information about the country's culture.
     */
    public String getCulturalInfo() {
        return culturalInfo;
    }

    /**
     * Gets the historical clue of the country.
     *
     * @return Information about the country's history.
     */
    public String getHistoricalInfo() {
        return historicalInfo;
    }

    /**
     * Gets the landmark clue of the country.
     *
     * @return Information about the country's landmarks.
     */
    public String getLandmarkInfo() {
        return landmarkInfo;
    }

    /**
     * Gets the image file name of the country's landmark.
     *
     * @return The image file name of the country's landmark.
     */
    public String getLandmarkImg() {
        return landmarkImg;
    }

    /**
     * Gets the language spoken in the country.
     * Also the mp3 file name of the language.
     *
     * @return The language spoken in the country.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Gets the transcript of the language spoken in the country.
     *
     * @return The transcript of the language spoken in the country.
     */
    public String getTranscript() {
        return transcript;
    }

    /**
     * Gets the image file name of the country's flag.
     *
     * @return The image file name of the country's flag.
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Returns a string representation of the country.
     *
     * @return A string representation of the country.
     */
    @NonNull
    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", culturalInfo='" + culturalInfo + '\'' +
                ", historicalInfo='" + historicalInfo + '\'' +
                ", landmarkInfo='" + landmarkInfo + '\'' +
                ", landmarkImg='" + landmarkImg + '\'' +
                ", language='" + language + '\'' +
                ", transcript='" + transcript + '\'' +
                ", flag='" + flag + '\'' +
                "}\n";
    }
}