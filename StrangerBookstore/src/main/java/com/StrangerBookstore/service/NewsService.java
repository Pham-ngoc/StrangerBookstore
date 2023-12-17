package com.StrangerBookstore.service;

import com.StrangerBookstore.model.News;
import com.StrangerBookstore.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public List<News> findAll() {

        return  newsRepository.findAll();
    };

    public Optional<News> findbyId(Integer id){
        return newsRepository.findById(id);
    }

    public News create(News news) {
        return newsRepository.save(news);
    }

    public News update(Integer id, News updatedNews) {
        // Kiểm tra xem tin tức có tồn tại không
        Optional<News> existingNewsOptional = newsRepository.findById(id);
        if (existingNewsOptional.isPresent()) {
            News existingNews = existingNewsOptional.get();
            // Cập nhật các trường tin tức
            existingNews.setNewsTitle(updatedNews.getNewsTitle());
            existingNews.setNewsContent(updatedNews.getNewsContent());
            existingNews.setNewsPicture(updatedNews.getNewsPicture());
            // Lưu tin tức đã cập nhật vào cơ sở dữ liệu
            return newsRepository.save(existingNews);
        } else {
            // Nếu không tìm thấy tin tức, bạn có thể xử lý hoặc trả về null hoặc thông báo lỗi tùy thuộc vào yêu cầu của bạn
            return null;
        }
    }

    public void delete(Integer id) {
        newsRepository.deleteById(id);
    }

}
