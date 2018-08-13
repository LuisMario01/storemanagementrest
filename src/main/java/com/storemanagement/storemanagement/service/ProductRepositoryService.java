package com.storemanagement.storemanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.storemanagement.storemanagement.repository.ProductRepository;
import com.storemanagement.storemanagement.domain.Product;

@Service
public class ProductRepositoryService {
	@Autowired
	private ProductRepository pr;
	
	// Loading demo data
	@Transactional
	public boolean loadData() {
		boolean result;
		try {		
			pr.save(new Product("Milk", 1.5, 100));
			pr.save(new Product("Doritos", 0.75, 200));
			pr.save(new Product("Almonds", 2.0, 89));
			pr.save(new Product("Soda", 0.65, 99));
			pr.save(new Product("Nuts", 2.55, 67));
			result = true;
		}catch(Exception e) {
			result = false;
		}
		return result;
	}
}
