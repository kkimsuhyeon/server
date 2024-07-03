package com.project.mybnb.article.service;

import com.project.mybnb.article.domain.Article;
import com.project.mybnb.article.dto.ArticlePostDto;
import com.project.mybnb.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article createArticle(ArticlePostDto articlePostDto){

        Article article= new Article();

        article.setTitle(articlePostDto.getTitle());
        article.setContent(articlePostDto.getContent());
        article.setCategory(articlePostDto.getCategory());
        article.setLocation(articlePostDto.getLocation());
        article.setPrice(articlePostDto.getPrice());

        return articleRepository.save(article);

    }




}
