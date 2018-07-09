package com.example.electronicservice.dao;

import com.example.electronicservice.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentDao extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByName(String name);
}
