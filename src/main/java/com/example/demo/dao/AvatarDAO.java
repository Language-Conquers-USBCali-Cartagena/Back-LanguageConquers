package com.example.demo.dao;

import com.example.demo.model.Avatar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface AvatarDAO extends JpaRepository<Avatar,Long> {

    @Query(value = "SELECT * FROM avatar", nativeQuery = true)
    Page<Avatar> findAllPage (Pageable pageable);

}
