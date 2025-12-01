package com.example.projectuas.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "media_cetak")
public abstract class MediaCetak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String judul;
    private double harga;
    private int tahunTerbit;
    private String penulis;
    private int hal;

    public MediaCetak() {}

    public MediaCetak(String judul, double harga, int tahunTerbit, String penulis, int hal) {
        this.judul = judul;
        this.harga = harga;
        this.tahunTerbit = tahunTerbit;
        this.penulis = penulis;
        this.hal = hal;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public void setJudul(String judul) { this.judul = judul; }
    public String getJudul() { return judul; }

    public void setHarga(double harga) { this.harga = harga; }
    public double getHarga() { return harga; }

    public void setTahunTerbit(int tahunTerbit) { this.tahunTerbit = tahunTerbit; }
    public int getTahunTerbit() { return tahunTerbit; }

    public void setPenulis(String penulis) { this.penulis = penulis; }
    public String getPenulis() { return penulis; }

    public void setHal(int hal) { this.hal = hal; }
    public int getHal() { return hal; }


    public abstract void display(int no);
    public abstract void printHeader();
}