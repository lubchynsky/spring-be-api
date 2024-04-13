package com.lubchynsky.springbeapi.controller;


import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.service.IPetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final IPetService iPetService;

    public PetController(IPetService iPetService) {
        this.iPetService = iPetService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PetModel pet) {
        PetModel newPet = iPetService.save(pet);
        try {
            return ResponseEntity
                    .created(new URI("/pets/" + newPet.getId()))
                    .body(newPet);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * Used optional query parameter
     * http://localhost:8080/pets?n=2
     * */
    @GetMapping
    public Iterable<PetModel> getAll(@RequestParam("n") Optional<Integer> num) {
        if (num.isPresent()) {
            return iPetService.getSublist(num.get());
        } else {
            return iPetService.getAll();
        }
    }

    /*
     * Used optional path parameter
     * http://localhost:8080/pets/1
     * */
    @GetMapping("/{id}")
    public ResponseEntity<?> getByPosition(@PathVariable long id) {
        return iPetService.get(id)
                .map(pet -> {
                            try {
                                return ResponseEntity
                                        .ok()
                                        .location(new URI("/pets/" + pet.getId()))
                                        .body(pet);
                            } catch (Throwable e) {
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                            }
                        }
                ).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PetModel pet, @PathVariable long id) {
        Optional<PetModel> existingPet = iPetService.get(id);
        return existingPet.map(
                p -> {
                    p.setName(pet.getName());
                    p.setType(pet.getType());
                    p.setAge(pet.getAge());

                    try {
                        if (iPetService.update(p)) {
                            return ResponseEntity
                                    .ok()
                                    .location(new URI("/pets/" + p.getId()))
                                    .body(p);
                        } else {
                            return ResponseEntity.notFound().build();
                        }
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable long id) {
        Optional<PetModel> existingPet = iPetService.get(id);

        return existingPet.map(
                p -> {
                    if (iPetService.remove(p.getId())) {
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
