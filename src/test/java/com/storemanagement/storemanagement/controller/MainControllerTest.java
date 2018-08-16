package com.storemanagement.storemanagement.controller;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.storemanagement.storemanagement.StoremanagementApplication;
import com.storemanagement.storemanagement.domain.Like;
import com.storemanagement.storemanagement.domain.Person;
import com.storemanagement.storemanagement.domain.Product;
import com.storemanagement.storemanagement.domain.Purchase;
import com.storemanagement.storemanagement.util.AssertAnnotations;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, StoremanagementApplication.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class MainControllerTest {
	
	@Test
	  public void typeAnnotationsForProduct() {
	    // assert
	    AssertAnnotations.assertType(
	        Product.class, Entity.class, Table.class);
	  }
	
	@Test
	  public void typeAnnotationsForStoreUser() {
	    // assert
	    AssertAnnotations.assertType(
	        Person.class, Entity.class, Table.class);
	  }
	
	@Test
	  public void typeAnnotationsForLike() {
	    // assert
	    AssertAnnotations.assertType(
	        Like.class, Entity.class, Table.class);
	  }
	
	@Test
	  public void typeAnnotationsForPurchase() {
	    // assert
	    AssertAnnotations.assertType(
	        Purchase.class, Entity.class, Table.class);
	  }
	

	  @Test
	  public void methodAnnotationsForPerson() {
	    // assert
	    AssertAnnotations.assertMethod(
	    		Person.class, "getIdPerson");
	    AssertAnnotations.assertMethod(
		        Person.class, "getPassword");
	    AssertAnnotations.assertMethod(
	    		Person.class, "getUsername");
	    AssertAnnotations.assertMethod(
	    		Person.class, "getRole");
	  }
	  
	  @Test
	  public void methodAnnotationsForLike() {
	    // assert
	    AssertAnnotations.assertMethod(
	        Like.class, "getIdLike");
	    AssertAnnotations.assertMethod(
	    		Like.class, "getPerson");
	    AssertAnnotations.assertMethod(
	    		Like.class, "getProduct");
	  }
	  
	  @Test
	  public void methodAnnotationsForPurchase() {
	    // assert
	    AssertAnnotations.assertMethod(
	        Purchase.class, "getIdPurchase");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getPerson");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getProduct");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getAmount");
	    AssertAnnotations.assertMethod(
	    		Purchase.class, "getDate");
	  }
	  
	  @Test
	  public void methodAnnotationsForProduct() {
	    // assert
	    AssertAnnotations.assertMethod(
	        Product.class, "getIdProduct");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getProduct");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getPrice");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getStock");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getPurchases");
	    AssertAnnotations.assertMethod(
	    		Product.class, "getLikes");
	  }
	 
}
