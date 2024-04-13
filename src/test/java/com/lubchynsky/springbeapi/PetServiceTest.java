package com.lubchynsky.springbeapi;

import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.model.PetType;
import com.lubchynsky.springbeapi.repository.PetRepository;
import com.lubchynsky.springbeapi.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetServiceTest {

    @Autowired
    private PetService petService;

    @MockBean
    private PetRepository petRepository;

    @Test
    @DisplayName("Test find by id - found")
    void testFindByIdSuccess() {
        PetModel pet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        doReturn(Optional.of(pet)).when(petRepository).findById(1L);

        Optional<PetModel> found = petService.get(1);

        assertTrue(found.isPresent());
    }

    @Test
    @DisplayName("Test find by id - not found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(petRepository).findById(1L);

        Optional<PetModel> found = petService.get(1);

        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Test find all - found")
    void testFindAllSuccess() {
        PetModel pet1 = new PetModel(1L, "Rexio", PetType.DOG, 1);
        PetModel pet2 = new PetModel(2L, "Catty", PetType.CAT, 2);
        doReturn(List.of(pet1, pet2)).when(petRepository).findAll();

        List<PetModel> found = petService.getAll();

        assertTrue(found.size() == 2);
    }

    @Test
    @DisplayName("Test save")
    void testSaveSuccess() {
        PetModel pet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        doReturn(pet).when(petRepository).save(any());

        PetModel saved = petService.save(pet);

        assertEquals(pet, saved);
    }
}
