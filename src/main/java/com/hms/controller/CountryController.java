package com.hms.controller;

import com.hms.entity.Country;
import com.hms.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    // http://localhost:8080/api/v1/country
    @PostMapping("/addcountry")
    public String addCountry(){
        return "added";
    }

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries(){
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Long id) {
        return countryService.findById(id).orElse(null);
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.save(country);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Long id, @RequestBody Country country) {
        country.setId(id);
        return countryService.save(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
