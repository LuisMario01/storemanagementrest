package com.storemanagement.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.storemanagement.storemanagement.domain.Person;

//This repository won't be exposed through the API
@RepositoryRestResource(exported = false)
public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	public Person findPersonByUsername(String username);
	
	public Person save(Person person);
}
