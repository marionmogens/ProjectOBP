package com.example.projectuas.repository;

import com.example.projectuas.entity.Majalah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajalahRepository extends JpaRepository<Majalah, Long> {
    // No custom methods - basic CRUD only
}