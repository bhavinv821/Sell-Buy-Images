package com.bhavin.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhavin.entity.Product;

@Repository
public interface ProductRepositry extends JpaRepository<Product, Long> {

}
