package com.storemanagement.storemanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storemanagement.storemanagement.domain.Person;
import com.storemanagement.storemanagement.domain.Product;
import com.storemanagement.storemanagement.domain.Purchase;
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
			// Password must be Bcrypted in order to used basic auth
			// Password: password
			pr.save(new Person("admin", "$2a$04$AjFEmZeX7mN8zSn57PUEZeJgBeoKMvwteZMBiP57Jb4AGFsUORmLC","ADMIN"));
			pr.save(new Person("user", "$2a$04$AjFEmZeX7mN8zSn57PUEZeJgBeoKMvwteZMBiP57Jb4AGFsUORmLC", "USER"));
			result = true;
		}catch(Exception e) {
			result = false;
		}
		return result;
	}
	
	@Transactional
	public Purchase buyProduct() {
		return new Purchase();
	}

}
