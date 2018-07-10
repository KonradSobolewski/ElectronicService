package com.example.electronicservice.dao;

import com.example.electronicservice.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {
    Optional<Comment> findByValue(String value);

    Optional<List<Comment>> findByUser_id(Long user_id);

    Optional<List<Comment>> findByEquipment_id(Long equipment_id);

}
