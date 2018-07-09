package com.example.electronicservice.services;

import com.example.electronicservice.dao.EquipmentDao;
import com.example.electronicservice.models.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    public void save(Equipment equipment) {
        equipmentDao.save(equipment);
    }

    public List<Equipment> findAll() {
        return equipmentDao.findAll();
    }

    public Optional<Equipment> findByID(Long id) {
        return equipmentDao.findById(id);
    }

    public Optional<Equipment> findByName(String name) {
        return equipmentDao.findByName(name);
    }

    public void deleteByID(Long id) {
        equipmentDao.deleteById(id);
    }
}
