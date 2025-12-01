package com.example.projectuas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pembeli")
public class Pembeli extends Person {
    private boolean member;

    public Pembeli() {}

    public Pembeli(String n, String no, boolean m) {
        super(n, no);
        this.member = m;
    }

    public void setMember(boolean member) { this.member = member; }
    public boolean isMember() { return member; }
}