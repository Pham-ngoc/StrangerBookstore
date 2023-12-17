package com.StrangerBookstore.controller.UserController;

import com.StrangerBookstore.model.*;
import com.StrangerBookstore.repository.*;
import com.StrangerBookstore.service.AddressService;
import com.StrangerBookstore.service.CustomerService;
import com.StrangerBookstore.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    CustomerService customerService;

    @Autowired
    WishlistService wishlistService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpSession session;

//PERSONAL
    @GetMapping("/personal")
    public String personal(Model model){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Profile profile = new Profile();
        profile.setName(customer.getCustomerName());
        profile.setPhoneNumber(customer.getPhoneNumber());
        profile.setEmail(customer.getEmail());
        profile.setPicture(customer.getPicture());
        model.addAttribute("profile", profile);
        return "User-view/profile-personal.html";
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@ModelAttribute Profile profile,
                                                @RequestParam(value = "file", required = false) MultipartFile file) {
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        // Xử lý dữ liệu từ profile
        customer.setCustomerName(profile.getName());
        customer.setPhoneNumber(profile.getPhoneNumber());
        customer.setEmail(profile.getEmail());
        customer.setPicture(customerService.getImageName(file));
        customerRepository.save(customer);
        session.setAttribute("loggingCustomer",customer);
        return ResponseEntity.ok("Profile updated successfully");
    }

//CHANGE PASSWORD
    @GetMapping("/changePass")
    public String changePass(Model model){
        ProfilePassword profilePassword = new ProfilePassword();
        model.addAttribute("profilePassword", profilePassword);
        return "User-view/profile-changePassword.html";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(Model model, @Valid @ModelAttribute("profilePassword") ProfilePassword profilePassword, BindingResult bindingResult){
        System.out.println("hello");
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "User-view/profile-changePassword.html";
        } else {
             if (!profilePassword.getNewPassword().equals(profilePassword.getConfirmNewPassword())) {
                model.addAttribute("newPasswordMismatch", "Password do not match");
                System.out.println("Error Email Confirm");
                 return "User-view/profile-changePassword.html";
             }
            Customer customer = (Customer) session.getAttribute("loggingCustomer");
            customer.setPassword(passwordEncoder.encode(profilePassword.getNewPassword()));
            customerRepository.save(customer);
            model.addAttribute("message", "Changed Password Successfully!");
            return "User-view/profile-changePassword.html";
        }
    }


