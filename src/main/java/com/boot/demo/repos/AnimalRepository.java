package com.boot.demo.repos;

import com.boot.demo.model.Animal;
import com.boot.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
    List<Animal> findAllByOwnerId(Long ownerId);
}
