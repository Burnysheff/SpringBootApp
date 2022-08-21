package com.boot.demo;

import com.boot.demo.model.Course;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebLayerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mainPage() {
        ResponseEntity<String> result = restTemplate.withBasicAuth("user", "password").getForEntity("/person/", String.class);
        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void postCourse() {
        Course course = new Course("name",
                new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "name");

        HttpEntity<Course> request = new HttpEntity<>(course);
        ResponseEntity<Course> result = restTemplate.withBasicAuth("user", "password").postForEntity("/person/newCourse", request, Course.class);
        assertEquals(result.getStatusCodeValue(), 302);
    }
}
