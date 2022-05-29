package com.example.agpaytech.entity;


import javax.persistence.*;
import java.util.Random;

@Entity
public class Country {

    @Id
    private int id;

    private String countryName;
    private long population;

    public Country(String countryName, long population) {
        this.id = new Random().nextInt() & Integer.MAX_VALUE;
        this.countryName = countryName;
        this.population = population;
    }

    public String getCountryName() {
        return countryName;
    }

    public long getPopulation() {
        return population;
    }

    public int getId() {
        return id;
    }
    public void setId() {
        this.id = new Random().nextInt() & Integer.MAX_VALUE;
    }
}
