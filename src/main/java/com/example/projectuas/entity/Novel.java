package com.example.projectuas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "novel")
public class Novel extends MediaCetak implements Diskon {
    private String genre;
    private double rating;
    private int bab;
    
    public Novel() {}

    public Novel(String judul, double harga, int tahunTerbit, String penulis, int hal,
                 String genre, double rating, int bab) {
        super(judul, harga, tahunTerbit, penulis, hal);
        this.genre = genre;
        this.rating = rating;
        this.bab = bab;
    }

    // Getter & Setter
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getBab() { return bab; }
    public void setBab(int bab) { this.bab = bab; }

    public double hitungDiskon() {
        return getHarga() * 0.90;
    }

    @Override
    public void display(int no) {
        System.out.printf("| %-3d | %-15s | %-7.0f | %-5d | %-20s | %-3d | %-10s | %-6.1f | %-3d |\n",
                no, getJudul(), getHarga(), getTahunTerbit(), getPenulis(),
                getHal(), genre, rating, bab);
    }

    public void printHeader() {
        System.out.println("====================================================================================================");
        System.out.printf("| %-3s | %-15s | %-7s | %-5s | %-20s | %-3s | %-10s | %-6s | %-3s |\n",
                "No", "Judul", "Harga", "Tahun", "Penulis", "Hal", "Genre", "Rating", "Bab");
        System.out.println("====================================================================================================");
    }
}