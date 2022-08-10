package com.boot.demo.service;

import com.boot.demo.model.Animal;
import com.boot.demo.model.AnimalUser;
import com.boot.demo.model.User;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.AnimalUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    AnimalRepository animalRepository;

    AnimalUserRepository animalUserRepository;

    public AnimalService(AnimalRepository animalRepository, AnimalUserRepository animalUserRepository) {
        this.animalRepository = animalRepository;
        this.animalUserRepository = animalUserRepository;
    }

    public List<Animal> findAllByOwnerId(User user) {
        return animalUserRepository.findAllByUser(user).stream().map(AnimalUser::getAnimal).collect(Collectors.toList());
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
