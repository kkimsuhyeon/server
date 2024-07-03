package com.project.mybnb.article.mapper;


import com.project.mybnb.article.domain.Article;
import com.project.mybnb.article.dto.ArticlePatchDto;
import com.project.mybnb.article.dto.ArticlePostDto;
import com.project.mybnb.article.dto.ArticleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticlePostDto createArticeDto(ArticlePostDto articleDto);

    ArticleResponseDto createToResposnseDto(Article article);

    ArticleResponseDto updateToResposnseDto(Article article);

    ArticlePatchDto updateArticleDto(ArticlePatchDto articleDto);
}
