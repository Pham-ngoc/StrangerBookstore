package com.StrangerBookstore.controller.AdminController;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.model.Picture;
import com.StrangerBookstore.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminPicProductController {
    @Autowired
    PictureService service;

    @GetMapping("/picProduct")
    public ResponseEntity<List<Picture>> categories(Model model) {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/picProduct/{id}")
    public ResponseEntity<Picture> getpicture(@PathVariable Integer id) {
        Optional<Picture> pictureOptional = service.findbyId(id);
        return pictureOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/picProduct")
    public ResponseEntity<Picture> create(@RequestBody Picture picture) {
        Picture createdpicture = service.create(picture);
        return ResponseEntity.ok(createdpicture);
    }

    @PutMapping("/picProduct/{id}")
    public ResponseEntity<Picture> updatepicture(@PathVariable("id") Integer id, @RequestBody Picture picture) {
        if (service.findbyId(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.update(id, picture);
        }
        return ResponseEntity.ok(picture);
    }


    @DeleteMapping("/picProduct/{id}")
    public void deletepicture(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}