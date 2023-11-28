package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Product;
import com.StrangerBookstore.repository.CategoryRepository;
import com.StrangerBookstore.repository.ProductRepository;
import com.StrangerBookstore.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
public class AdminProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    HttpSession session;


    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getByProductId(@PathVariable("productId") int productId){
        return ResponseEntity.ok(productRepository.findByProductId(productId));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestParam("imageFile") MultipartFile imageFile,
                                                  @RequestParam("productName") String productName,
                                                 @RequestParam("author") String author,
                                                 @RequestParam("publisher") String publisher,
                                                 @RequestParam("language") String language,
                                                 @RequestParam("condition") String condition,
                                                 @RequestParam("quantityInStock") String quantityInStock,
                                                 @RequestParam("isbn") String isbn,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("price") long price,
                                                  @RequestParam("category") int categoryId) {
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        List<Categories> listCategories = categoryRepository.findAll();
        Categories categories = null;
        for (Categories category : listCategories) {
            if (category.getCategoryId() == categoryId) {
                categories = category;
            }
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setAuthor(author);
        product.setPublisher(publisher);
        product.setLanguage(language);
        product.setCondition(condition);
        product.setQuantityInStock(quantityInStock);
        product.setIsbn(isbn);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategories(categories);
        product.setProduct_img(productService.getImageName(imageFile));
        product.setCreateBy(customer.getEmail());
        product.setCreateAt(LocalDateTime.now());
        // Save the product entity
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") int productId,
                                                  @RequestParam("imageFile") MultipartFile imageFile,
                                                  @RequestParam("productName") String productName,
                                                 @RequestParam("author") String author,
                                                 @RequestParam("publisher") String publisher,
                                                 @RequestParam("language") String language,
                                                 @RequestParam("condition") String condition,
                                                 @RequestParam("quantityInStock") String quantityInStock,
                                                 @RequestParam("isbn") String isbn,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("price") long price,
                                                  @RequestParam("category") int categoryId) {
        // Find the existing product entity
        Product product = productRepository.findByProductId(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        // Update other fields
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        List<Categories> listCategories = categoryRepository.findAll();
        Categories categories = null;
        for (Categories category : listCategories) {
            if (category.getCategoryId() == categoryId) {
                categories = category;
            }
        }
        product.setProductName(productName);
        product.setAuthor(author);
        product.setPublisher(publisher);
        product.setLanguage(language);
        product.setCondition(condition);
        product.setQuantityInStock(quantityInStock);
        product.setIsbn(isbn);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategories(categories);
        product.setProduct_img(productService.getImageName(imageFile));
        product.setUpdateBy(customer.getEmail());
        product.setUpdateAt(LocalDateTime.now());
        // Save the product entity
        productRepository.save(product);

        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> getDelete(@PathVariable("productId") int productId){
        if(productId < 0){
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }
}
