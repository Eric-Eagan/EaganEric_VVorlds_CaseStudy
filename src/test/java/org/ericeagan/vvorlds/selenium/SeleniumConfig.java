package org.ericeagan.vvorlds.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SeleniumConfig {
	
	@Bean
	public WebDriver getWebDriver(Environment env) {
		System.setProperty("webdriver.gecko.driver", env.getProperty("selenium.path"));
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
}
