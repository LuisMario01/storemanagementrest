package com.storemanagement.storemanagement.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.storemanagement.storemanagement.domain.Product;

@Projection(name = "listingProductProjection", types = {Product.class })
public interface ProductProjection {
	
	public Integer getIdProduct();
	
	public String getProduct();
	
	public Double getPrice();
	
	public Integer getStock();
	
	@Value("#{target.likes.size()}")
	public Integer getTimesLiked();

}
