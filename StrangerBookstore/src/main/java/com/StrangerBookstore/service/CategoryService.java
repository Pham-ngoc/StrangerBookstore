package com.StrangerBookstore.service;

import com.StrangerBookstore.model.Categories;
import com.StrangerBookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoriesRepository;

    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    public Categories findbyId(Integer id){
        return categoriesRepository.findById(id).get();
    }

    public Categories create(Categories categories) {

        return categoriesRepository.save(categories);
    }

    public Categories update(Integer id, Categories categories) {
        Categories model = categoriesRepository.findById(id).get();
        model.setCategoryName(categories.getCategoryName());
        return categoriesRepository.save(model);
    }

    public void delete(Integer id) {
        categoriesRepository.deleteById(id);
    }

    public List<Categories> sreachCategories(String query){
        List<Categories> categories = categoriesRepository.findByCategoryName(query);
        return categories;
    }
}
