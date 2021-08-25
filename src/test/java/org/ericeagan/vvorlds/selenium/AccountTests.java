package org.ericeagan.vvorlds.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.services.UserService;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class AccountTests {
	private WebDriver driver;
	private UserService us;
	
	@Autowired
	public AccountTests(WebDriver driver, UserService us) {
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
	void testAccountPage() {
		// Opens the registration page for this web application
		driver.get("http://localhost:8080/");
		
		login("admin","admin");
		
		WebElement accountBtn = driver.findElement(By.cssSelector("#account"));
		accountBtn.click();
		
		assertEquals("admin's Account", driver.getTitle());
	}
	
	@Test
	void testAccountDisplay() {
		// Opens the registration page for this web application
		driver.get("http://localhost:8080/");
		
		login("admin","admin");
		
		WebElement accountBtn = driver.findElement(By.cssSelector("#account"));
		accountBtn.click();

		WebElement usernameBox = driver.findElement(By.cssSelector("#UN"));
		WebElement passwordBox = driver.findElement(By.cssSelector("#PW"));
		
		assertEquals("admin", usernameBox.getAttribute("value"));
		assertEquals("****", passwordBox.getAttribute("placeholder"));
	}
	
	@Test
	void testEditAccount() {
		// Opens the registration page for this web application
		driver.get("http://localhost:8080/");
		
		login("admin","admin");
		
		WebElement accountBtn = driver.findElement(By.cssSelector("#account"));
		accountBtn.click();

		WebElement firstnameBox = driver.findElement(By.cssSelector("#FN"));
		WebElement unlockBtn = driver.findElement(By.cssSelector("#SBM"));
		assertEquals("", firstnameBox.getAttribute("value"));
		
		unlockBtn.click();
		firstnameBox.sendKeys("Admin");
		WebElement lockBtn = driver.findElement(By.cssSelector("#NSBM"));
		lockBtn.click();

		firstnameBox = driver.findElement(By.cssSelector("#FN"));
		assertEquals("Admin", firstnameBox.getAttribute("value"));
		
		unlockBtn = driver.findElement(By.cssSelector("#SBM"));
		unlockBtn.click();
		firstnameBox.clear();
		lockBtn = driver.findElement(By.cssSelector("#NSBM"));
		lockBtn.click();
	}
	
	@Test
	void testDeleteAccount() {
		// Opens the registration page for this web application
		driver.get("http://localhost:8080/");
		
		User user = new User("test","test");
		Account account = new Account();
		user.setAccount(account);
		account.setUser(user);
		us.createUser(user);
		
		login("test","test");
		
		WebElement accountBtn = driver.findElement(By.cssSelector("#account"));
		accountBtn.click();

		WebElement unlockBtn = driver.findElement(By.cssSelector("#SBM"));
		unlockBtn.click();
		unlockBtn.click();
		driver.switchTo().alert().accept();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		assertNull(us.getByUsername("test"));
	}
	
	void login(String username, 
			   String password) {
		
		WebElement usernameInput = driver.findElement(By.cssSelector("#UN"));
		usernameInput.sendKeys(username);
		
		WebElement passwordInput = driver.findElement(By.cssSelector("#PW"));
		passwordInput.sendKeys(password);
		
		WebElement submit = driver.findElement(By.cssSelector("#SBM"));
		submit.click();
	}
}