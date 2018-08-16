package com.storemanagement.storemanagement.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.storemanagement.storemanagement.dto.PurchaseDTO;
import com.storemanagement.storemanagement.service.LikeRepositoryService;
import com.storemanagement.storemanagement.service.ProductRepositoryService;
import com.storemanagement.storemanagement.service.PurchaseRepositoryService;

@RestController
public class MainController {
	@Autowired
	private ProductRepositoryService prr;
	
	@Autowired
	private PurchaseRepositoryService prs;
	
	@Autowired
	private LikeRepositoryService lrs;
	
	/*
	 * Method to perform purchase of a product. Consult service for info.
	 * */
	@RequestMapping(method=RequestMethod.POST, value="/store/purchases")
	@ResponseBody
	public ResponseEntity<?> purchase(@RequestBody PurchaseDTO purchase, HttpServletRequest req){
		return prs.purchase(purchase,req);
	}
	
	/*
	 * Method to perform like on a product. Consult service for info.
	 * */
	@RequestMapping(method=RequestMethod.POST, value="/store/products/{pid}/like")
	@ResponseBody
	public ResponseEntity<?> like(@PathVariable("pid")String pid, HttpServletRequest req){
		return lrs.like(pid,req);
	}
	
	/*
	 * Method to perform order using join from entities. Consult service for info.
	 * */
	@RequestMapping(method=RequestMethod.GET, value="/store/products/byLike")
	@ResponseBody
	public ResponseEntity<?> sortByLike(){
		return prr.sortByLike();
	}
	
}
