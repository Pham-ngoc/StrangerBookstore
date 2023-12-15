package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.News;
import com.StrangerBookstore.model.Products;
import com.StrangerBookstore.repository.NewsRepository;
import com.StrangerBookstore.service.NewsService;
import com.StrangerBookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminNewsController {

    @Autowired
    NewsRepository newRepository;

    @Autowired
    NewsService service;

    @GetMapping("/news")
    public ResponseEntity<List<News>> news(Model model){
        return ResponseEntity.ok(newRepository.newsFindAll());
    }

    @PostMapping("/news")
    public ResponseEntity<News> create(@RequestBody News news) {
        News creatednews = service.create(news);
        return ResponseEntity.ok(creatednews);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<News> update(@PathVariable("id") Integer id, @RequestBody News news) {
        Optional<News> existingNewsOptional = service.findbyId(id);

        if (existingNewsOptional.isPresent()) {
            News existingNews = existingNewsOptional.get();
            service.update(id, news);
            return ResponseEntity.ok(news);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/news/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }


}
