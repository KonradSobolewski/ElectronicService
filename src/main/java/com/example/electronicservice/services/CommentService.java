package com.example.electronicservice.services;

import com.example.electronicservice.dao.CommentDao;
import com.example.electronicservice.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<Comment> findAll(){
        return commentDao.findAll();
    }

    public Optional<List<Comment>> findByUser_id(Long user_id){
        return commentDao.findByUser_id(user_id);
    }

    public Optional<List<Comment>> gettAllByEquipmentId(Long equipment_id){
        return commentDao.findByEquipment_id(equipment_id);
    }

    public void save(Comment comment) {
        commentDao.save(comment);
    }

    public void deleteByID(Long id) {
        commentDao.deleteById(id);
    }
}
