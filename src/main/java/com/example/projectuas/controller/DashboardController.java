package com.example.projectuas.controller;

import com.example.projectuas.entity.Penjual;
import com.example.projectuas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
    
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
    
    @Autowired
    private TransaksiService transaksiService;
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("/validateLogin")
    public String sellerLogin(Model model, @RequestParam(value = "email") String emailUser,
            @RequestParam(value = "password") String passUser, HttpServletRequest request) {
        Penjual S = penjualService.findSeller(emailUser);
        if (S != null && passUser.equals(S.getPassword())) {
            request.getSession().setAttribute("Seller", S);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Email atau password salah!");
            return "login";
        }
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") == null) {
            return "redirect:/login";
        }

        Penjual seller = (Penjual) request.getSession().getAttribute("Seller");
        
        int totalPembeli = pembeliService.getAllPembeli().size();
        int totalPenjual = penjualService.getAllPenjual().size();
        int totalKomik = komikService.getAllKomik().size();
        int totalNovel = novelService.getAllNovel().size();
        int totalMajalah = majalahService.getAllMajalah().size();
        int totalTransaksi = transaksiService.getAllTransaksi().size();

        model.addAttribute("totalPembeli", totalPembeli);
        model.addAttribute("totalPenjual", totalPenjual);
        model.addAttribute("totalKomik", totalKomik);
        model.addAttribute("totalNovel", totalNovel);
        model.addAttribute("totalMajalah", totalMajalah);
        model.addAttribute("totalTransaksi", totalTransaksi);
        model.addAttribute("logSeller", seller);
        
        return "dashboard";
    }
    
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/homepage";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/homepage";
    }
}