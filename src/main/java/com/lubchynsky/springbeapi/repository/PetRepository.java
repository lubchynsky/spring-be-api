package com.lubchynsky.springbeapi.repository;


import com.lubchynsky.springbeapi.model.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetModel, Long> {
}
