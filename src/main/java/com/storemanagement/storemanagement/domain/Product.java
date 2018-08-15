package com.storemanagement.storemanagement.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="product")
@Audited
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduct;

	@NotAudited
	@NotNull
	@Column(name="product")
	private String product;
	
	@NotNull
	@DecimalMin(value = "0.01", inclusive = true, message="Price must be equal or greater than 0.01$")
	@Column(name="price")
	private Double price;
	
	@NotAudited
	@NotNull
	@Min(value=0, message="Stock must be equal or greater than 0")
	@Column(name="stock")
	@Version
	private Integer stock;
	
	@JsonIgnore
	@NotAudited
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Collection<Purchase> purchases = new ArrayList<Purchase>();
	
	@JsonIgnore
	@NotAudited
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Collection<Like> likes = new ArrayList<Like>();
	
	@JsonInclude
	@NotAudited
	@Formula("(select count(likes.id_like) from likes where likes.id_product = id_product)")
	private Integer likeAmount;
	
	public Product() {}
	
	public Product(Integer stock) {
		this.stock = stock;
	}
	
	public Product(String product, Double price, Integer stock){
		this.product = product;
		this.stock = stock;
		this.price = price;
	}
	
	public Integer getLikeAmount() {
		return likeAmount;
	}
	
	public void setLikeAmount(Integer likeAmount) {
		this.likeAmount = likeAmount;
	}
	
	public Integer getAmountOfLikes() {
		return this.likes.size();
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public Collection<Like> getLikes() {
		return likes;
	}
	public void setLikes(Collection<Like> likes) {
		this.likes = likes;
	}
	
	public String toString() {
		return "\t"+"{\n\"idProduct\": \" "+this.idProduct+
				"\n\"product\": \""+this.product+"\""+
				"\n\"price\": "+this.price+
				"\n\"stock\": "+this.stock+
				"\"\n"+"\"likeAmount\":\" "+this.likeAmount+"\n}";
	}
}
