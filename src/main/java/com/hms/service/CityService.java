package com.hms.service;

import com.hms.entity.City;
import com.hms.repository.CityRepository;
import com.hms.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public Optional<City> findById(long id){
        return cityRepository.findById(id);
    }

    public City save(City city){
        return cityRepository.save(city);
    }

    public void deleteById(long id){
        cityRepository.deleteById(id);
    }
}
