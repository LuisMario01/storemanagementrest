package com.storemanagement.storemanagement.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	@GeneratedValue
	private Integer idPurchase;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPerson")
	private Person person;
	
	@JsonIgnore
	@NotAudited
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProduct")
	private Product product;
	
	@NotNull
	@Column(name="amount")
	private Integer amount;
	
	@NotNull
	@Column(name="date")
	private Date date;
	
	public Purchase() {}
	
	public Purchase(Person person, Product product, Integer amount) {
		this.person = person;
		this.product = product;
		this.amount = amount;
		this.date = new Date();
	}
	
	public Integer getIdPurchase() {
		return idPurchase;
	}
	public void setIdPurchase(Integer idPurchase) {
		this.idPurchase = idPurchase;
	}
	
	@JsonIgnore
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

