package com.example.electronicservice.exceptions;


import com.example.electronicservice.serviceUtils.ExceptionReason;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ExceptionReason.EquipmentNotAllowed)
public class EquipmentNotAllowed extends Exception {
    public EquipmentNotAllowed() {
        super("Error");
    }

    public EquipmentNotAllowed(String message) {
        super(message);
    }
}
