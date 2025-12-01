package com.example.projectuas.repository;

import com.example.projectuas.entity.Komik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomikRepository extends JpaRepository<Komik, Long> {
    // No custom methods - basic CRUD only
}