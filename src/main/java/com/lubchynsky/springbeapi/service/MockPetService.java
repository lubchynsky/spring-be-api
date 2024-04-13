package com.lubchynsky.springbeapi.service;

import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.model.PetType;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("test")
@Primary
public class MockPetService implements IPetService {

    private static final PetModel pet = new PetModel(1L, "Mocked Rex", PetType.DOG, 1);

    @Override
    public PetModel save(PetModel pet) {
        System.out.println("Added pet: " + pet);
        return pet;
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
    public boolean remove(long id) {
        System.out.println("Removed pet: " + id);
        return true;
    }

    @Override
    public boolean update(PetModel pet) {
        System.out.println("Updated pet: " + pet);
        return true;
    }
}
