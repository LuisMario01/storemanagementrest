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

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	@GeneratedValue
	private Integer idPurchase;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPerson")
	private Person person;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProduct")
	private Product product;
	
	@NotNull
	@Column(name="amount")
	private Integer amount;
	
	@NotNull
	@Column(name="date")
	private Date date;
	
	public Integer getIdPurchase() {
		return idPurchase;
	}
	public void setIdPurchase(Integer idPurchase) {
		this.idPurchase = idPurchase;
	}
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

