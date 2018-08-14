package com.storemanagement.storemanagement.service;

import org.springframework.stereotype.Service;

import com.storemanagement.storemanagement.domain.Person;
import com.storemanagement.storemanagement.repository.PersonRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private PersonRepository pr;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Person person = pr.findPersonByUsername(username);
	    UserBuilder builder = null;
	    if (person != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(person.getPassword());
	      String authorities = person.getRole();
	      builder.authorities(authorities);
	      builder.roles(authorities);
	      
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }
	    return builder.build();
	  }
}
