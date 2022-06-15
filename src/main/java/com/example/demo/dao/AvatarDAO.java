package com.example.demo.dao;

import com.example.demo.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarDAO extends JpaRepository<Avatar,Long> {
}
