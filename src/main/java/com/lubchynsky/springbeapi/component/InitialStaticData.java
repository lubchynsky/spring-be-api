package com.lubchynsky.springbeapi.component;

import com.lubchynsky.springbeapi.model.PetModel;
import com.lubchynsky.springbeapi.model.PetType;
import com.lubchynsky.springbeapi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitialStaticData {

    @Autowired
    private PetRepository petRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        petRepository.save(new PetModel("Rex", PetType.DOG, 1));
        petRepository.save(new PetModel("Catty", PetType.CAT, 2));
        petRepository.save(new PetModel("Linda", PetType.DOG, 5));
        petRepository.save(new PetModel("Rab", PetType.RABBIT, 0));
        petRepository.save(new PetModel("Abrek", PetType.DOG, 8));
    }
}
