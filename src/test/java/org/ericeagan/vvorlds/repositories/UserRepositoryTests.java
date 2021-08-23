package org.ericeagan.vvorlds.repositories;

import static org.junit.Assert.assertEquals;

import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTests {
	
	private UserRepository ur;
	private User expected;
	
	@Autowired
	public UserRepositoryTests(UserRepository ur) {
		this.ur = ur;
	}
	
	@BeforeAll
	void setup() {
		User u = new User("John", "john1234");
		Account a = new Account();
		u.setAccount(a);
		a.setUser(u);
		expected = ur.save(u);
		
	}
	
	@AfterAll
	void clearSetup() {
		ur.delete(expected);
	}
	
	@Test
	void testGetById() {
		User actual = (User) ur.getById(expected.getId());
		assertEquals(expected.getId(), actual.getId());
	}
	
	@Test
	void testGetByUsername() {
		User actual = ur.getByUsername(expected.getUsername());
		assertEquals(expected, actual);
	}
}
