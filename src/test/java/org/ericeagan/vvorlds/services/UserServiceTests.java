package org.ericeagan.vvorlds.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.repositories.UserRepository;
import org.ericeagan.vvorlds.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTests {
	
	private static UserService us;
	private static UserRepository ur;
	private static PasswordEncoder pe;
	
	@BeforeAll
	static void setup() {
		ur = Mockito.mock(UserRepository.class);
		pe = Mockito.mock(BCryptPasswordEncoder.class);
		us = new UserServiceImpl(ur, pe);
	}
	
	@Test
	void testSave() {
		User expected = new User("Test", "testPass");
		Mockito.when(ur.save(Mockito.any(User.class))).then(
				returnsFirstArg());
		User actual = us.save(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCreateUser() {
		User expected = new User("Test", "testPass");
		User actual = new User("Test", "testPass");
		Mockito.when(ur.save(Mockito.any(User.class))).then(
				returnsFirstArg());
		Mockito.when(pe.encode(Mockito.anyString())).thenReturn(
				"1234");
		actual = us.createUser(actual);
		
		assertNotEquals(expected.getPassword(), actual.getPassword());
	}
	
	@Test
	void testGetById() {
		User expected = new User("Test", "testPass");
		Mockito.when(ur.getById(1)).thenReturn(
				expected);
		User actual = us.getById(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetByUsername() {
		User expected = new User("Test", "testPass");
		Mockito.when(ur.getByUsername("Test")).thenReturn(
				expected);
		User actual = us.getByUsername(expected.getUsername());
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testValidatePassword() {
		User user = new User("Test", "testpass");
		Mockito.when(pe.matches("testpass", "testpass")).thenReturn(
				true);
		boolean actual = us.validatePassword(user, "testpass");
		
		assertTrue(actual);
	}
}