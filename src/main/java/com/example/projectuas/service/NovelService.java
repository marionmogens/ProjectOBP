package com.example.projectuas.service;

import com.example.projectuas.entity.Novel;
import com.example.projectuas.repository.NovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NovelService {
    
    @Autowired
    private NovelRepository novelRepository;
    
    public List<Novel> getAllNovel() {
        return novelRepository.findAll();
    }
    
    public Novel getNovelById(Long id) {
        return novelRepository.findById(id).orElse(null);
    }
    
    public Novel saveNovel(Novel novel) {
        return novelRepository.save(novel);
    }
    
    public Novel updateNovel(Long id, Novel novel) {
        novel.setId(id);
        return novelRepository.save(novel);
    }
    
    public void deleteNovel(Long id) {
        novelRepository.deleteById(id);
    }
}