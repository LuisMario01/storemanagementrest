package com.storemanagement.storemanagement.service;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.storemanagement.storemanagement.domain.Like;
import com.storemanagement.storemanagement.domain.Person;
import com.storemanagement.storemanagement.domain.Product;
import com.storemanagement.storemanagement.repository.LikeRepository;
import com.storemanagement.storemanagement.repository.PersonRepository;
import com.storemanagement.storemanagement.repository.ProductRepository;

@Service
public class LikeRepositoryService {
	
	@Autowired
	private LikeRepository lkr;
	
	@Autowired
	private ProductRepository prr;
	
	@Autowired
	private PersonRepository per;
	
	@Transactional
	public ResponseEntity<?> like(String pid, HttpServletRequest req){
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
		        Product product = prr.findProductByIdProduct(Integer.parseInt(pid));
		        Like like = lkr.save(new Like(person, product));
		        return ResponseEntity.ok(like);
		        
		    }
		    return ResponseEntity.badRequest().build();
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
