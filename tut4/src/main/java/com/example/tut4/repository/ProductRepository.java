package com.example.tut4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tut4.model.ProductModel;

public interface ProductRepository  extends JpaRepository<ProductModel, Long>{ //model tipini ve  primary key tipini yazdık

}