//ADDRESS
    @GetMapping("/address/{page}")
    public String address(Model model, @PathVariable("page") int page){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Page<Address> pageAddress = addressService.findAllAddress(page, customer.getCustomerId());
        if (pageAddress.getTotalPages() == 0){
            model.addAttribute("msg", "Not found!");
        }
        List<Address> listAddress = pageAddress.getContent();
        model.addAttribute("listAddress", listAddress);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageAddress.getTotalPages());
        return "User-view/profile-address.html";
    }

    @GetMapping("/addressEdit")
    public String add_address(Model model){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Address address = new Address();
        model.addAttribute("address", address);
        model.addAttribute("editMode", false);
        return "User-view/profile-editAddress.html";
    }

    @GetMapping("/getByAddressID")
    public String getByAddressID(Model model, @RequestParam("addressID") String addressID){
        Address address = addressRepository.findByAddressId(Integer.parseInt(addressID));
        if(address != null) {
            System.out.println(address);
            model.addAttribute("address", address);
            model.addAttribute("editMode", true);
        }
        return "User-view/profile-editAddress.html";
    }

    @GetMapping("/deleteByAddressID")
    public String deleteByAddressId(Model model, @RequestParam("addressID") int addressID){
        Address address = addressRepository.findByAddressId(addressID);
        if(address != null){
            addressRepository.delete(address);
            model.addAttribute("message", "Delete Address Successfully!");
        }
        return "redirect:/profile/address/1";
    }

    @PostMapping("/addAddress")
    public String addAddress(Model model, @Valid @ModelAttribute("address") Address address, BindingResult bindingResult, RedirectAttributes ra){
        if(bindingResult.hasErrors()){
            return "User-view/profile-editAddress.html";
        } else{
            Customer customer = (Customer) session.getAttribute("loggingCustomer");
            address.setCustomer(customer);
            address.setCreateAt(LocalDateTime.now());
            address.setCreateBy(customer.getEmail());
            addressRepository.save(address);
            ra.addFlashAttribute("message", "Add Address Successfully!");
            System.out.println("Add Address Successfully!");
        }
        return "redirect:/profile/addressEdit";
    }

    @PostMapping("/updateAddress")
    public String updateAddress(Model model,
                                @Valid @ModelAttribute("address") Address address,
                                BindingResult bindingResult,
                                RedirectAttributes ra){
        if(bindingResult.hasErrors()){
            return "User-view/profile-editAddress.html";
        } else {
            Customer customer = (Customer) session.getAttribute("loggingCustomer");
            Address addressId = addressRepository.findByAddressId(address.getAddressId());
            System.out.println(addressId);
            if (addressId != null) {
                addressId.setRecipientFullName(address.getRecipientFullName());
                addressId.setRecipientPhoneNumber(address.getRecipientPhoneNumber());
                addressId.setAddressDetail(address.getAddressDetail());
                addressId.setAddressType(address.getAddressType());
                addressId.setCustomer(customer);
                addressId.setUpdateAt(LocalDateTime.now());
                addressId.setUpdateBy(customer.getEmail());
                addressRepository.save(addressId);
                ra.addFlashAttribute("message", "Update Address Successfully!");
                System.out.println("Update Address Successfully!");
            }
        }
        return "redirect:/profile/addressEdit";
    }

    @PostMapping("/deleteAddress")
    public String deleteAddress(Model model, @ModelAttribute("address") Address address, RedirectAttributes ra){
        Address addressId = addressRepository.findByAddressId(address.getAddressId());
        if(addressId != null){
            addressRepository.delete(addressId);
            ra.addFlashAttribute("message", "Delete Address Successfully!");
            System.out.println("Delete Address Successfully!");
        }
        return "redirect:/profile/addressEdit";
    }


//WISHLIST
    @GetMapping("/wishlist/{page}")
    public String wishlist(Model model, @PathVariable("page") int page){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Page<Wishlist> pageWishlist = wishlistService.findAllWishlist(page, customer.getCustomerId());
        List<Cart> cartProduct = cartRepository.findAllByCustomerId(customer.getCustomerId());
        int cartSize = cartProduct.size();
        if (pageWishlist.getTotalPages() == 0){
            model.addAttribute("msg", "Not found!");
        }
        List<Wishlist> listWishlist = pageWishlist.getContent();
        model.addAttribute("listWishlist", listWishlist);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageWishlist.getTotalPages());
        session.setAttribute("itemInCart", cartSize);
        return "User-view/profile-wishlist.html";
    }

    @GetMapping("/addToWishlist")
    public ResponseEntity<String> addToWishlist(Model model,
                                                @RequestParam("productId") int productId){
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Products products = productRepository.findById(productId).orElse(null);
        if (customer == null || products == null) {
            return ResponseEntity.notFound().build();
        }
        Wishlist wishlist = wishlistRepository.findByUserAndProduct(customer.getCustomerId(), productId);
        if(wishlist == null){
            wishlist = new Wishlist();
            wishlist.setCustomer(customer);
            wishlist.setProduct(products);
        } else {
            return ResponseEntity.ok("The Product Already Exists In The Wishlist!");
        }
        wishlistRepository.save(wishlist);
        return ResponseEntity.ok("Add Product To Wishlist Successfully!");
    }

    @GetMapping("/deleteWishlist")
    @ResponseBody
    public ResponseEntity<String> deleteWishlist(Model model, @RequestParam("productId") int productId) {
        Customer customer = (Customer) session.getAttribute("loggingCustomer");
        Wishlist wishlist = wishlistRepository.findByUserAndProduct(customer.getCustomerId(), productId);
        wishlistRepository.delete(wishlist);
        return ResponseEntity.ok("Delete Product To Wishlist Successfully!");
    }
}
