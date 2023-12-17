package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Address;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    private static Pageable getPageable(int pageNumber){
        int pageShow = 3;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageShow, Sort.by("recipientFullName").descending());
        return pageable;
    }

    public Page<Address> findAllAddress(int pageNumber, int customerId){
        Pageable pageable = getPageable(pageNumber);
        Page<Address> pageAddress = addressRepository.findByCustomerId(customerId, pageable);
        return pageAddress;
    }
}
