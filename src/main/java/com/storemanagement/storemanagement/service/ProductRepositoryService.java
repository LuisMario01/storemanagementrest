package com.storemanagement.storemanagement.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanagement.storemanagement.repository.ProductRepository;
import com.storemanagement.storemanagement.domain.Product;

@Service
public class ProductRepositoryService {
	@Autowired
	private ProductRepository pr;
	
	/*
	 * Method used to load product dummy data
	 * */
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
	
	/*
	 * Method to perform order using like sorting. 
	 * Criteria API is used to build query on a programmatical level.
	 * */
	public ResponseEntity<?> sortByLike(){
		try {
			List<Product> products = pr.findAll(new Specification<Product>(){
				
				@Override
				public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate criteria = cb.conjunction();
					query.orderBy(cb.desc(root.get("likeAmount")));
					return criteria;
				}
			});
			return ResponseEntity.ok(products);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
