package com.hms.service;

import com.hms.entity.Country;
import com.hms.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public Optional<Country> findById(long id){
        return countryRepository.findById(id);
    }

    public Country save(Country country){
        return countryRepository.save(country);
    }

    public void deleteById(long id){
        countryRepository.deleteById(id);
    }
}
