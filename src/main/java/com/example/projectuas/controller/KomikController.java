package com.example.projectuas.controller;

import com.example.projectuas.entity.Komik;
import com.example.projectuas.service.KomikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/komik")
public class KomikController {
    
    @Autowired
    private KomikService komikService;
    
    @GetMapping(value = {"", "/"})
    public String komikPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Komik> komikList = komikService.getAllKomik();
            model.addAttribute("komikList", komikList);
            model.addAttribute("komikInfo", new Komik());
            return "komik";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/{id}")
    public String komikGetRec(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Komik> komikList = komikService.getAllKomik();
            Komik komikRec = komikService.getKomikById(id);
            model.addAttribute("komikList", komikList);
            model.addAttribute("komikInfo", komikRec);
            return "komik";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @PostMapping(value = {"/submit/", "/submit/{id}"}, params = "add")
    public String komikAdd(@ModelAttribute("komikInfo") Komik komikInfo) {
        komikService.saveKomik(komikInfo);
        return "redirect:/komik";
    }
    
    @PostMapping(value = "/submit/{id}", params = "edit")
    public String komikEdit(@ModelAttribute("komikInfo") Komik komikInfo, @PathVariable("id") Long id) {
        komikInfo.setId(id);
        komikService.saveKomik(komikInfo);
        return "redirect:/komik";
    }
    
    @PostMapping(value = "/submit/{id}", params = "delete")
    public String komikDelete(@PathVariable("id") Long id) {
        komikService.deleteKomik(id);
        return "redirect:/komik";
    }
}