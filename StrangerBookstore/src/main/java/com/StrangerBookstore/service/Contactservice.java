package com.StrangerBookstore.service;

import com.StrangerBookstore.model.ContactUs;

import com.StrangerBookstore.repository.contactReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Contactservice {
    @Autowired
    contactReponsitory contactReponsitory;

    public List<ContactUs> findAll() {
        return contactReponsitory.findAll();
    }
}
