package org.ericeagan.vvorlds.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.repositories.UserRepository;
import org.ericeagan.vvorlds.security.CurrentUser;
import org.ericeagan.vvorlds.services.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class UserDetailsServiceTests {
	
	private static UserDetailsService uds;
	private static UserRepository ur;

	@BeforeAll
	static void setup() {
		ur = Mockito.mock(UserRepository.class);
		uds = new UserDetailsServiceImpl(ur);
	}
	
	@ParameterizedTest()
	@CsvSource({"test,test,true", "test,bad,false"})
	void testLoadUserByUsername(String username, String searchName, boolean success ) {
		User user = new User(username, "");
		UserDetails expected = new CurrentUser(user);
		Mockito.when(ur.getByUsername(Mockito.anyString())).thenReturn(
				null);
		Mockito.when(ur.getByUsername(username)).thenReturn(
				user);
		
		if (success) {
			UserDetails actual = uds.loadUserByUsername(searchName);
			assertEquals(expected.getUsername(), actual.getUsername());
		}else {
			Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
		        uds.loadUserByUsername(searchName);
		    });
			
			String expectedMessage = "User not found";
		    String actualMessage = exception.getMessage();
		    
		    assertEquals(expectedMessage, actualMessage);
		}
	}
}
