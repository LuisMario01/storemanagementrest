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
		  };
		
		@Override
		  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		  }
		  
		  
		  /*
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("john123").password("$2a$04$AjFEmZeX7mN8zSn57PUEZeJgBeoKMvwteZMBiP57Jb4AGFsUORmLC").roles("USER");
		}
		*/

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().
					and().
					authorizeRequests().//
					antMatchers("/store").permitAll().
					antMatchers(HttpMethod.GET, "/store/products").permitAll().
					antMatchers(HttpMethod.POST, "/store/**").hasAnyRole("ROLE_ADMIN", "ADMIN", "USER", "ROLE_USER").//
					/*antMatchers(HttpMethod.DELETE, "/store/products/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER").//
					antMatchers(HttpMethod.PUT, "/store/products/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER").//
					antMatchers(HttpMethod.PATCH, "/store/products/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER").
					*/
					and().//
					logout().clearAuthentication(true).and().
					csrf().disable();
		}
}
}
