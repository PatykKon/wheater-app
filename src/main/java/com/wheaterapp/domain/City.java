package com.wheaterapp.domain;

public enum City {
    Jastarnia("Jastarnia", "PL"),
    Brigetown("Brigetown", "EN"),
    Fortaleza("Fortaleza", "BR"),
    Pissouri("Pissouri", "CY"),
    LeMorne("LeMorne", "US");

    private final String city;
    private final String lang;

    City(String city, String lang) {
        this.city = city;
        this.lang = lang;
    }
    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                '}';
    }
}

