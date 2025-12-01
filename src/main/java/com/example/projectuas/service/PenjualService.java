package com.example.projectuas.service;

import com.example.projectuas.entity.Penjual;
import com.example.projectuas.repository.PenjualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class PenjualService {
    
    @Autowired
    private PenjualRepository penjualRepository;
    
    public List<Penjual> getAllPenjual() {
        List<Penjual> result = penjualRepository.findAll();
        return result != null ? result : new ArrayList<>();
    }
    
    public Penjual getPenjualById(Long id) {
        return penjualRepository.findById(id).orElse(null);
    }
    
    public Penjual findSeller(String email) {
        List<Penjual> allPenjual = getAllPenjual();
        for (Penjual penjual : allPenjual) {
            if (email.equals(penjual.getEmail())) {
                return penjual;
            }
        }
        return null;
    }
    
    public Penjual savePenjual(Penjual penjual) {
        return penjualRepository.save(penjual);
    }
    
    public Penjual updatePenjual(Long id, Penjual penjual) {
        penjual.setId(id);
        return penjualRepository.save(penjual);
    }
    
    public void deletePenjual(Long id) {
        penjualRepository.deleteById(id);
    }
}