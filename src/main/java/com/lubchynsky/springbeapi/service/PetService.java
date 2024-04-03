package com.lubchynsky.springbeapi.service;

import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public void add(PetModel pet) {
        petRepository.save(pet);
    }

    public List<PetModel> getAll() {
        return petRepository.findAll();
    }

    public Optional<PetModel> get(long id) {
        return petRepository.findById(id);
    }

    public List<PetModel> getSublist(int num) {
        return petRepository.findAll().subList(0, num);
    }

    public void remove(long id) {
        petRepository.deleteById(id);
    }

    public void update(PetModel pet) {
        petRepository.save(pet);
    }
}
