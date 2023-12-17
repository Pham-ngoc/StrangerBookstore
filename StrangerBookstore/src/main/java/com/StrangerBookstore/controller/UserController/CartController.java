package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Cart;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.CartRepository;
import com.StrangerBookstore.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    HttpSession session;

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/cart")
    public String displayCartPage(Model model) {
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        if (cartProduct.isEmpty()) {
            return "User-view/cart.html";
        }
        double totalAmount = cartRepository.getAmount(customer.getCustomerId());
        model.addAttribute("cartProducts", cartProduct);
        model.addAttribute("count", cartProduct.size());
        model.addAttribute("itemInCart", cartProduct.size());
        session.setAttribute("itemInCart", cartProduct.size());
//        System.out.println(cartProduct.size());
        model.addAttribute("totalAmount", totalAmount);
        return "User-view/cart.html";
    }
    @GetMapping("/addToCart")
    public ResponseEntity<Map<String, Object>> addToCart(Model model,
                                                         @RequestParam("productId") int productId) {
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Products products = productRepository.findById(productId).orElse(null);
        if (customer == null || products == null) {
            return ResponseEntity.notFound().build();
        }
        Cart cartItem = cartRepository.findByUserAndProduct(customer.getCustomerId(), products.getProductId());
        if (cartItem == null) {
            cartItem = new Cart();
            cartItem.setCustomer(customer);
            cartItem.setProduct(products);
            cartItem.setQuantity(1);
        } else{
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        cartRepository.save(cartItem);
        List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        int cartSize = cartProduct.size();
        Map<String, Object> response = new HashMap<>();
        response.put("itemInCart", cartSize);
        session.setAttribute("itemInCart", cartSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deleteItemInCard")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteItemInCard(Model model, @RequestParam("productId") int productId) {
        Map<String, Object> response = new HashMap<>();
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Cart cart = cartRepository.findByUserAndProduct(customer.getCustomerId(), productId);
        cartRepository.delete(cart);
        List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        int cartSize = cartProduct.size();
        double totalAmount = cartRepository.getAmount(customer.getCustomerId());
        System.out.println(totalAmount);
        response.put("totalAmount", totalAmount);
        response.put("itemInCart", cartSize);
        session.setAttribute("itemInCart", cartSize);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/decreaseQuantity")
    @ResponseBody
    public Map<String, Object> decreaseQuantity(Model model, @RequestParam("productId") int productId) {
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Cart cart = cartRepository.findByUserAndProduct(customer.getCustomerId(), productId);
        Optional<Products> products = productRepository.findById(productId);
        Map<String, Object> map = new HashMap<>();
        int currentQuantity = cart.getQuantity();
        if (currentQuantity > 1) {
            cart.setQuantity(currentQuantity - 1);
        }
        cartRepository.save(cart);
        double amount = products.get().getPrice() * cart.getQuantity();
        double totalAmount = cartRepository.getAmount(customer.getCustomerId());
        List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        int cartSize = cartProduct.size();
        map.put("amount", amount);
        map.put("totalAmount", totalAmount);
        map.put("quantity", cart.getQuantity());
        session.setAttribute("itemInCart", cartSize);
        return map;
    }

    @PostMapping("/increaseQuantity")
    @ResponseBody
    public Map<String, Object> increaseQuantity(Model model, @RequestParam("productId") int productId) {
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Cart cart = cartRepository.findByUserAndProduct(customer.getCustomerId(), productId);
        Optional<Products> product = productRepository.findById(productId);
        Map<String, Object> map = new HashMap<>();
        int currentQuantity = cart.getQuantity();
        cart.setQuantity(currentQuantity + 1);
        cartRepository.save(cart);
        double amount = product.get().getPrice() * cart.getQuantity();
        double totalAmount = cartRepository.getAmount(customer.getCustomerId());
        List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        int cartSize = cartProduct.size();
        map.put("amount", amount);
        map.put("totalAmount", totalAmount);
        map.put("quantity", cart.getQuantity());
        session.setAttribute("itemInCart", cartSize);
        return map;
    }
}
