package com.lubchynsky.springbeapi.service;

import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.model.PetType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
    private static final List<PetModel> listOfPets = new ArrayList<>() {
        {
            add(new PetModel("Rex", PetType.DOG, 1));
            add(new PetModel("Linda", PetType.DOG, 3));
            add(new PetModel("Catty", PetType.CAT, 0));
        }
    };

    public void add(PetModel pet) {
        listOfPets.add(pet);
    }

    public List<PetModel> getAll() {
        return listOfPets;
    }

    public PetModel get(int id) {
        return listOfPets.get(id);
    }

    public List<PetModel> getSublist(int num) {
        return listOfPets.subList(0, num);
    }
}
