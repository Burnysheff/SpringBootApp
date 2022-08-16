package com.boot.demo.repos;

import com.boot.demo.model.Animal;
import com.boot.demo.model.AnimalFeed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalFeedRepository extends CrudRepository<AnimalFeed, Long> {
    List<AnimalFeed> getAllAnimalFeedByAnimal(Animal animal);

    void deleteAnimalFeedByAnimal(Animal animal);
}
