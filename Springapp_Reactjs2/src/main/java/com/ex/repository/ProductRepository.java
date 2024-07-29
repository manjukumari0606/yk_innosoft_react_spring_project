package com.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
