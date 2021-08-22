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
class AccountRepositoryTests {
	
	private AccountRepository ar;
	private Account expected;
	
	@Autowired
	public AccountRepositoryTests(AccountRepository ar) {
		this.ar = ar;
	}
	
	@BeforeAll
	void setup() {
		User u = new User("John", "john1234");
		Account a = new Account();
		u.setAccount(a);
		a.setUser(u);
		expected = ar.save(a);
	}
	
	@AfterAll
	void clearSetup() {
		ar.delete(expected);
	}
	
	@Test
	void testGetById() {
		Account actual = (Account) ar.getById(expected.getUserId());
		assertEquals(expected.getUserId(), actual.getUserId());
	}
}
