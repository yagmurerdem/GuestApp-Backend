package com.project.yagmurquestapp.services;

import com.project.yagmurquestapp.entities.Kisi;
import com.project.yagmurquestapp.repos.KisiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KisiService {

    KisiRepository kisiRepository;

    public KisiService(KisiRepository kisiRepository) {
        this.kisiRepository = kisiRepository;
    }

    public List<Kisi> getAllKisi() {
        return kisiRepository.findAll();
    }

    public Kisi saveOneKisi(Kisi newKisi) {
        return kisiRepository.save(newKisi);
    }

    public Kisi getOneUserById(Long userId) {
        return kisiRepository.findById(userId).orElse(null);
    }

    public Kisi updateOneKisi(Long userId, Kisi newKisi) {
        Optional<Kisi> kisi = kisiRepository.findById(userId);
        if(kisi.isPresent()) {
            Kisi foundKisi = kisi.get();
            foundKisi.setUserName(newKisi.getUserName());
            foundKisi.setPassword(newKisi.getPassword());
            kisiRepository.save(foundKisi);
            return foundKisi;
        }else
            return null;
    }

    public void deleteById(Long userId) {
        kisiRepository.deleteById(userId);
    }

    public Kisi getOneKisiByUserName(String userName) {
        return kisiRepository.findByUserName(userName);
    }


}
