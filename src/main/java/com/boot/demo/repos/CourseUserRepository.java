package com.boot.demo.repos;

import com.boot.demo.model.CourseUser;
import com.boot.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseUserRepository  extends CrudRepository<CourseUser, Long> {
    List<CourseUser> findAllByUser(User user);
}
