package com.example.electronicservice.controllers;

import com.example.electronicservice.serviceUtils.ConstValues;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @ExceptionHandler(Exception.class)
    public String renderErrorPage(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals(ConstValues.ANONYMOUS))
            return "redirect:/login";
        String errorMsg = "Http Error Code: " + response.getStatus() + ". " + exception.getMessage();
        request.setAttribute("errorMsg", errorMsg);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
