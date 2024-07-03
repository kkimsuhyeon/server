package com.project.mybnb.article.controller;

import com.project.mybnb.article.domain.Article;
import com.project.mybnb.article.dto.ArticlePatchDto;
import com.project.mybnb.article.dto.ArticlePostDto;
import com.project.mybnb.article.dto.ArticleResponseDto;
import com.project.mybnb.article.mapper.ArticleMapper;
import com.project.mybnb.article.repository.ArticleRepository;
import com.project.mybnb.article.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    //숙소 등록
    @PostMapping("/posts")
    public ResponseEntity addArticle(@Valid @RequestBody ArticlePostDto articleDto) {

      Article article= articleService.createArticle(articleMapper.createArticeDto(articleDto));

      return  new ResponseEntity (articleMapper.createToResposnseDto(article),HttpStatus.CREATED);

    }

    //숙소 수정
    @PatchMapping("/{article-id}")
    public ResponseEntity updateArticle(@Valid @RequestBody ArticlePatchDto articleDto,
    @PathVariable("article-id") long article_id) throws Exception {

        Article article= articleService.updateArticle(articleMapper.updateArticleDto(articleDto),article_id);

        return new ResponseEntity (articleMapper.updateToResposnseDto(article),HttpStatus.OK);
    }
}
