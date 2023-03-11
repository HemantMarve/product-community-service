package com.product.community.service;

import java.util.List;

import com.product.community.model.Product;

public interface ProductService {

	List<Product> getProducts();

	void saveProduct(Product product);
}
