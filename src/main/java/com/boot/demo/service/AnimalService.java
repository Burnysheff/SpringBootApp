package com.boot.demo.service;

import com.boot.demo.model.Animal;
import com.boot.demo.repos.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAllByOwnerId(Long id) {
        return animalRepository.findAllByOwnerId(id);
    }

    public boolean checkPresentById(Long id) {
        return animalRepository.findById(id).isPresent();
    }

    public Animal findById(Long id) {
        if (animalRepository.findById(id).isPresent()) {
            return animalRepository.findById(id).get();
        } else {
            return new Animal();
        }
    }

    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal);
    }
}
