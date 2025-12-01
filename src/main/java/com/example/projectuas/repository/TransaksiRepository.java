package com.example.projectuas.repository;

import com.example.projectuas.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    // No custom methods - basic CRUD only
}