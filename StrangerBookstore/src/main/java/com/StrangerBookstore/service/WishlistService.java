package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.model.Wishlist;
import com.StrangerBookstore.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    private static Pageable getPageable(int pageNumber){
        int pageShow = 3;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageShow, Sort.by("wishlistId").descending());
        return pageable;
    }

    public Page<Wishlist> findAllWishlist(int pageNumber, int customerId){
        Pageable pageable = getPageable(pageNumber);
        Page<Wishlist> pageWishlist = wishlistRepository.findAllByCustomerID(pageable, customerId);
        return pageWishlist;
    }
}
