package com.example.chap5.service;

import com.example.chap5.entity.Article;
import com.example.chap5.entity.Member;
import com.example.chap5.respository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article create(Article article, Member writer) {
        article.setWriter(writer);
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findMyArticles(Member writer) {
        return articleRepository.findByWriter(writer);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));
    }

    public void update(Long id, Article formArticle) {
        Article article = findById(id);
        article.setTitle(formArticle.getTitle());
        article.setContent(formArticle.getContent());
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public void deleteByWriter(Member writer) {
        articleRepository.deleteByWriter(writer);
    }
}
