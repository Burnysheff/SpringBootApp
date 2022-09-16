package com.boot.demo;

import com.boot.demo.repo.CourseUserRepository;
import com.boot.demo.repo.UserRepository;
import com.boot.demo.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceTest {
	private UserService service;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseUserRepository courseUserRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@BeforeEach
	public void set() {
		when(bCryptPasswordEncoder.encode("password")).thenReturn("encoded");
		this.service = new UserService(userRepository, courseUserRepository, bCryptPasswordEncoder);
		service.addUser("name", "password");
	}

	@Test
	public void checkEncode() {
		assertEquals(service.findUserByName("name").getPassword(), "encoded");
	}

	@Test
	public void checkCreated() {
		assertTrue(service.wasCreated("name"));
	}

	@Test
	public void checkNotCreated() {
		assertFalse(service.wasCreated("heh"));
	}

	@Test
	public void checkLoadByUsername() {
		assertEquals(service.loadUserByUsername("name").getPassword(), "encoded");
	}

	@Test
	public void checkAdding() {
		when(bCryptPasswordEncoder.encode("pass")).thenReturn("pass");
		service.addUser("nick", "pass");
		assertEquals(service.findUserByName("nick").getPassword(), "pass");
	}

	@Test
	public void checkFindById() {
		Long id = service.findUserByName("name").getId();
		assertEquals(service.findUserById(id).getName(), "name");
	}

	@Test
	public void checkFindByIdFalse() {
		service.addUser("nick", "pass");
		assertNull(service.findUserById(0L).getName(), "nick");
	}
}
