package com.garbuz.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@ConfigurationProperties("security")
public class SecurityConfig  {
	
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	/**
	 * Loads the value of security.admin-user=admin
	 */
	private String adminUser;
	/**
	 * Loads the value of security.admin-password=password
	 */
	private String adminPassword;
	/**
	 * Loads the value of security.user-user
	 */
	private String userUser;
	/**
	 * Loads the value of security.user-password
	 */
	private String userPassword;

	public String getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getUserUser() {
		return userUser;
	}
	public void setUserUser(String userUser) {
		this.userUser = userUser;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Bean
	protected SecurityFilterChain  securityFilterChain(final HttpSecurity http) throws Exception {
		http.authorizeRequests().requestMatchers("/admin/**", "/api/admin/**").hasRole(ROLE_ADMIN);
		http.formLogin().permitAll();
		http.logout().permitAll();
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username(getUserUser())
				.password(getUserPassword())
				.roles(ROLE_USER)
				.build();
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username(getAdminUser())
				.password(getAdminPassword())
				.roles(ROLE_USER, ROLE_ADMIN)
				.build();

		// This is for demo purposes only. For production implementation need to switch
		// to JdbcUserDetailsManager 
		// For more information please visit
		//
		// https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html#servlet-authentication-jdbc-datasource
		//
		UserDetailsService userService = new InMemoryUserDetailsManager(user, admin);
		
		return userService;
	}

}
