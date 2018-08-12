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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="person" , uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPerson;
	
	@NotNull
	@Column(name="username", unique = true, length=8)
	private String username;
	
	@Column(name="password", length=8)
	@NotNull(message = "Field: password cannot be null")
	private String password;
	
	@NotNull
	@Column(name="role")
	private Integer role;
	
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	private Collection<Purchase> purchases = new ArrayList<Purchase>();
	
	public Person() {}
	
	public Person(String username, String password, Integer role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}
}
