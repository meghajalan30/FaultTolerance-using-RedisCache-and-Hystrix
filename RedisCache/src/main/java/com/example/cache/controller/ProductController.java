package com.example.cache.controller;

import com.example.cache.model.Product;
import com.example.cache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductService getUserService;

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> product(@PathVariable Long id) {
		System.out.println("Inside request");
		Product product=this.getUserService.getProduct(id);
		if(product!=null)
		return ResponseEntity.ok().body(this.getUserService.getProduct(id));
		else
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}