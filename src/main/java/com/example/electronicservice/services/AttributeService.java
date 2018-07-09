package com.example.electronicservice.services;

import com.example.electronicservice.dao.AttributeDao;
import com.example.electronicservice.models.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttributeService {

    @Autowired
    AttributeDao attributeDao;

    public void save(Attribute attribute){ attributeDao.save(attribute);
    }

    public Optional<List<Attribute>> getAllByEquipmentId(Long id){
        return attributeDao.findByEquipment_id(id);
    }

    public List<Attribute> findAll(){
        return attributeDao.findAll();
    }

    public Optional<Attribute> findByID(Long id){
        return attributeDao.findById(id);
    }

    public Optional<Attribute> findByName(String name){
        return attributeDao.findByName(name);
    }

    public void deleteByID(Long id){
        attributeDao.deleteById(id);
    }
}
