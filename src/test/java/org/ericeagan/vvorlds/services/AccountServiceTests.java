package org.ericeagan.vvorlds.services;

import static org.junit.Assert.assertEquals;

import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.repositories.AccountRepository;
import org.ericeagan.vvorlds.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AccountServiceTests {
	
	private static AccountService as;
	private static AccountRepository ar;
	
	@BeforeAll
	static void setup() {
		ar = Mockito.mock(AccountRepository.class);
		as = new AccountServiceImpl(ar);
	}
	
	@Test
	void testSave() {
		Account expected = new Account();
		Mockito.when(ar.save(Mockito.any(Account.class))).thenReturn(
				expected);
		Account actual = as.save(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetById() {
		Account expected = new Account();
		Mockito.when(ar.getById(1)).thenReturn(
				expected);
		Account actual = as.getById(1);
		
		assertEquals(expected, actual);
	}
}
