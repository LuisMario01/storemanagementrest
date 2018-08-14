package com.storemanagement.storemanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping(method=RequestMethod.POST, value="/store/products/{pid}/purchase")
	@ResponseBody
	public ResponseEntity<?> purchase(@PathVariable("pid")String productid, HttpServletRequest req){
		String lol = "LOL";
		lol = req.getHeader("Authorization");
		System.out.println(lol);
        return ResponseEntity.ok(lol); 
	}
}
