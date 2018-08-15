package com.storemanagement.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.storemanagement.storemanagement.domain.Like;

@RepositoryRestResource(exported = false) // This repository won't be exposed through the API
public interface LikeRepository extends JpaRepository<Like, Integer>{
	public Like save(Like Like);
}
