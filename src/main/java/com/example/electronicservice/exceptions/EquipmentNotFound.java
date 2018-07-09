package com.example.electronicservice.exceptions;

import com.example.electronicservice.serviceUtils.ExceptionReason;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ExceptionReason.EquipmentNotFound)
public class EquipmentNotFound extends Exception {
    public EquipmentNotFound() {
        super("Error");
    }

    public EquipmentNotFound(String message) {
        super(message);
    }
}
