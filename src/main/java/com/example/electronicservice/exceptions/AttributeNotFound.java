package com.example.electronicservice.exceptions;

import com.example.electronicservice.serviceUtils.ExceptionReason;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = ExceptionReason.AttributeNotFound)
public class AttributeNotFound extends Exception{
    public AttributeNotFound(){
        super("Error");

    }
    public AttributeNotFound(String message){
        super(message);
    }
}
