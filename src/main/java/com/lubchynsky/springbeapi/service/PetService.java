package com.lubchynsky.springbeapi.service;

import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Profile("!test")
public class PetService implements IPetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public void add(PetModel pet) {
        petRepository.save(pet);
    }

    @Override
    public List<PetModel> getAll() {
        return petRepository.findAll();
    }

    @Override
    public Optional<PetModel> get(long id) {
        return petRepository.findById(id);
    }

    @Override
    public List<PetModel> getSublist(int num) {
        return petRepository.findAll().subList(0, num);
    }

    @Override
    public void remove(long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void update(PetModel pet) {
        petRepository.save(pet);
    }
}
