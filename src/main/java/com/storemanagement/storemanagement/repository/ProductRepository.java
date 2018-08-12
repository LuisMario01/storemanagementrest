package com.storemanagement.storemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.storemanagement.storemanagement.domain.Product;
import com.storemanagement.storemanagement.projections.ProductProjection;

@RepositoryRestResource(excerptProjection = ProductProjection.class,
						collectionResourceRel = "products", 
						path = "products")

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public List<Product> findAll();
	
	public Product save(Product product);
	
	public Product findByOrderByProduct();
}
