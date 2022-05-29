package com.example.agpaytech.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.example.agpaytech.config.ProjConstants;
import com.example.agpaytech.entity.Country;
import com.example.agpaytech.exception.IllegalValueException;
import com.example.agpaytech.exception.InvalidJsonData;
import com.example.agpaytech.exception.InvalidParamData;
import org.springframework.stereotype.Service;

@Service
public class CountriesServices {
    static List<Country> countries = new ArrayList<>(
            Arrays.asList(
                    new Country("USA", 13254223),
                    new Country("India", 653215462),
                    new Country("Malaysia", 232564285),
                    new Country("Singapore", 54342568),
                    new Country("China", 439323776),
                    new Country("Indonesia", 273523615),
                    new Country("Brazil", 	212559417),
                    new Country("Nigeria", 206139589),
                    new Country("Bangladesh", 164689383),
                    new Country("Russia", 145934462),
                    new Country("Mexico", 128932753),
                    new Country("Japan", 126476461)
            ));
    public List<Country> getAllCountries() {
        countries.sort(Comparator.comparing(Country::getCountryName));
        return countries;
    }

    public List<Country> getCountries(Integer pageNo, Integer pageSize, String sortBy){

        int start = pageNo * pageSize;
        int end = start + pageSize;
        int maxLength = countries.size();

        if(start < maxLength && end > maxLength){
            end = maxLength;
        }
        if(start < 0){
            throw new InvalidParamData("Page can only be offset by a positive amount");
        }else if(end > maxLength){
            throw new InvalidParamData("No countries present in the page");
        }

        if(sortBy.toLowerCase().equals(ProjConstants.COUNTRY_NAME.toLowerCase()))
            countries.sort(Comparator.comparing(Country::getCountryName));
        else if(sortBy.toLowerCase().equals(ProjConstants.POPULATION.toLowerCase()))
            countries.sort(Comparator.comparing(Country::getPopulation));
        else if(sortBy.toLowerCase().equals(ProjConstants.ID.toLowerCase()))
            countries.sort(Comparator.comparing(Country::getId));
        else
            throw new InvalidParamData("Please provide a valid sortBy value");

        return countries.subList(start, end);
    }

    public List<Country> getCountry(String partialName, Boolean fullText) {
        List<Country> partialHitCountries = new ArrayList<>();
        if(!fullText){
            for(Country country: countries) {
                String countryName = country.getCountryName();
                if (countryName.toLowerCase().contains(partialName.toLowerCase())){
                    partialHitCountries.add(country);
                }
            }
        }else{
            for(Country country: countries) {
                String countryName = country.getCountryName();
                if (countryName.toLowerCase().equals(partialName.toLowerCase())){
                    partialHitCountries.add(country);
                    return partialHitCountries;
                }
            }
        }

        return partialHitCountries;
    }

    public void addCountry(Country country) {
        if(country.getCountryName() != null && country.getPopulation() != 0){

            if(country.getId() < 0){
                throw new InvalidJsonData("Id Should be a positive integer");
            }else if (country.getId() == 0) {
                country.setId();
            }

            for(int i = 0; i < countries.size(); i++) {
                Country tempCountry = countries.get(i);
                if(tempCountry.getCountryName().toLowerCase().equals(country.getCountryName().toLowerCase())) {
                    throw new InvalidJsonData("Country is already present");
                }else if(tempCountry.getId() == country.getId()){
                    throw new InvalidJsonData("Id is already present");
                }
            }
            countries.add(country);
        }else{
            throw new InvalidJsonData("CountryName and Population should be provided to add Country");
        }
    }

    public void deleteCountry(String countryName) {
        for(int i = 0; i < countries.size(); i++) {
            Country tempCountry = countries.get(i);
            if(tempCountry.getCountryName().toLowerCase().equals(countryName.toLowerCase())) {
                countries.remove(i);
                return;
            }
        }
        throw new IllegalValueException("No Country exist for given name");
    }
}
