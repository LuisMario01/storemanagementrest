package com.storemanagement.storemanagement.projections;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.storemanagement.storemanagement.domain.Like;
import com.storemanagement.storemanagement.domain.Product;

@Projection(name = "likeNoPersonProjection", types = {Like.class })
public interface LikeProjection {
	public Integer getIdLike() ;
	
	@JsonIgnore
	public Product getProduct();
}
