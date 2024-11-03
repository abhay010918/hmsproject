package com.hms.service;

import com.hms.entity.Property;
import com.hms.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> findAll(){
        return propertyRepository.findAll();
    }

    public Optional<Property> findById(long id){
        return propertyRepository.findById(id);
    }

    public Property save(Property property){
        return propertyRepository.save(property);
    }

    public void deleteById(long id){
        propertyRepository.deleteById(id);
    }
}
