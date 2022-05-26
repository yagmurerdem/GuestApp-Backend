package com.project.yagmurquestapp.controllers;

import com.project.yagmurquestapp.entities.Kisi;
import com.project.yagmurquestapp.services.KisiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kisi")
public class KisiController {

    private KisiService kisiService;

    public KisiController(KisiService kisiService) {
        this.kisiService = kisiService;
    }

    @GetMapping
    public List<Kisi> getAllKisi(){
        return kisiService.getAllKisi();
    }

    @PostMapping
    public Kisi createKisi(@RequestBody Kisi newKisi) {
        return kisiService.saveOneKisi(newKisi);
    }

    @GetMapping("/{userId}")
    public Kisi getOneKisi(@PathVariable Long userId) {
        //custom exception
        return kisiService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public Kisi updateOneKisi(@PathVariable Long userId, @RequestBody Kisi newKisi) {
        return kisiService.updateOneKisi(userId, newKisi);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneKisi(@PathVariable Long userId) {
        kisiService.deleteById(userId);
    }
}