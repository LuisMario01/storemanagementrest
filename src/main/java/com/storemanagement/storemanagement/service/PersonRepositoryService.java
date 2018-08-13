package com.storemanagement.storemanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storemanagement.storemanagement.domain.Person;
import com.storemanagement.storemanagement.domain.Product;
import com.storemanagement.storemanagement.repository.PersonRepository;

@Service
public class PersonRepositoryService {
	
	@Autowired
	private PersonRepository pr;
	
	// Loading demo data
	@Transactional
	public boolean loadData() {
		boolean result;
		try {		
			pr.save(new Person("admin", "root","ADMIN"));
			pr.save(new Person("user", "root", "USER"));
			result = true;
		}catch(Exception e) {
			result = false;
		}
		return result;
	}

}
