package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.*;
import com.StrangerBookstore.repository.CartRepository;
import com.StrangerBookstore.repository.CategoryRepository;
import com.StrangerBookstore.repository.ProductRepository;
import com.StrangerBookstore.repository.ReviewsRepository;
import com.StrangerBookstore.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ShopController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    HttpSession session;


    @GetMapping("/shop/{page}")
    public String shop(Model model, @PathVariable("page") int page,
                       @RequestParam("CateID") String CateID,
                       @RequestParam("searchName") String searchName){
        List<Categories> listCate = categoryRepository.findAll();
        model.addAttribute("listCate", listCate);
        Page<Products> pageProduct = null;
        if(CateID.equals("all")){
            if(searchName.equals("null")){
                pageProduct = productService.findAllProductShop(page);
            }else{
                pageProduct = productService.findProductByNameAndCateID(page, null, searchName);
            }
        } else{
            if(searchName.equals("null")) {
                pageProduct = productService.findAllByCateID(page, Integer.parseInt(CateID));
            } else {
                pageProduct = productService.findProductByNameAndCateID(page, CateID, searchName);
            }
        }

        if (pageProduct.getTotalPages() == 0){
            model.addAttribute("message", "Not found!");
        }

        List<Products> listProduct = pageProduct.getContent();
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("currentPage", page);
        model.addAttribute("CateID", CateID);
        model.addAttribute("totalPages", pageProduct.getTotalPages());
        return "user-view/shop.html";
    }

    @GetMapping("/getByCateID")
    public String getByCate(Model model, @RequestParam("CateID") String CateID){

        if (CateID != null) {
            Optional<Categories> categories = categoryRepository.findById(Integer.parseInt(CateID));
            if (categories.isPresent()) {
                return "redirect:/shop/1?CateID=" + CateID +"&searchName=null";
            }
        }
        return "redirect:/shop/1?CateID=all&searchName=null";
    }

    @GetMapping("/searchName")
    public String searchName(Model model,
                             @RequestParam("searchName") String searchName,
                             @RequestParam("CateID") String CateID){
        String encodedSearchName = UriComponentsBuilder.fromUriString(searchName)
                .build()
                .encode()
                .toUriString();
        if(searchName.equals("null")){
            return "redirect:/shop/1?CateID=" + CateID + "&searchName=null";
        } else{
            return "redirect:/shop/1?CateID=" + CateID + "&searchName=" + encodedSearchName.replace("%20", "+") ;
        }
    }

    @GetMapping("/getByPrice")
    public ResponseEntity<List<Products>> handlePrices(Model model,
                                              @RequestParam("page") int page,
                                              @RequestParam("minPrice") double minPrice,
                                              @RequestParam("maxPrice") double maxPrice) {
        // Xử lý giá trị minPrice và maxPrice ở đây
        System.out.println("Received minPrice: " + minPrice);
        System.out.println("Received maxPrice: " + maxPrice);
        Page<Products> pageProduct = productService.findProductByPrice(page ,minPrice, maxPrice);
        List<Products> listProduct = pageProduct.getContent();

        // Trả về fragment HTML để AJAX sử dụng
        return ResponseEntity.ok(listProduct);
    }


    @GetMapping("/productDetail")
    public String productDetail(Model model, @RequestParam("productId") String productId){
        List<Products> listProduct = productRepository.findTop6Products();
        List<ProductReviews> listReview = reviewsRepository.findByProductId(Integer.parseInt(productId));
        model.addAttribute("listProduct", listProduct);
        Optional<Products> products = productRepository.findById(Integer.parseInt(productId));
        if(products.isPresent()){
            model.addAttribute("product", products.get());
            model.addAttribute("listReview", listReview);
        }
        return "user-view/product-detail.html";
    }


}
