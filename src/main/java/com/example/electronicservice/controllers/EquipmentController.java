package com.example.electronicservice.controllers;

import com.example.electronicservice.exceptions.EquipmentNotFound;
import com.example.electronicservice.exceptions.NameNotAllowed;
import com.example.electronicservice.models.Equipment;
import com.example.electronicservice.serviceUtils.RestUri;
import com.example.electronicservice.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = RestUri.GETALL, method = RequestMethod.GET, produces = "application/json")
    public List<Equipment> getAllPosts() {
        return equipmentService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(RestUri.CREATE)
    public void createEquipment(@RequestBody Equipment equipment) throws NameNotAllowed {
        if (equipmentService.findByName(equipment.getName()) != equipmentService.findByID(equipment.getId()))
            throw new NameNotAllowed();
        else
            equipmentService.save(equipment);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(RestUri.UPDATE)
    public void updateProduct(@RequestBody Equipment equipment) throws EquipmentNotFound {
        if (!equipmentService.findByID(equipment.getId()).isPresent())
            throw new EquipmentNotFound();
        else
            equipmentService.save(equipment);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(RestUri.GETBYID)
    public Equipment getEquipmentById(@RequestParam(value = "id") Long id) throws EquipmentNotFound {
        Optional<Equipment> obj = equipmentService.findByID(id);
        if (obj.isPresent())
            return obj.get();
        else
            throw new EquipmentNotFound();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(RestUri.GETBYNAME)
    public Equipment getEquipmentByName(@RequestParam(value = "name") String name) throws EquipmentNotFound {
        Optional<Equipment> obj = equipmentService.findByName(name);
        if (obj.isPresent())
            return obj.get();
        else
            throw new EquipmentNotFound();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(RestUri.DELETE)
    public void deleteEquipment(@RequestParam(value = "id") Long id) throws EquipmentNotFound {
        Optional<Equipment> obj = equipmentService.findByID(id);
        if (obj.isPresent())
            equipmentService.deleteByID(id);
        else
            throw new EquipmentNotFound();
    }
}
