package com.example.electronicservice.controllers;

import com.example.electronicservice.exceptions.CategoryNotAllowed;
import com.example.electronicservice.exceptions.EquipmentNotFound;
import com.example.electronicservice.exceptions.NameNotAllowed;
import com.example.electronicservice.models.Attribute;
import com.example.electronicservice.models.Category;
import com.example.electronicservice.models.Equipment;
import com.example.electronicservice.serviceUtils.ConstValues;
import com.example.electronicservice.serviceUtils.ExceptionReason;
import com.example.electronicservice.serviceUtils.ServiceMods;
import com.example.electronicservice.serviceUtils.ServiceUri;
import com.example.electronicservice.services.AttributeService;
import com.example.electronicservice.services.CategoryService;
import com.example.electronicservice.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class ServiceViewController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = {"/", ServiceUri.HOME})
    public String home(HttpServletRequest request){
        request.setAttribute(ConstValues.mode, ServiceMods.HOME);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = ServiceUri.GETALL)
    public String allEquipments(HttpServletRequest request){
        request.setAttribute(ConstValues.equipments, equipmentService.findAll());
        request.setAttribute(ConstValues.mode, ServiceMods.GETALL);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = ServiceUri.NEW)
    public String newEquipment(HttpServletRequest request){
        request.setAttribute(ConstValues.mode, ServiceMods.NEW);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = ServiceUri.SAVE)
    public String saveEquipment(@ModelAttribute Equipment equipment,
                                BindingResult bindingResult,
                                HttpServletRequest request) throws Exception {
        Optional<Category> category = categoryService.getCategoryByName(equipment.getCategory().getName());
        if(category.isPresent()){
            equipment.getCategory().setId(category.get().getId());
            equipmentService.save(equipment);
        }else{
            throw new CategoryNotAllowed(ExceptionReason.CategoryNotAllowed);
        }
        request.setAttribute(ConstValues.equipments, equipmentService.findAll());
        request.setAttribute(ConstValues.mode, ServiceMods.GETALL);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = ServiceUri.CREATE)
    public String createEquipment(@ModelAttribute Equipment equipment,
                                  BindingResult bindingResult,
                                  HttpServletRequest request) throws Exception {
        Optional<Category> category = categoryService.getCategoryByName(equipment.getCategory().getName());
        if(equipmentService.findByName(equipment.getName()).isPresent())
            throw new NameNotAllowed(ExceptionReason.NameNotAllowed);
        else if(!category.isPresent()){
            throw new CategoryNotAllowed(ExceptionReason.CategoryNotAllowed);
        }
        equipment.getCategory().setId(category.get().getId());
            equipmentService.save(equipment);
        request.setAttribute(ConstValues.equipments, equipmentService.findAll());
        request.setAttribute(ConstValues.mode, ServiceMods.GETALL);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = ServiceUri.UPDATE)
    public String updateEquipment(@RequestParam(value = "id") Long id,
                                  HttpServletRequest request) throws Exception {
        Optional<Equipment> equipment = equipmentService.findByID(id);
        if(!equipment.isPresent())
            throw new EquipmentNotFound(ExceptionReason.EquipmentNotFound);
        request.setAttribute(ConstValues.equipment, equipment.get());
        request.setAttribute(ConstValues.mode, ServiceMods.UPDATE);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = ServiceUri.DELETE)
    public String deleteEquipment(@RequestParam(value = "id") Long  id,
                                  HttpServletRequest request) throws Exception {
        Optional<Equipment> equipment = equipmentService.findByID(id);
        if(!equipment.isPresent())
            throw new EquipmentNotFound(ExceptionReason.EquipmentNotFound);
        else
            equipmentService.deleteByID(id);

        request.setAttribute(ConstValues.equipments, equipmentService.findAll());
        request.setAttribute(ConstValues.mode,ServiceMods.GETALL);
        return ConstValues.index;
    }
}
