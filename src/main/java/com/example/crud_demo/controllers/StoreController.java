package com.example.crud_demo.controllers;

import com.example.crud_demo.models.Store;
import com.example.crud_demo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public List<Store> getStores(){
        return storeRepository.findAll();
    }

    @PostMapping("/addstore")
    public Store addStore(@RequestBody Store store){ return storeRepository.save(store);}
}
