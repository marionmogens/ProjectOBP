package com.example.projectuas.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String saleID;
    private LocalDateTime tanggal;
    private Integer jumlah;
    private Double totalHarga;
    
    @ManyToOne
    @JoinColumn(name = "pembeli_id")
    private Pembeli pembeli;
    
    @ManyToOne
    @JoinColumn(name = "penjual_id")
    private Penjual penjual;
    
    @ManyToOne
    @JoinColumn(name = "media_id")
    private MediaCetak mediaCetak; 
    
    public Transaksi() {
        this.tanggal = LocalDateTime.now();
        // jumlah, totalHarga, mediaCetak dibiarkan null
    }
    
    public Transaksi(Pembeli pembeli, Penjual penjual, MediaCetak mediaCetak, Integer jumlah) {
        this();
        this.pembeli = pembeli;
        this.penjual = penjual;
        this.mediaCetak = mediaCetak;
        this.jumlah = jumlah;
        this.totalHarga = hitungTotalHarga();
    }
    
    // Generate SaleID
    public String getSaleID() { 
        if (saleID == null && id != null) {
            saleID = "TRX" + id;
        }
        return saleID; 
    }
    
    // Method hitung total dengan diskon - VERSI PERBAIKAN
    public Double hitungTotalHarga() {
        if (mediaCetak == null || pembeli == null || jumlah == null) {
            return null; // Return null jika data tidak lengkap
        }
        
        Double hargaMedia = mediaCetak.getHarga();
        Double hargaFinal = hargaMedia;
        
        // Member dapat diskon sesuai jenis media, non-member bayar full
        if (pembeli.isMember() && mediaCetak instanceof Diskon) {
            hargaFinal = ((Diskon) mediaCetak).hitungDiskon();
        }
        
        return hargaFinal * jumlah;
    }
    
    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getTanggal() { return tanggal; }
    public void setTanggal(LocalDateTime tanggal) { this.tanggal = tanggal; }
    
    public Integer getJumlah() { return jumlah; }
    public void setJumlah(Integer jumlah) { this.jumlah = jumlah; }
    
    public Double getTotalHarga() { 
        return totalHarga; // Biarkan return apa adanya
    }

    public void setSaleID(String saleID) { this.saleID = saleID; }

    public void setTotalHarga(Double totalHarga) { 
        this.totalHarga = totalHarga; 
    }
    
    public Pembeli getPembeli() { return pembeli; }
    public void setPembeli(Pembeli pembeli) { this.pembeli = pembeli; }
    
    public Penjual getPenjual() { return penjual; }
    public void setPenjual(Penjual penjual) { this.penjual = penjual; }
    
    public MediaCetak getMediaCetak() { return mediaCetak; }
    public void setMediaCetak(MediaCetak mediaCetak) { this.mediaCetak = mediaCetak; }
    
    // Method cetak struk
    public String cetakStruk() {
        if (pembeli == null || penjual == null) {
            return "Data transaksi tidak lengkap";
        }
        
        return "\n========== STRUK TRANSAKSI ==========\n" +
               "ID Transaksi : " + getSaleID() + "\n" +
               "Tanggal      : " + tanggal + "\n" +
               "Pembeli      : " + pembeli.getNama() + 
               (pembeli.isMember() ? " (Member)" : "") + "\n" +
               "Penjual      : " + penjual.getNama() + "\n" +
               "Jumlah       : " + jumlah + "\n" +
               "Total Harga  : Rp " + (totalHarga != null ? totalHarga : "Belum dihitung") + "\n" +
               "=====================================\n";
    }
}