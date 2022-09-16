package com.boot.demo.service;

import com.boot.demo.model.Course;
import com.boot.demo.model.CourseUser;
import com.boot.demo.model.User;
import com.boot.demo.repo.CourseUserRepository;
import com.boot.demo.repo.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User current;

    UserRepository userRepository;

    CourseUserRepository courseUserRepository;

    public UserService(UserRepository repository, CourseUserRepository courseUserRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = repository;
        this.courseUserRepository = courseUserRepository;
    }

    public boolean wasCreated(String name) {
        return !userRepository.findAllByName(name).isEmpty();
    }

    public void addUser(String login, String password) {
        User user = new User(login, password);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User findUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            return new User();
        }
    }

    public void connectUserCourse(Course course) {
        courseUserRepository.save(new CourseUser(this.current, course));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByName(username) != null) {
            return userRepository.findByName(username);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
