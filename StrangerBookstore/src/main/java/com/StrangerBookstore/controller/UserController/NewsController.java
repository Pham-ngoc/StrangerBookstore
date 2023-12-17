package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.Cart;
import com.StrangerBookstore.model.Customer;
import com.StrangerBookstore.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class NewsController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    HttpSession session;
    @RequestMapping("/news")
    public String homeNew(Model model){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        if(customer != null){
            List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
            session.setAttribute("itemInCart", cartProduct.size());
        }
        return ("User-view/news.html");
    }
    @RequestMapping("/news-detail")
    public String newDetail(Model model){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        if(customer != null){
            List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
            session.setAttribute("itemInCart", cartProduct.size());
        }
        return ("User-view/news-detail.html");
    }

}
