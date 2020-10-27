package com.example.tut4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tut4.model.ProductModel;
import com.example.tut4.repository.ProductRepository;

@Service //bu classın servis olduğunu belirttik
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public List<ProductModel> listAll() {
		return repo.findAll();
	}
	
	public void save(ProductModel productModel) {
		repo.save(productModel);
	}
	
	public ProductModel get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
