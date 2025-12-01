package com.example.projectuas.controller;

import com.example.projectuas.entity.*;
import com.example.projectuas.service.TransaksiService;
import com.example.projectuas.service.PembeliService;
import com.example.projectuas.service.PenjualService;
import com.example.projectuas.service.KomikService;
import com.example.projectuas.service.NovelService;
import com.example.projectuas.service.MajalahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/transaksi")
public class TransaksiController {
    
    @Autowired
    private TransaksiService transaksiService;
    
    @Autowired
    private PembeliService pembeliService;
    
    @Autowired
    private PenjualService penjualService;
    
    @Autowired
    private KomikService komikService;
    
    @Autowired
    private NovelService novelService;
    
    @Autowired
    private MajalahService majalahService;
    
    @GetMapping(value = {"", "/"})
    public String transaksiPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Transaksi> transaksiList = transaksiService.getAllTransaksi();
            model.addAttribute("transaksiList", transaksiList);
            model.addAttribute("transaksiInfo", new Transaksi());
            
            addDropdownData(model);
            return "transaksi";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/{id}")
    public String transaksiGetRec(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Transaksi> transaksiList = transaksiService.getAllTransaksi();
            Transaksi transaksiRec = transaksiService.getTransaksiById(id);
            model.addAttribute("transaksiList", transaksiList);
            model.addAttribute("transaksiInfo", transaksiRec);
            
            addDropdownData(model);
            return "transaksi";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }

    
    @PostMapping("/submit")
    public String transaksiSubmit(
        @RequestParam(value = "id", required = false) Long id, 
        @RequestParam("pembeli.id") Long pembeliId,
        @RequestParam("penjual.id") Long penjualId,
        @RequestParam("mediaCetak.id") Long mediaCetakId,
        @RequestParam("jumlah") Integer jumlah,
        @RequestParam("tanggal") String tanggalStr,
        HttpServletRequest request) {
    
    try {
        Transaksi transaksi;
        
        // Cek apakah ini EDIT atau CREATE
        if (id != null) {
            // EDIT: Ambil transaksi existing dari database
            transaksi = transaksiService.getTransaksiById(id);
            if (transaksi == null) {
                transaksi = new Transaksi();
            }
        } else {
            // CREATE: Buat transaksi baru
            transaksi = new Transaksi();
        }
        
        // Set field dasar
        transaksi.setJumlah(jumlah != null ? jumlah : 1);
        
        // Parse tanggal manual
        if (tanggalStr != null && !tanggalStr.isEmpty()) {
            LocalDate localDate = LocalDate.parse(tanggalStr);
            LocalDateTime tanggal = localDate.atStartOfDay();
            transaksi.setTanggal(tanggal);
        } else {
            transaksi.setTanggal(LocalDateTime.now());
        }
        
        // Ambil relasi objects
        Pembeli pembeli = pembeliService.getPembeliById(pembeliId);
        Penjual penjual = penjualService.getPenjualById(penjualId);
        MediaCetak mediaCetak = findMediaCetakById(mediaCetakId);
        
        // Set relasi ke transaksi
        transaksi.setPembeli(pembeli);
        transaksi.setPenjual(penjual);
        transaksi.setMediaCetak(mediaCetak);
        
        // Hitung total harga
        if (mediaCetak != null && pembeli != null && transaksi.getJumlah() != null) {
            Double totalHarga = hitungTotalHargaManual(mediaCetak, pembeli, transaksi.getJumlah());
            transaksi.setTotalHarga(totalHarga);
        }
        
        // Save transaksi (JPA akan handle UPDATE jika id sudah ada)
        Transaksi savedTransaksi = transaksiService.saveTransaksi(transaksi);
        
        // Set SaleID setelah save jika baru
        if (savedTransaksi.getSaleID() == null) {
            savedTransaksi.setSaleID("TRX" + savedTransaksi.getId());
            transaksiService.saveTransaksi(savedTransaksi);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return "redirect:/transaksi";
    }
    
    // TAMBAHKAN: Method untuk handle delete via POST (konsisten dengan controller lain)
    @PostMapping("/submit/{id}")
    public String transaksiDelete(@PathVariable("id") Long id) {
        transaksiService.deleteTransaksi(id);
        return "redirect:/transaksi";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteTransaksi(@PathVariable Long id) {
        transaksiService.deleteTransaksi(id);
        return "redirect:/transaksi";
    }
    
    private void addDropdownData(Model model) {
        model.addAttribute("pembeliList", pembeliService.getAllPembeli());
        model.addAttribute("penjualList", penjualService.getAllPenjual());
        model.addAttribute("komikList", komikService.getAllKomik());
        model.addAttribute("novelList", novelService.getAllNovel());
        model.addAttribute("majalahList", majalahService.getAllMajalah());
    }
    
    // Method untuk mencari media cetak berdasarkan ID
    private MediaCetak findMediaCetakById(Long id) {
        if (id == null) return null;
        
        Komik komik = komikService.getKomikById(id);
        if (komik != null) return komik;
        
        Novel novel = novelService.getNovelById(id);
        if (novel != null) return novel;
        
        Majalah majalah = majalahService.getMajalahById(id);
        if (majalah != null) return majalah;
        
        return null;
    }
    
    // Method hitung total harga - LOGIC BARU: hanya member dapat diskon
    private Double hitungTotalHargaManual(MediaCetak mediaCetak, Pembeli pembeli, Integer jumlah) {
    Double hargaDasar = mediaCetak.getHarga();
    Double hargaAkhir = hargaDasar;
    
    // Hanya beri diskon jika member DAN media implement Diskon
    if (pembeli.isMember() && mediaCetak instanceof Diskon) {
        hargaAkhir = ((Diskon) mediaCetak).hitungDiskon();  // ← Delegasi ke object
    }
    // Non-member bayar full price
    
    return hargaAkhir * jumlah;
    
    }
}