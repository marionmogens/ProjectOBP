package com.example.projectuas.controller;

import com.example.projectuas.entity.Penjual;
import com.example.projectuas.service.PenjualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/penjual")
public class PenjualController {
    
    @Autowired
    private PenjualService penjualService;
    
    @GetMapping(value = {"", "/"})
    public String penjualPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Penjual> penjualList = penjualService.getAllPenjual();
            model.addAttribute("penjualList", penjualList);
            model.addAttribute("penjualInfo", new Penjual());
            return "penjual";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/{id}")
    public String penjualGetRec(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Penjual> penjualList = penjualService.getAllPenjual();
            Penjual penjualRec = penjualService.getPenjualById(id);
            model.addAttribute("penjualList", penjualList);
            model.addAttribute("penjualInfo", penjualRec);
            return "penjual";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @PostMapping(value = {"/submit/", "/submit/{id}"}, params = "add")
    public String penjualAdd(@ModelAttribute("penjualInfo") Penjual penjualInfo) {
        penjualService.savePenjual(penjualInfo);
        return "redirect:/penjual";
    }
    
    @PostMapping(value = "/submit/{id}", params = "edit")
    public String penjualEdit(@ModelAttribute("penjualInfo") Penjual penjualInfo, @PathVariable("id") Long id) {
        penjualInfo.setId(id);
        penjualService.savePenjual(penjualInfo);
        return "redirect:/penjual";
    }
    
    @PostMapping(value = "/submit/{id}", params = "delete")
    public String penjualDelete(@PathVariable("id") Long id) {
        penjualService.deletePenjual(id);
        return "redirect:/penjual";
    }
}