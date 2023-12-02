package com.StrangerBookstore.service;


import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Products> findAll() {
        return productRepository.findAll();
    }

    public Optional<Products> findbyId(Integer id){
        return productRepository.findById(id);
    }

    public Products create(Products Product) {

        return productRepository.save(Product);
    }

    public Products update(Integer id, Products product) {
        Products model =productRepository.findById(id).get();
        model.setProductName(product.getProductName());
        model.setAuthor(product.getAuthor());
        model.setPublisher(product.getPublisher());
        model.setLanguage(product.getLanguage());
        model.setCondition(product.getCondition());
        model.setQuantityInStock(product.getQuantityInStock());
        model.setDescription(product.getDescription());
        return productRepository.save(model);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }


}