package com.example.airbnb.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.airbnb.entities.Property;
import com.example.airbnb.mappers.PropertyRowMapper;

/**
 * PropertyRespository
 */
@Transactional
@Repository
public class PropertyRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PropertyRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    
    }

    public List<Property> getAllProperty() {
        String sql = "SELECT * FROM \"property\"";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public List<Property> getProperties(String address, int numRooms, int price){
      String sql = "SELECT * FROM \"property\" WHERE address like ? and numRooms >=? and price > ?";
      RowMapper<Property> rowMapper = new PropertyRowMapper();
      String arg = "%" + address + "%";
      return this.jdbcTemplate.query(sql, new Object[]{arg,numRooms,price} ,rowMapper); 
  }


  public List<Property> getBookedProperties(){
    String sql = "SELECT DISTINCT property.* FROM property JOIN booking ON booking.property_id = property.id";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql,rowMapper); 
    }


    public void createProperty(Property property){
      String sql = "INSERT INTO property VALUES (?,?,?,?,?)";
      this.jdbcTemplate.update(sql, property.getAddress(), property.getNumRooms(), property.getPrice(), property.getAllowSmoking(), property.getMaxGuestNum());

      sql = "SELECT id FROM property WHERE address = ?";
      int id = jdbcTemplate.queryForObject(sql, Integer.class, property.getAddress());

      property.setId(id);
  }


  public void createProperty2(Property property) {
    String sql = "INSERT INTO \"property\" VALUES(?, ?, ?, ?,?) ";
    String address = property.getAddress();
    int numRooms = property.getNumRooms();
    int price = property.getPrice();
    boolean allowSmoking = property.getAllowSmoking();
    int maxGuestNum = property.getMaxGuestNum();
    this.jdbcTemplate.update(sql, address,numRooms,price,allowSmoking,maxGuestNum);
  
    String select = "SELECT id FROM \"property\" WHERE address = ?";
    int propertyId = this.jdbcTemplate.queryForObject(select, Integer.class, address);
  
    property.setId(propertyId);
    }


}




