package com.example.projectuas.controller;

import com.example.projectuas.entity.Majalah;
import com.example.projectuas.service.MajalahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/majalah")
public class MajalahController {
    
    @Autowired
    private MajalahService majalahService;
    
    @GetMapping(value = {"", "/"})
    public String majalahPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Majalah> majalahList = majalahService.getAllMajalah();
            model.addAttribute("majalahList", majalahList);
            model.addAttribute("majalahInfo", new Majalah());
            return "majalah";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/{id}")
    public String majalahGetRec(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Majalah> majalahList = majalahService.getAllMajalah();
            Majalah majalahRec = majalahService.getMajalahById(id);
            model.addAttribute("majalahList", majalahList);
            model.addAttribute("majalahInfo", majalahRec);
            return "majalah";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @PostMapping(value = {"/submit/", "/submit/{id}"}, params = "add")
    public String majalahAdd(@ModelAttribute("majalahInfo") Majalah majalahInfo) {
        majalahService.saveMajalah(majalahInfo);
        return "redirect:/majalah";
    }
    
    @PostMapping(value = "/submit/{id}", params = "edit")
    public String majalahEdit(@ModelAttribute("majalahInfo") Majalah majalahInfo, @PathVariable("id") Long id) {
        majalahInfo.setId(id);
        majalahService.saveMajalah(majalahInfo);
        return "redirect:/majalah";
    }
    
    @PostMapping(value = "/submit/{id}", params = "delete")
    public String majalahDelete(@PathVariable("id") Long id) {
        majalahService.deleteMajalah(id);
        return "redirect:/majalah";
    }
}