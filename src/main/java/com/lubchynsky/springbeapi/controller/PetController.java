package com.lubchynsky.springbeapi.controller;


import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public void add(@RequestBody PetModel pet) {
        petService.add(pet);
    }

    @GetMapping
    public List<PetModel> getAll() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public PetModel getByPosition(@PathVariable("id") int id) {
        return petService.get(id);
    }
}
