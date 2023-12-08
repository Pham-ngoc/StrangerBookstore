package com.StrangerBookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Setter
@Getter
public class News extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int newsId;
    private String newsTitle;
    private String newsContent;
    private String newsPicture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        News news = (News) o;
        return newsId == news.newsId
                && Objects.equals(newsTitle, news.newsTitle)
                && Objects.equals(newsContent, news.newsContent)
                && Objects.equals(newsPicture, news.newsPicture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), newsId, newsTitle, newsContent, newsPicture);
    }
}
