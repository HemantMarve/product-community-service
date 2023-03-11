package com.product.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.community.model.Product;
import com.product.community.model.StandardMessage;
import com.product.community.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins="http://localhost:8083/")
public class ProductManagementController {

	@Autowired
	private ProductService prodcutService;

	@GetMapping("")
	public ResponseEntity<List<Product>> getProducts() {
		return ResponseEntity.ok(this.prodcutService.getProducts());
	}
	
	@PostMapping("")
	public ResponseEntity<StandardMessage> saveProduct(@RequestBody Product product) {
		this.prodcutService.saveProduct(product);
		return ResponseEntity.ok(new StandardMessage("success","product inserted"));
	}

}
