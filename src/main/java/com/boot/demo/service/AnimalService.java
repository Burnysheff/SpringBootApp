package com.boot.demo.service;

import com.boot.demo.model.*;
import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.repos.AnimalReviewRepository;
import com.boot.demo.repos.AnimalUserRepository;
import com.boot.demo.repos.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    AnimalRepository animalRepository;

    AnimalUserRepository animalUserRepository;

    AnimalReviewRepository animalReviewRepository;

    ReviewRepository reviewRepository;

    public AnimalService(AnimalRepository animalRepository, AnimalUserRepository animalUserRepository,
                         AnimalReviewRepository animalReviewRepository, ReviewRepository reviewRepository) {
        this.animalRepository = animalRepository;
        this.animalUserRepository = animalUserRepository;
        this.animalReviewRepository = animalReviewRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Animal> findAllByOwner(User user) {
        return animalUserRepository.findAllByUser(user).stream().map(AnimalUser::getAnimal).collect(Collectors.toList());
    }

    public Animal getAnimalById(Long id) {
        if (animalRepository.findById(id).isEmpty()) {
            return null;
        }
        return animalRepository.findById(id).get();
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

    public List<Review> getAllReviewById(Long id) {
        return animalReviewRepository.getAllByAnimal(animalRepository.findById(id)).stream().map(AnimalReview::getReview).collect(Collectors.toList());
    }

    public void addReview(Review review) {
        this.reviewRepository.save(review);
    }

    public void addAnimalReview(Review review, Animal animal) {
        this.animalReviewRepository.save(new AnimalReview(animal, review));
    }

    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal);
    }
}
