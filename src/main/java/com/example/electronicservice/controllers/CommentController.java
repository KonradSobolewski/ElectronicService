package com.example.electronicservice.controllers;

import com.example.electronicservice.models.*;
import com.example.electronicservice.serviceUtils.ConstValues;
import com.example.electronicservice.serviceUtils.ServiceMods;
import com.example.electronicservice.serviceUtils.ServiceUri;
import com.example.electronicservice.services.CommentService;
import com.example.electronicservice.services.CustomUserDetailsService;
import com.example.electronicservice.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private EquipmentService equipmentService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = ServiceUri.COMMENTS)
    public String showComments(@RequestParam(value = "id") Long id, HttpServletRequest request) {
        Optional<List<Comment>> commentList = commentService.gettAllByEquipmentId(id);
        commentList.ifPresent(comments -> request.setAttribute(ConstValues.COMMENTS, comments));
        request.setAttribute(ConstValues.mode, ServiceMods.COMMENTS);
        return ConstValues.index;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = ServiceUri.SAVECOMMENT)
    public String saveComment(@RequestParam(value = "id") Long id, HttpServletRequest request){
        String value = request.getParameter("commentValue");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment comment = new Comment();
        comment.setValue(value);
        Equipment equipment = equipmentService.findByID(id).get();
        comment.setEquipment(equipment);
        User user = userDetailsService.getByUsername(name).get();
        comment.setUser(user);
        commentService.save(comment);
        request.setAttribute(ConstValues.mode, ServiceMods.COMMENTS);
        return showComments(id,request);
    }
}
