package com.example.projectuas.controller;

import com.example.projectuas.entity.Novel;
import com.example.projectuas.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/novel")
public class NovelController {
    
    @Autowired
    private NovelService novelService;
    
    @GetMapping(value = {"", "/"})
    public String novelPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Novel> novelList = novelService.getAllNovel();
            model.addAttribute("novelList", novelList);
            model.addAttribute("novelInfo", new Novel());
            return "novel";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/{id}")
    public String novelGetRec(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Seller") != null) {
            model.addAttribute("logSeller", request.getSession().getAttribute("Seller"));
            List<Novel> novelList = novelService.getAllNovel();
            Novel novelRec = novelService.getNovelById(id);
            model.addAttribute("novelList", novelList);
            model.addAttribute("novelInfo", novelRec);
            return "novel";
        } else {
            request.getSession().setAttribute("pageBuyer", "B");
            return "redirect:/login";
        }
    }
    
    @PostMapping(value = {"/submit/", "/submit/{id}"}, params = "add")
    public String novelAdd(@ModelAttribute("novelInfo") Novel novelInfo) {
        novelService.saveNovel(novelInfo);
        return "redirect:/novel";
    }
    
    @PostMapping(value = "/submit/{id}", params = "edit")
    public String novelEdit(@ModelAttribute("novelInfo") Novel novelInfo, @PathVariable("id") Long id) {
        novelInfo.setId(id);
        novelService.saveNovel(novelInfo);
        return "redirect:/novel";
    }
    
    @PostMapping(value = "/submit/{id}", params = "delete")
    public String novelDelete(@PathVariable("id") Long id) {
        novelService.deleteNovel(id);
        return "redirect:/novel";
    }
}