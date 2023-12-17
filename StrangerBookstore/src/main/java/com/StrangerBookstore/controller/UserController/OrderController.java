package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.*;
import com.StrangerBookstore.repository.*;
import com.StrangerBookstore.service.OrderDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    StatusOrderRepository statusOrderRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ShipInforRepository shipInforRepository;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    HttpSession session;

    @GetMapping("/myOrder/{page}")
    public String viewOrder(Model model, @PathVariable("page") int page, @RequestParam("statusId") String statusId){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        List<StatusOrders> listStatus = statusOrderRepository.findAll();
        List<Orders> orders = ordersRepository.findByCustomerId(customer.getCustomerId());
        Page<OrderDetail> pageOrderDetail = null;
        List<OrderDetail> listOrderDetail = new ArrayList<>();

        for (Orders order : orders) {
            if(statusId.equals("all")){
                pageOrderDetail = orderDetailService.findByOrderId(page, order.getOrderId());
                System.out.println(statusId);
            } else {
                pageOrderDetail = orderDetailService.findByOrderIdAndStatus(page, order.getOrderId(), Integer.parseInt(statusId));
                System.out.println(statusId);
            }

            if (pageOrderDetail != null && pageOrderDetail.getTotalPages() > 0) {
                listOrderDetail.addAll(pageOrderDetail.getContent());
                model.addAttribute("listOrderDetail", listOrderDetail);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", pageOrderDetail.getTotalPages());
            }
        }
        List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        session.setAttribute("itemInCart", cartProduct.size());
        model.addAttribute("statusId", statusId);
        model.addAttribute("listStatus", listStatus);
        model.addAttribute("orders", orders);
        return "User-view/my-order.html";
    }

    @GetMapping("/getByStatusID")
    public String getByStatusID(Model model, @RequestParam("statusId") String statusId){
        if (statusId != null) {
            Optional<StatusOrders> status = statusOrderRepository.findById(Integer.parseInt(statusId));
            if (status.isPresent()) {
                return "redirect:/myOrder/1?statusId=" + statusId;
            }
        }
        return "redirect:/myOrder/1?statusId=all";
    }

    @GetMapping("/getOrderDetail")
    public String orderDetail(Model model, @RequestParam("orderDetailId") int orderDetailId){
        List<StatusOrders> listStatus = statusOrderRepository.findAll();
        int ordersId = orderDetailRepository.findOrdersByOrderDetailId(orderDetailId);
        int addressShipInfor = shipInforRepository.findAddressByOrderId(ordersId);
        Address address = addressRepository.findByAddressId(addressShipInfor);
        OrderDetail orderDetail = orderDetailRepository.findAllByOrderDetailId(orderDetailId);
        model.addAttribute("listStatus", listStatus);
        model.addAttribute("shipInfor", address);
        model.addAttribute("orderDetail", orderDetail);
        return "User-view/my-order-detail.html";
    }

    @GetMapping("/writeReview")
    public ResponseEntity<String> writeReview(Model model,
                                              @RequestParam("productId") int productId,
                                              @RequestParam("starForProduct") String star,
                                              @RequestParam("reviewContent") String reviewContent){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Products products = productRepository.findByProductId(productId);
        if(productId > 0){
            ProductReviews productReviews = new ProductReviews();
            productReviews.setProduct(products);
            productReviews.setCustomer(customer);
            productReviews.setReviewContent(reviewContent);
            productReviews.setStarForProduct(Integer.parseInt(star));
            productReviews.setCreateAt(LocalDateTime.now());
            productReviews.setCreateBy(customer.getEmail());
            reviewsRepository.save(productReviews);
        }
        return ResponseEntity.ok("ok");
    }
}
