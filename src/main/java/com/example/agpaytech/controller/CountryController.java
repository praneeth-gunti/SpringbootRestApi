package com.example.agpaytech.controller;

import java.util.List;

import com.example.agpaytech.entity.Country;
import com.example.agpaytech.services.CountriesServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @GetMapping
    public ResponseEntity<List<Country>> getCountries(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "countryName") String sortBy){
        return new ResponseEntity<List<Country>>(
                new CountriesServices().getCountries(pageNo, pageSize, sortBy),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAllCountries(){
        return new ResponseEntity<List<Country>>(
                new CountriesServices().getAllCountries(),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @RequestMapping("/name/{countryName}")
    public ResponseEntity<List<Country>> getPartialHitCountries(
            @PathVariable String countryName,
            @RequestParam(defaultValue = "false") Boolean fullText) {
        return new ResponseEntity<List<Country>>(
                new CountriesServices().getCountry(countryName, fullText),
                new HttpHeaders(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCountry(@RequestBody Country listElement) {
        new CountriesServices().addCountry(listElement);
        return new ResponseEntity<>("Country added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{countryName}")
    public ResponseEntity<Object> deleteCountry(@PathVariable String countryName) {
        new CountriesServices().deleteCountry(countryName);
        return new ResponseEntity<>("Country deleted successfully", HttpStatus.OK);
    }

}




