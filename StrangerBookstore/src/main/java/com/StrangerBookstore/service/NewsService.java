package com.StrangerBookstore.service;

import com.StrangerBookstore.model.News;
import com.StrangerBookstore.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public List<News> findAll() {
        return  newsRepository.newFindAll();
    }

}
