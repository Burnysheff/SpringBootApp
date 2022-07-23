package com.boot.demo;

import com.boot.demo.repos.UserRepository;
import com.boot.demo.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserServiceTest {
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void contextLoads() {
		when(bCryptPasswordEncoder.encode("password")).thenReturn("encoded");
		UserService service = new UserService(userRepository, bCryptPasswordEncoder);
		service.addUser("name", "password");
		assertEquals(service.findUserByName("name").getPassword(), "encoded");
	}
}
