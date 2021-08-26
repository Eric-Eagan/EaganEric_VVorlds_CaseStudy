package org.ericeagan.vvorlds.selenium;

import static org.junit.Assert.assertEquals;

import org.ericeagan.vvorlds.exceptions.UserNotFoundException;
import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.services.UserService;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SpringBootTest
class RegistrationTests {
	private WebDriver driver;
	private UserService us;
	
	@Autowired
	public RegistrationTests(WebDriver driver, UserService us) throws UserNotFoundException {
		this.driver = driver;
		this.us = us;
		
		User u = us.getByUsername("admin");
		if (u == null) {
			u = new User("admin", "admin");
			Account a = new Account();
			u.setAccount(a);
			a.setUser(u);
			us.createUser(u);
		}
	}
	
	@Test
	void testRegistrationPage() {
		// Opens the registration page for this web application
		driver.get("http://localhost:8080/register");
		
		assertEquals("Register", driver.getTitle());
	}
	
	@ParameterizedTest
	@CsvSource({"t,test,test,Register",
				"test,t,t,Register",
				"test,test,tset,Register",
				"test,test,test,Login",
				"admin,admin,admin,Register"})
	void testRegistration(String username, 
						  String password,
						  String confPass,
						  String expected) throws UserNotFoundException {
		
		driver.get("http://localhost:8080/register");
		WebElement usernameInput = driver.findElement(By.cssSelector("#UN"));
		usernameInput.sendKeys(username);
		
		WebElement passwordInput = driver.findElement(By.cssSelector("#PW"));
		passwordInput.sendKeys(password);
		
		WebElement confPassInput = driver.findElement(By.cssSelector("#PWC"));
		confPassInput.sendKeys(confPass);
		
		WebElement submit = driver.findElement(By.cssSelector("#SBM"));
		submit.click();
		
		assertEquals(expected, driver.getTitle());
		
		if(expected.equals("Login")) {
			User temp = us.getByUsername(username);
			us.deleteUser(temp);
		}
	}
}
