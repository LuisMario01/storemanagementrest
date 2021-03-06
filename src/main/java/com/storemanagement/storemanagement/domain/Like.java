package com.storemanagement.storemanagement.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="likes")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLike;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person")
	private Person person;
	
	/*
	 * @NotAudited annotation to avoid log on product table from like entity
	 * */
	@NotAudited
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idProduct")
	private Product product;
	
	public Like() {}
	
	public Like(Person person, Product product) {
		this.person = person;
		this.product = product;
	}
	
	public Integer getIdLike() {
		return idLike;
	}
	public void setIdLike(Integer idLike) {
		this.idLike = idLike;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	/*
	 * Product serialization not performed on a Like-type resource
	 * */
	@JsonIgnore
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
