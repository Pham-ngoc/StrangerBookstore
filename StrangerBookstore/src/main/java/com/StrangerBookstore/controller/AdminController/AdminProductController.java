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
        return productOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product")
    public ResponseEntity<Products> create(@RequestBody Products product) {
        Products createdproduct = service.create(product);
        return ResponseEntity.ok(createdproduct);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Products> updateproduct(@PathVariable("id") Integer id, @RequestBody Products product) {
        if(service. findbyId (id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.update(id, product);
        }
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/product/{id}")
    public void deleteproduct(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}