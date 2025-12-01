package com.example.projectuas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "majalah")
public class Majalah extends MediaCetak implements Diskon {
    private String redaksi;
    private int edisi;
    private String issn;

    public Majalah() {}

    public Majalah(String judul, double harga, int tahunTerbit, String penulis, int hal,
                   String redaksi, int edisi, String issn) {
        super(judul, harga, tahunTerbit, penulis, hal);
        this.redaksi = redaksi;
        this.edisi = edisi;
        this.issn = issn;
    }

    // Getter & Setter
    public String getRedaksi() { return redaksi; }
    public void setRedaksi(String redaksi) { this.redaksi = redaksi; }
    
    public int getEdisi() { return edisi; }
    public void setEdisi(int edisi) { this.edisi = edisi; }
    
    public String getIssn() { return issn; }
    public void setIssn(String issn) { this.issn = issn; }

    public double hitungDiskon() {
        return getHarga() * 0.85;
    }

    @Override
    public void display(int no) {
        System.out.printf("| %-3d | %-15s | %-7.0f | %-5d | %-20s | %-3d | %-10s | %-5d | %-12s |\n",
                no, getJudul(), getHarga(), getTahunTerbit(), getPenulis(),
                getHal(), redaksi, edisi, issn);
    }

    public void printHeader() {
        System.out.println("============================================================================================================");
        System.out.printf("| %-3s | %-15s | %-7s | %-5s | %-20s | %-3s | %-10s | %-5s | %-12s |\n",
                "No", "Judul", "Harga", "Tahun", "Penulis", "Hal", "Redaksi", "Edisi", "ISSN");
        System.out.println("============================================================================================================");
    }
}