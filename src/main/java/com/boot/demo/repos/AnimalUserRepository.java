package com.boot.demo.repos;

import com.boot.demo.model.AnimalUser;
import com.boot.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalUserRepository  extends CrudRepository<AnimalUser, Long> {
    List<AnimalUser> findAllByUser(User user);
}
