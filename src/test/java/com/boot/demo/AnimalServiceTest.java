package com.boot.demo;

import com.boot.demo.repos.AnimalRepository;
import com.boot.demo.service.AnimalService;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class AnimalServiceTest {
    private AnimalService animalService;

    @Autowired
    AnimalRepository animalRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
