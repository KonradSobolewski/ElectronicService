package com.example.electronicservice.controllers;

import com.example.electronicservice.models.Role;
import com.example.electronicservice.models.User;
import com.example.electronicservice.models.UserDto;
import com.example.electronicservice.serviceUtils.ConstValues;
import com.example.electronicservice.serviceUtils.ServiceUri;
import com.example.electronicservice.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class OpenViewController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping(value = ServiceUri.LOGIN)
    public String login() {
        return "login";
    }

    @GetMapping(value = ServiceUri.REGISTER)
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping(value = ServiceUri.REGISTER)
    public String processRegister(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, HttpServletRequest request) {
        if (userDto.getEmail().isEmpty() || userDto.getUsername().isEmpty() || userDto.getPassword().isEmpty()
                || userDto.getConfirmPassword().isEmpty()) {
            request.setAttribute("error", "Invalid");
        } else if (!userDto.getConfirmPassword().equals(userDto.getPassword())) {
            request.setAttribute("error", "Pass");
        } else if (userDetailsService.getByUsername(userDto.getUsername()).isPresent() ||
                userDetailsService.getByEmail(userDto.getEmail()).isPresent()) {
            request.setAttribute("error", "Exists");
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role("USER"));
            User user = new User(
                    userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.getEmail(),
                    roles);
            userDetailsService.save(user);
            return "redirect:/login?register=1";
        }
        return "registration";
    }

    @GetMapping(value = ServiceUri.LOGOUT)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().equals(ConstValues.ANONYMOUS)) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=1";
    }

}
