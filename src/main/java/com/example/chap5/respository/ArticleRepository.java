package com.example.chap5.respository;

import com.example.chap5.entity.Article;
import com.example.chap5.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByWriter(Member writer);

    void deleteByWriter(Member writer);
}
