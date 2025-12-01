package com.example.projectuas.controller;

import com.example.projectuas.entity.Pembeli;
import com.example.projectuas.service.PembeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pembeli")
public class PembeliController {
    
    @Autowired
    private PembeliService pembeliService;
    
    @GetMapping(value = {"", "/"})
    public String pembeliPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Pembeli> pembeliList = pembeliService.getAllPembeli();
            model.addAttribute("pembeliList", pembeliList);
            model.addAttribute("pembeliInfo", new Pembeli());
            return "pembeli";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/{id}")
    public String pembeliGetRec(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Pembeli> pembeliList = pembeliService.getAllPembeli();
            Pembeli pembeliRec = pembeliService.getPembeliById(id);
            model.addAttribute("pembeliList", pembeliList);
            model.addAttribute("pembeliInfo", pembeliRec);
            return "pembeli";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @PostMapping(value = {"/submit/", "/submit/{id}"}, params = "add")
    public String pembeliAdd(@ModelAttribute("pembeliInfo") Pembeli pembeliInfo) {
        pembeliService.savePembeli(pembeliInfo);
        return "redirect:/pembeli";
    }
    
    @PostMapping(value = "/submit/{id}", params = "edit")
    public String pembeliEdit(@ModelAttribute("pembeliInfo") Pembeli pembeliInfo, @PathVariable("id") Long id) {
        pembeliInfo.setId(id);
        pembeliService.savePembeli(pembeliInfo);
        return "redirect:/pembeli";
    }
    
    @PostMapping(value = "/submit/{id}", params = "delete")
    public String pembeliDelete(@PathVariable("id") Long id) {
        pembeliService.deletePembeli(id);
        return "redirect:/pembeli";
    }
}