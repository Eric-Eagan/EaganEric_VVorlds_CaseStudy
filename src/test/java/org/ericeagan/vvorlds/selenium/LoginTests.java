package org.ericeagan.vvorlds.selenium;

import static org.junit.Assert.assertEquals;

import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginTests {
	private WebDriver driver;
	private UserService us;

	@Autowired
	public LoginTests(WebDriver driver, UserService us) {
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
	void testLoginPage() {
		// Opens the registration page for this web application
		driver.get("http://localhost:8080/");
		
		assertEquals("Login", driver.getTitle());
	}
	
	@ParameterizedTest
	@CsvSource({"admin,a,Login",
				"a,admin,Login",
				"admin,admin,VVorlds"})
	void testLogin(String username, 
				   String password,
				   String expected) {
		
		driver.get("http://localhost:8080/");
		WebElement usernameInput = driver.findElement(By.cssSelector("#UN"));
		usernameInput.sendKeys(username);
		
		WebElement passwordInput = driver.findElement(By.cssSelector("#PW"));
		passwordInput.sendKeys(password);
		
		WebElement submit = driver.findElement(By.cssSelector("#SBM"));
		submit.click();
		
		assertEquals(expected, driver.getTitle());
		us.getByUsername(username);
	}
}
