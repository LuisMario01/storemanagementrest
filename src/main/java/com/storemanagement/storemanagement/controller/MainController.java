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
import com.storemanagement.storemanagement.service.PurchaseRepositoryService;

@RestController
public class MainController {
	
	@Autowired
	private PurchaseRepositoryService prs;
	
	@Autowired
	private LikeRepositoryService lrs;

	@RequestMapping(method=RequestMethod.POST, value="/store/purchases")
	@ResponseBody
	public ResponseEntity<?> purchase(@RequestBody PurchaseDTO purchase, HttpServletRequest req){
		return prs.purchase(purchase,req);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/store/products/{pid}/like")
	@ResponseBody
	public ResponseEntity<?> like(@PathVariable("pid")String pid, HttpServletRequest req){
		return lrs.like(pid,req);
	}
}
