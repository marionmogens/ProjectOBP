package com.example.projectuas.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nama;
    private String nomor;
   

    public Person() {}

    public Person(String name, String no) {
        this.nama = name;
        this.nomor = no;
    }
    
    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public void setNama(String N) { this.nama = N; }
    public String getNama() { return nama; }

    public void setNomor(String no) { this.nomor = no; }
    public String getNomor() { return nomor; }

}