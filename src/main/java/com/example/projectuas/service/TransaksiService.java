package com.example.projectuas.service;

import com.example.projectuas.entity.Transaksi;
import com.example.projectuas.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransaksiService {
    
    @Autowired
    private TransaksiRepository transaksiRepository;
    
    public List<Transaksi> getAllTransaksi() {
        return transaksiRepository.findAll();
    }
    
    public Transaksi getTransaksiById(Long id) {
        return transaksiRepository.findById(id).orElse(null);
    }
    
    public Transaksi saveTransaksi(Transaksi transaksi) {
        return transaksiRepository.save(transaksi);
    }
    
    public Transaksi updateTransaksi(Long id, Transaksi transaksi) {
        transaksi.setId(id);
        return transaksiRepository.save(transaksi);
    }
    
    public void deleteTransaksi(Long id) {
        transaksiRepository.deleteById(id);
    }

    
}