package com.product.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.community.model.Product;
import com.product.community.repo.ProductRepo;
import com.product.community.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public List<Product> getProducts() {

		return this.productRepo.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		this.productRepo.save(product);

	}

}
