package com.example.projectuas.service;

import com.example.projectuas.entity.Majalah;
import com.example.projectuas.repository.MajalahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MajalahService {
    
    @Autowired
    private MajalahRepository majalahRepository;
    
    public List<Majalah> getAllMajalah() {
        return majalahRepository.findAll();
    }
    
    public Majalah getMajalahById(Long id) {
        return majalahRepository.findById(id).orElse(null);
    }
    
    public Majalah saveMajalah(Majalah majalah) {
        return majalahRepository.save(majalah);
    }
    
    public Majalah updateMajalah(Long id, Majalah majalah) {
        majalah.setId(id);
        return majalahRepository.save(majalah);
    }
    
    public void deleteMajalah(Long id) {
        majalahRepository.deleteById(id);
    }
}