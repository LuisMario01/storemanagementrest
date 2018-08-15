package com.storemanagement.storemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.storemanagement.storemanagement.controller.MainController;
import com.storemanagement.storemanagement.service.PersonRepositoryService;
import com.storemanagement.storemanagement.service.ProductRepositoryService;
import com.storemanagement.storemanagement.service.UserDetailsServiceImpl;

@ComponentScan
@SpringBootApplication
public class StoremanagementApplication {
	
	@Autowired
	public ProductRepositoryService prs;
	
	@Autowired
	public PersonRepositoryService pers;

	public static void main(String[] args) {
		SpringApplication.run(StoremanagementApplication.class, args);
	}
	
	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
	    @Override
	    public void run(String...args) throws Exception {
	    	pers.loadData();
	    	prs.loadData();
	    }
	}
	
	
	// Security configuration
	@Configuration
	@ComponentScan(basePackageClasses = UserDetailsServiceImpl.class)
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
		  
		  
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// Allowing h2 console to show for test purposes
			http.headers().frameOptions().sameOrigin();
			http.httpBasic().
					and().
					authorizeRequests().//
					antMatchers("/store").permitAll().//
					// Listing methods
					antMatchers(HttpMethod.GET, "/store/products/**").permitAll().//
					// Adding new product
					antMatchers(HttpMethod.POST, "/store/products/").hasAnyRole("ADMIN").//
					// Deleting product
					antMatchers(HttpMethod.DELETE, "/store/products/").hasAnyRole("ADMIN").//
					// Updating product price
					antMatchers(HttpMethod.PATCH, "/store/products/{pid}").hasAnyRole("ADMIN").//
					// Making a purchase
					antMatchers(HttpMethod.POST, "/store/purchases/**").hasAnyRole("ADMIN", "USER").//
					// Liking a product
					antMatchers(HttpMethod.POST, "/store/products/{pid}/like").hasAnyRole("ADMIN", "USER").//
					and().//
					logout().clearAuthentication(true).and().
					csrf().disable();
		}
}
}
