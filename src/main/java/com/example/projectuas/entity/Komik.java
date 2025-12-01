package com.example.projectuas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "komik")
public class Komik extends MediaCetak implements Diskon {
    private int chapter;
    private int volume;
    private String illustrator;

    public Komik() {}

    public Komik(String judul, double harga, int tahunTerbit, String penulis, int hal,
                 int chapter, int volume, String illustrator) {
        super(judul, harga, tahunTerbit, penulis, hal);
        this.chapter = chapter;
        this.volume = volume;
        this.illustrator = illustrator;
        
    }

    // Getter & Setter
    public int getChapter() { return chapter; }
    public void setChapter(int chapter) { this.chapter = chapter; }

    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }

    public String getIllustrator() { return illustrator; }
    public void setIllustrator(String illustrator) { this.illustrator = illustrator; }

    public double hitungDiskon() {
        return getHarga() * 0.80;
    }

    @Override
    public void display(int no) {
        System.out.printf("| %-3d | %-15s | %-7.0f | %-5d | %-20s | %-3d | %-7d | %-6d | %-15s |\n",
                no, getJudul(), getHarga(), getTahunTerbit(), getPenulis(),
                getHal(), chapter, volume, illustrator);
    }

    public void printHeader() {
        System.out.println("=============================================================================================================");
        System.out.printf("| %-3s | %-15s | %-7s | %-5s | %-20s | %-3s | %-7s | %-6s | %-15s |\n",
                "No", "Judul", "Harga", "Tahun", "Penulis", "Hal", "Chapter", "Volume", "Ilustrator");
        System.out.println("=============================================================================================================");
    }
}