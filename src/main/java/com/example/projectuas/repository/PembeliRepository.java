package com.example.projectuas.repository;

import com.example.projectuas.entity.Pembeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembeliRepository extends JpaRepository<Pembeli, Long> {
    // No custom methods - basic CRUD only
}