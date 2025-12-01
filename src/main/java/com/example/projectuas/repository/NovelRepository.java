package com.example.projectuas.repository;

import com.example.projectuas.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long> {
    // No custom methods - basic CRUD only
}