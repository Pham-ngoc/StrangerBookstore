package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.*;
import com.StrangerBookstore.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CheckoutController {

    @Autowired
    HttpSession session;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    StatusOrderRepository statusOrderRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ShipInforRepository shipInforRepository;

    @GetMapping("/checkout")
    public String checkout(Model model){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        List<Cart> listCart = cartRepository.findAllByCustomerId(customer.getCustomerId());
        List<Address> listAddress = addressRepository.findAllByCustomerId(customer.getCustomerId());
        double totalAmount = cartRepository.getAmount(customer.getCustomerId());
        model.addAttribute("listCart", listCart);
        model.addAttribute("sizeListCart", listCart.size());
        model.addAttribute("listAddress", listAddress);
        model.addAttribute("productTotal", totalAmount);
        model.addAttribute("msg", "Not found!");
        session.setAttribute("itemInCart", listCart.size());
        return "User-view/check-out.html";
    }

    @GetMapping("/placeOrder")
    public ResponseEntity<Map<String, Object>> placeOrder(Model model,
                                                          @RequestParam("addressId") int addressId,
                                                          @RequestParam("paymentMethod") String paymentMethod){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        List<Cart> listCart = cartRepository.findAllByCustomerId(customer.getCustomerId());
        Address address = addressRepository.findByAddressId(addressId);
        double totalAmount = cartRepository.getAmount(customer.getCustomerId());
        StatusOrders statusOrders = statusOrderRepository.findStatusByName("Chờ thanh toán");
        if(address != null){
            Orders orders = new Orders();
            orders.setCustomer(customer);
            orders.setStatusOrders(statusOrders);
            orders.setPaymentMethod(paymentMethod);
            orders.setTotalAmount(totalAmount);
            orders.setCreateBy(customer.getEmail());
            orders.setCreateAt(LocalDateTime.now());
            ordersRepository.save(orders);
            if(orders.getOrderId() > 0) {
                listCart.forEach(cart -> {
                    OrderDetail orderDetails = new OrderDetail();
                    orderDetails.setOrders(orders);
                    orderDetails.setProducts(cart.getProducts());
                    orderDetails.setQuantity(cart.getQuantity());
                    orderDetails.setAmount(cart.getQuantity() * cart.getProducts().getPrice());
                    orderDetails.setCreateBy(customer.getEmail());
                    orderDetails.setCreateAt(LocalDateTime.now());
                    ShipInfor shipInfor = new ShipInfor();
                    shipInfor.setAddress(address);
                    shipInfor.setOrders(orders);
                    shipInforRepository.save(shipInfor);
                    orderDetailRepository.save(orderDetails);
                    cartRepository.delete(cart);
                });
            }
        }
        List<Cart> listCartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        Map<String, Object> response = new HashMap<>();
        response.put("itemInCart", listCartProduct.size());
        return ResponseEntity.ok(response);
    }
}
