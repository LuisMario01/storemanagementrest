package com.storemanagement.storemanagement.service;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanagement.storemanagement.domain.Person;
import com.storemanagement.storemanagement.domain.Product;
import com.storemanagement.storemanagement.domain.Purchase;
import com.storemanagement.storemanagement.dto.PurchaseDTO;
import com.storemanagement.storemanagement.repository.PersonRepository;
import com.storemanagement.storemanagement.repository.ProductRepository;
import com.storemanagement.storemanagement.repository.PurchaseRepository;

@Service
public class PurchaseRepositoryService {
	
	@Autowired
	public ProductRepository prr;
	
	@Autowired
	public PersonRepository per;
	
	@Autowired
	public PurchaseRepository pur;
	
	public ResponseEntity<?> purchase(PurchaseDTO purchasedto, HttpServletRequest req){
		try {
			String authorization = req.getHeader("Authorization");
			String[] values = {""};
		    if (authorization != null && authorization.startsWith("Basic")) {
		        // Authorization: Basic base64credentials
		        String base64Credentials = authorization.substring("Basic".length()).trim();
		        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
		                Charset.forName("UTF-8"));
		        // credentials = username:password
		        values = credentials.split(":",2);
		        
		        Person person = per.findPersonByUsername(values[0]);
		        Product product = prr.findProductByIdProduct(purchasedto.getProduct());
		        // Product stock validation
		        if(product.getStock()>purchasedto.getAmount()) {
		        	Purchase purchase = new Purchase(person, product, purchasedto.getAmount());
		        	product.setStock(product.getStock()-purchasedto.getAmount());
			        pur.save(purchase);
			        prr.save(product); // Updating new product price.
			        return ResponseEntity.ok(""); 
	
		        }
		        else {
		        	return ResponseEntity.unprocessableEntity().build();
		        }
		    }
		    return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
        
	}
}
