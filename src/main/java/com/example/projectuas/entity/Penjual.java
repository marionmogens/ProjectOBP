package com.example.projectuas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "penjual")
public class Penjual extends Person {

    private String shift;
    private String pengalaman;
    private String email;
    private String password;
    

    public Penjual() {}

    public Penjual(String N, String No, String s, String p,String email,String pw) {
        super(N, No);
        this.shift = s;
        this.pengalaman = p;
        this.email = email;
        this.password = pw;
    }

    public void setShift(String S) { this.shift = S; }
    public String getShift() { return shift; }

    public void setPengalaman(String p) { this.pengalaman = p; }
    public String getPengalaman() { return pengalaman; }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}