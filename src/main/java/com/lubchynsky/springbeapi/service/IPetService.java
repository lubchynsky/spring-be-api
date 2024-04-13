package com.lubchynsky.springbeapi.service;

import com.lubchynsky.springbeapi.model.PetModel;

import java.util.List;
import java.util.Optional;

public interface IPetService {
    PetModel save(PetModel pet);

    List<PetModel> getAll();

    Optional<PetModel> get(long id);

    List<PetModel> getSublist(int num);

    boolean remove(long id);

    boolean update(PetModel pet);
}
