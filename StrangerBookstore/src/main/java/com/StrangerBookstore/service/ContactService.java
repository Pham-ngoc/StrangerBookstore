package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.ContactUs;

import com.StrangerBookstore.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactReponsitory;

    public List<ContactUs> findAll() {
        return contactReponsitory.findAll();
    }
    public ContactUs update(Integer id, ContactUs contactUs) {
        ContactUs model = contactReponsitory.findById(id).get();
        model.setMessage(contactUs.getMessage());
        model.setStatus(contactUs.getStatus());
        return contactReponsitory.save(model);
    }

    public ContactUs findbyId(Integer id){
        return contactReponsitory.findById(id).get();
    }
}
