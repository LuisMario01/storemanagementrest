package com.storemanagement.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.storemanagement.storemanagement.domain.Purchase;

@RepositoryRestResource(collectionResourceRel = "purchases", path = "purchases")
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	public Purchase save(Purchase purchase);
}
