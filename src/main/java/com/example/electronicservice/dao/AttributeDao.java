package com.example.electronicservice.dao;

import com.example.electronicservice.models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeDao extends JpaRepository<Attribute,Long> {
    Optional<Attribute> findByName(String name) ;
}
