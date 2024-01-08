package com.login.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.login.login.model.Product;
import com.login.login.repository.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getStart(Model model) {
        model.addAttribute("products", productRepository.findAll());

        return "home";
    }

    @GetMapping("/product/{id}")
    String getProduct(@PathVariable int id, Model model) {

        Product products = productRepository.findById(id).orElse(null);
        model.addAttribute("products", products);

        return "product";
    }

    @GetMapping("/order")
    public String getRegister() {
        return "orders";
    }
}
