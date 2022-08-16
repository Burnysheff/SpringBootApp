package com.boot.demo.service;

import com.boot.demo.dto.FeedForAnimal;
import com.boot.demo.model.*;
import com.boot.demo.repos.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    AnimalRepository animalRepository;

    AnimalUserRepository animalUserRepository;

    AnimalReviewRepository animalReviewRepository;

    ReviewRepository reviewRepository;

    AnimalFeedRepository animalFeedRepository;

    FeedRepository feedRepository;

    public AnimalService(AnimalRepository animalRepository, AnimalUserRepository animalUserRepository,
                         AnimalReviewRepository animalReviewRepository, ReviewRepository reviewRepository,
                         AnimalFeedRepository animalFeedRepository, FeedRepository feedRepository) {
        this.animalRepository = animalRepository;
        this.animalUserRepository = animalUserRepository;
        this.animalReviewRepository = animalReviewRepository;
        this.reviewRepository = reviewRepository;
        this.animalFeedRepository = animalFeedRepository;
        this.feedRepository = feedRepository;
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

    public void saveFeeds(Animal animal, FeedForAnimal feed) {
        if (feed.isMeat()) {
            Long meatId = (feedRepository.findFeedByFood("meat")).getId();
            animalFeedRepository.save(new AnimalFeed(animal, meatId));
        }
        if (feed.isFruit()) {
            Long fruitId = (feedRepository.findFeedByFood("fruits")).getId();
            animalFeedRepository.save(new AnimalFeed(animal, fruitId));
        }
        if (feed.isVeges()) {
            Long vegesId = (feedRepository.findFeedByFood("veges")).getId();
            animalFeedRepository.save(new AnimalFeed(animal, vegesId));
        }
    }

    public List<String> getFeeds(Animal animal) {
        List<AnimalFeed> animalFeeds = animalFeedRepository.getAllAnimalFeedByAnimal(animal);
        List<Long> keys = animalFeeds.stream().map(AnimalFeed::getFeedId).toList();
        List<String> feeds = new ArrayList<>();
        for (Long id : keys) {
            feeds.add(feedRepository.findFeedById(id).getFood());
        }
        return feeds;
    }

    @Transactional
    public void changeFeeds(Animal animal, FeedForAnimal feedForAnimal) {
        animalFeedRepository.deleteAnimalFeedByAnimal(animal);
        this.saveFeeds(animal, feedForAnimal);
    }
}
