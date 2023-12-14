package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.ProductRepository;
import com.StrangerBookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminProductController {


    @Autowired
    ProductService service;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public ResponseEntity<List<Products>> product(Model model){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Products> getCategory(@PathVariable Integer id) {
        Optional<Products> productOptional = service.findbyId(id);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product")
    public ResponseEntity<Object> create(@RequestBody Products product) throws Exception {
        ResponseEntity<Object> response = service.create(product);
        return response;
    }



    @PutMapping("/product/{id}")
    public ResponseEntity<Products> update(@PathVariable("id") Integer id, @RequestBody Products product) {
        Products updatedProduct = service.update(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}