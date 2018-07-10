package com.example.electronicservice.controllers;

import com.example.electronicservice.exceptions.AttributeNotFound;
import com.example.electronicservice.models.Attribute;
import com.example.electronicservice.serviceUtils.ConstValues;
import com.example.electronicservice.serviceUtils.ExceptionReason;
import com.example.electronicservice.serviceUtils.ServiceMods;
import com.example.electronicservice.serviceUtils.ServiceUri;
import com.example.electronicservice.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = ServiceUri.ATTRIBUTES)
    public String showAttributes(@RequestParam(value = "id") Long id, HttpServletRequest request) {
        Optional<List<Attribute>> attributeList = attributeService.getAllByEquipmentId(id);
        attributeList.ifPresent(attributes -> request.setAttribute(ConstValues.ATTRIBUTES, attributes));
        request.setAttribute(ConstValues.mode, ServiceMods.ATTRIBUTE);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = ServiceUri.CREATEATR)
    public String createAttribute(@ModelAttribute Attribute attribute,
                                  BindingResult bindingResult,
                                  HttpServletRequest request) {
        attributeService.save(attribute);
        return showAttributes(attribute.getEquipment().getId(), request);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = ServiceUri.DELETEATR)
    public String deleteAttribute(@RequestParam(value = "attribute_id") Long attribute_id,
                                  @RequestParam(value = "id") Long id,
                                  HttpServletRequest request) throws Exception {
        if (attributeService.findByID(attribute_id).isPresent())
            attributeService.deleteByID(attribute_id);
        else
            throw new AttributeNotFound(ExceptionReason.AttributeNotFound);
        return showAttributes(id, request);
    }
}
