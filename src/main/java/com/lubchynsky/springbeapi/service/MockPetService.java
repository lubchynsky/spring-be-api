package com.lubchynsky.springbeapi.service;

import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.model.PetType;
import com.lubchynsky.springbeapi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Profile("test")
public class MockPetService implements IPetService {

    private static final PetModel pet = new PetModel(1L, "Mocked Rex", PetType.DOG, 1);

    @Override
    public void add(PetModel pet) {
        System.out.println("Added pet: " + pet);
    }

    @Override
    public List<PetModel> getAll() {
        return List.of(pet);
    }

    @Override
    public Optional<PetModel> get(long id) {
        return Optional.of(pet);
    }

    @Override
    public List<PetModel> getSublist(int num) {
        return List.of(pet);
    }

    @Override
    public void remove(long id) {
        System.out.println("Removed pet: " + id);
    }

    @Override
    public void update(PetModel pet) {
        System.out.println("Updated pet: " + pet);
    }
}
