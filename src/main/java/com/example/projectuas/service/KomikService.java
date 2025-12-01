package com.example.projectuas.service;

import com.example.projectuas.entity.Komik;
import com.example.projectuas.repository.KomikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KomikService {
    
    @Autowired
    private KomikRepository komikRepository;
    
    public List<Komik> getAllKomik() {
        return komikRepository.findAll();
    }
    
    public Komik getKomikById(Long id) {
        return komikRepository.findById(id).orElse(null);
    }
    
    public Komik saveKomik(Komik komik) {
        return komikRepository.save(komik);
    }
    
    public Komik updateKomik(Long id, Komik komik) {
        komik.setId(id);
        return komikRepository.save(komik);
    }
    
    public void deleteKomik(Long id) {
        komikRepository.deleteById(id);
    }
}