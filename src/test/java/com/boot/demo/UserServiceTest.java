package com.boot.demo;

import com.boot.demo.repos.UserRepository;
import com.boot.demo.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserServiceTest {
	private UserService service;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@BeforeEach
	public void set() {
		when(bCryptPasswordEncoder.encode("password")).thenReturn("encoded");
		this.service = new UserService(userRepository, bCryptPasswordEncoder);
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
	public void checkLoadByUsername() {
		assertEquals(service.loadUserByUsername("name").getPassword(), "encoded");
	}
}
