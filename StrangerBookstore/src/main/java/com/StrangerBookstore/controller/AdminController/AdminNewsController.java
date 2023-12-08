package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.News;
import com.StrangerBookstore.repository.NewsRepository;
import com.StrangerBookstore.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminNewsController {

    @Autowired
    NewsRepository newRepository;



    @GetMapping("/news")
    public ResponseEntity<List<News>> news(Model model){
        return ResponseEntity.ok(newRepository.newsFindAll());
    }


}
