package com.StrangerBookstore.service;



import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.CategoryRepository;
import com.StrangerBookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public ResponseEntity<Products> create(Products product) throws Exception{
        if (product == null){
            return null;
        }
        return ResponseEntity.ok(productRepository.save(product));
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


    ResourceLoader resourceLoader;

    private static Pageable getPageableShop(int pageNumber){
        int pageShow = 12;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageShow, Sort.by("productName").descending());
        return pageable;
    }

    public Page<Products> findAllProductShop(int pageNumber){
        Pageable pageable = getPageableShop(pageNumber);
        Page<Products> pageProduct = productRepository.findAllProduct(pageable);
        return pageProduct;
    }

    public Page<Products> findAllByCateID(int pageNumber, int cateID){
        Pageable pageable = getPageableShop(pageNumber);
        Page<Products> pageProduct = productRepository.fillAllProductByCateID(pageable, cateID);
        return pageProduct;
    }

    public Page<Products> findProductByNameAndCateID(int pageNumber, String cateId, String name){
        Pageable pageable = getPageableShop(pageNumber);
        Page<Products> pageProduct = productRepository.fillProductByNameAndCateID(pageable, name, cateId);
        return pageProduct;
    }
    public Page<Products> findProductByPrice(int pageNumber, double minPrice, double maxPrice){
        Pageable pageable = getPageableShop(pageNumber);
        Page<Products> pageProduct = productRepository.findProductsByPrice(maxPrice, minPrice, pageable);
        return pageProduct;
    }

    public String getImageName(@RequestParam("product_img") MultipartFile file) {
        String filename = "";
        try {
            String uploadRootPath = resourceLoader.getResource("classpath:static/product_img/").getFile()
                    .getAbsolutePath();

            if (!Files.exists(Paths.get(uploadRootPath))) {
                Files.createDirectories(Paths.get(uploadRootPath));
            }

            filename = file.getOriginalFilename();
            Path filePath = Paths.get(uploadRootPath, filename);
            Files.write(filePath, file.getBytes());
            // Kiểm tra định dạng của tệp
            boolean isImage = Files.probeContentType(filePath).startsWith("image");
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }
}
