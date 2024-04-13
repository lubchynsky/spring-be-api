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
    public PetModel save(PetModel pet) {
        return petRepository.save(pet);
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
    public boolean remove(long id) {
        petRepository.deleteById(id);
        return petRepository.findById(id).isEmpty();
    }

    @Override
    public boolean update(PetModel pet) {
        petRepository.save(pet);
        Optional<PetModel> updatedPet = petRepository.findById(pet.getId());
        return updatedPet.map(p -> p.equals(pet)).orElse(false);
    }
}
