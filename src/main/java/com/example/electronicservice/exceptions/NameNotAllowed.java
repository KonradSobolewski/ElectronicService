package com.example.electronicservice.exceptions;

import com.example.electronicservice.serviceUtils.ExceptionReason;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = ExceptionReason.NameNotAllowed)
public class NameNotAllowed extends Exception {
    public NameNotAllowed(){
        super("Error");
    }
    public NameNotAllowed(String message){
        super(message);
    }
}
