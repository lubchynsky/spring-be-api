package com.lubchynsky.springbeapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.model.PetType;
import com.lubchynsky.springbeapi.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {
    @MockBean
    private PetService petService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET - found")
    void testGetByIdFound() throws Exception {
        PetModel pet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        doReturn(Optional.of(pet)).when(petService).get(1L);

        mockMvc.perform(get("/pets/{id}", 1L))
                // Response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/pets/1"))
                // Body
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Rexio")))
                .andExpect(jsonPath("$.type", is("DOG")))
                .andExpect(jsonPath("$.age", is(1)));
    }

    @Test
    @DisplayName("GET - not found")
    void testGetByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(petService).get(1L);

        mockMvc.perform(get("/pets/{id}", 1L))
                // Response code
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST - created")
    void testPost() throws Exception {
        PetModel pet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        PetModel createdPet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        doReturn(createdPet).when(petService).save(any());

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pet)))

                // Response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/pets/1"))
                // Body
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Rexio")))
                .andExpect(jsonPath("$.type", is("DOG")))
                .andExpect(jsonPath("$.age", is(1)));
    }

    @Test
    @DisplayName("PUT - updated")
    void testPutUpdated() throws Exception {
        PetModel pet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        PetModel createdPet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        doReturn(Optional.of(createdPet)).when(petService).get(1);
        doReturn(true).when(petService).update(any());

        mockMvc.perform(put("/pets/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pet)))

                // Response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/pets/1"))
                // Body
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Rexio")))
                .andExpect(jsonPath("$.type", is("DOG")))
                .andExpect(jsonPath("$.age", is(1)));
    }

    @Test
    @DisplayName("PUT - not found")
    void testPutNotFound() throws Exception {
        PetModel pet = new PetModel(1L, "Rexio", PetType.DOG, 1);

        doReturn(Optional.empty()).when(petService).get(1);

        mockMvc.perform(put("/pets/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pet)))

                // Response code and content type
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE - deleted")
    void testDeleteSuccess() throws Exception {

        PetModel createdPet = new PetModel(1L, "Rexio", PetType.DOG, 1);
        doReturn(Optional.of(createdPet)).when(petService).get(1L);
        doReturn(true).when(petService).remove(1);

        mockMvc.perform(delete("/pets/{id}", 1L))

                // Response code and content type
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE - not found")
    void testDeleteNotFound() throws Exception {

        doReturn(Optional.empty()).when(petService).get(1L);

        mockMvc.perform(delete("/pets/{id}", 1L))

                // Response code and content type
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
