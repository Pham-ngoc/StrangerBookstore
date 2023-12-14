package com.StrangerBookstore.service;


import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.CategoryRepository;
import com.StrangerBookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    public List<Products> findAll() {
        return productRepository.findAll();
    }

    public Optional<Products> findbyId(Integer id){
        return productRepository.findById(id);
    }

//    public Products create(Products Product) {
//
//        return productRepository.save(Product);
//    }

    public ResponseEntity<Object> create(Products product) throws Exception     {

        if (product == null){
            return new ResponseEntity<>("Cannot product because error product please try again", null, HttpStatus.BAD_REQUEST);
        }
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }




//    public Products update(Integer id, Products product) {
//        // Kiểm tra xem tin tức có tồn tại không
//        Optional<Products> existingProductOptional = productRepository.findById(id);
//        if (existingProductOptional.isPresent()) {
//            Products existingProduct = existingProductOptional.get();
//            // Cập nhật các trường tin tức
//            existingProduct.setProductName(product.getProductName());
//            existingProduct.setAuthor(product.getAuthor());
//            existingProduct.setPublisher(product.getPublisher());
//            existingProduct.setLanguage(product.getLanguage());
//            existingProduct.setCondition(product.getCondition());
//            existingProduct.setQuantityInStock(product.getQuantityInStock());
//            existingProduct.setDescription(product.getDescription());
//            existingProduct.setPrice(product.getPrice());
//            existingProduct.setCategories(product.getCategories());
//            existingProduct.setProduct_img(product.getProduct_img());
//            // Lưu tin tức đã cập nhật vào cơ sở dữ liệu
//            return productRepository.save(existingProduct);
//        } else {
//            // Nếu không tìm thấy tin tức, bạn có thể xử lý hoặc trả về null hoặc thông báo lỗi tùy thuộc vào yêu cầu của bạn
//            return null;
//        }
//    }


public Products update(Integer id, Products product) {
    Optional<Products> existingProductOptional = productRepository.findById(id);
    if (existingProductOptional.isPresent()) {
        Products existingProduct = existingProductOptional.get();

        // Cập nhật các trường thông tin khác
        existingProduct.setProductName(product.getProductName());
        existingProduct.setAuthor(product.getAuthor());
        existingProduct.setPublisher(product.getPublisher());
        existingProduct.setLanguage(product.getLanguage());
        existingProduct.setCondition(product.getCondition());
        existingProduct.setQuantityInStock(product.getQuantityInStock());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setProduct_img(product.getProduct_img());
        if (!existingProduct.getCategories().equals(product.getCategories())) {
            Optional<Categories> categoryOptional = categoryRepository.findById(product.getCategories().getCategoryId());
            if (categoryOptional.isPresent()) {
                existingProduct.setCategories(categoryOptional.get());
            } else {
                return null;
            }
        }

        return productRepository.save(existingProduct);
    } else {
        return null;
    }
}




    public void delete(Integer id) {
        productRepository.deleteById(id);
    }


}