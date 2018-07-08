package com.example.electronicservice.exceptions;

import com.example.electronicservice.serviceUtils.ExceptionReason;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = ExceptionReason.CategoryNotAllowed)
public class CategoryNotAllowed extends Exception {
    public CategoryNotAllowed(){
        super("Error");
    }
    public CategoryNotAllowed(String message){
        super(message);
    }
}
