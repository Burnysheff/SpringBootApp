package com.boot.demo.repos;

import com.boot.demo.model.Animal;
import com.boot.demo.model.AnimalReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalReviewRepository extends CrudRepository<AnimalReview, Long> {
    List<AnimalReview> getAllByAnimal(Optional<Animal> animal);
}
