package com.example.airbnb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.airbnb.entities.Property;
import com.example.airbnb.repositories.PropertyRepository;


/**
 * PropertyController
 */
@RestController
@RequestMapping(path="/api")
public class PropertyController {
    @Autowired
    PropertyRepository propertyRepository;

    @GetMapping(value="/property", produces="application/json")
    public List<Property> displayProperty(){
        return propertyRepository.getAllProperty();

    }

    @GetMapping(path="/properties", produces="application/json")
    public List<Property> displayProperties(@RequestParam String address, int numRooms, int price){
        return propertyRepository.getProperties(address, numRooms, price);
    }
    // http://localhost:8080/api/properties?address=side&numRooms=0&price=1000
    

    @GetMapping(path="/bookedProperties", produces="application/json")
    public List<Property> displayBookedProperties(){
        return propertyRepository.getBookedProperties();
    }

    @GetMapping(path="/create_property", produces="application/json")
    public void createProperty(
        @RequestParam("address") String address,
        @RequestParam("numRooms") int numRooms,
        @RequestParam("price") int price,
        @RequestParam("allowSmoking") boolean allowSmoking,
        @RequestParam("maxGuestNum") int maxGuestNum
    ){
        Property property = new Property();
        property.setAddress(address);
        property.setAllowSmoking(allowSmoking);
        property.setMaxGuestNum(maxGuestNum);
        property.setNumRooms(numRooms);
        property.setPrice(price);
        
        propertyRepository.createProperty(property);
    }

    @PostMapping(value="/properties", produces="application/json")
    public Property createProperty(@RequestBody Property property) {
            propertyRepository.createProperty2(property);
            return property;
    }

}

