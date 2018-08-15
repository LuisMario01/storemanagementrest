package com.storemanagement.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.storemanagement.storemanagement.domain.Like;
import com.storemanagement.storemanagement.projections.LikeProjection;

@RepositoryRestResource(excerptProjection = LikeProjection.class, exported = false) // This repository won't be exposed through the API
public interface LikeRepository extends JpaRepository<Like, Integer>{
	public Like save(Like Like);
}
