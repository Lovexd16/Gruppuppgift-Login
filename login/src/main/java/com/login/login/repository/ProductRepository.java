package com.login.login.repository;

import org.springframework.data.repository.CrudRepository;

import com.login.login.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
