package org.ericeagan.vvorlds.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public PasswordEncoder pswdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(pswdEncoder());
		return daoAuthenticationProvider;
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
		.antMatchers("/js/**", "/img/**", "/css/**", "/scripts/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF is disabled for simplification and demonstration.
		// Do not use this configuration for production.
		http.csrf().disable()
		// Allows all requests access to the /login URL
		.authorizeRequests().antMatchers("/login", "/register", "/registerNewUser").permitAll()
		// Restrict Admin page to "ADMIN" roles
		.antMatchers("/admin").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		// Redirect to access denied page if access not authorize for user
		.exceptionHandling().accessDeniedPage("/accessDenied")
		.and()
		// Specifies that we would like to use a custom form to login
		.formLogin().loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login?error=1").permitAll()
		
		// Specifies that any authenticated user can access all URLs
		//.and().authorizeRequests().anyRequest().authenticated()
		
		
		.and()
		// Upon logout, invalidate the session and clear authentication
		.logout().invalidateHttpSession(true).clearAuthentication(true)
		// Specifies Logout URL
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		// Forward to /logoutsuccess upon logout and allow all requests
		.logoutSuccessUrl("/logoutUser").permitAll();
	}
}
