package com.example.projectuas.service;

import com.example.projectuas.entity.Pembeli;
import com.example.projectuas.repository.PembeliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PembeliService {
    
    @Autowired
    private PembeliRepository pembeliRepository;
    
    public List<Pembeli> getAllPembeli() {
        return pembeliRepository.findAll();
    }
    
    public Pembeli getPembeliById(Long id) {
        return pembeliRepository.findById(id).orElse(null);
    }
    
    public Pembeli savePembeli(Pembeli pembeli) {
        return pembeliRepository.save(pembeli);
    }
    
    public Pembeli updatePembeli(Long id, Pembeli pembeli) {
        pembeli.setId(id);
        return pembeliRepository.save(pembeli);
    }
    
    public void deletePembeli(Long id) {
        pembeliRepository.deleteById(id);
    }
}