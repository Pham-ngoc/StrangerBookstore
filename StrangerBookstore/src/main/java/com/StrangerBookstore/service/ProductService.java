package com.StrangerBookstore.service;


import com.StrangerBookstore.model.Product;
import com.StrangerBookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findbyId(Integer id){
        return productRepository.findById(id);
    }

    public Product create(Product Product) {

        return productRepository.save(Product);
    }

    public Product update(Integer id, Product product) {
        Product model =productRepository.findById(id).get();
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