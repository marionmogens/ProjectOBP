package com.example.projectuas.repository;

import com.example.projectuas.entity.Penjual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenjualRepository extends JpaRepository<Penjual, Long> {
    // No custom methods - basic CRUD only
}