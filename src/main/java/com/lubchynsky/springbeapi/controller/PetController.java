package com.lubchynsky.springbeapi.controller;


import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public void add(@RequestBody PetModel pet) {
        petService.add(pet);
    }

    /*
     * Used optional query parameter
     * http://localhost:8080/pets?n=2
     * */
    @GetMapping
    public List<PetModel> getAll(@RequestParam("n") Optional<Integer> num) {
        if (num.isPresent()) {
            return petService.getSublist(num.get());
        } else {
            return petService.getAll();
        }
    }

    /*
     * Used optional path parameter
     * http://localhost:8080/pets/1
     * */
    @GetMapping("/{id}")
    public PetModel getByPosition(@PathVariable("id") long id) {
        Optional<PetModel> pet = petService.get(id);
        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    @PutMapping
    public void update(@RequestBody PetModel pet){
        petService.update(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable("id") long id){
        petService.remove(id);
    }
}
