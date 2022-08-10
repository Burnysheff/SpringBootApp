package com.boot.demo.repos;

import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
    List<Animal> findAllByOwner(User user);
}
