package com.parcial.parcial02.dto;

import com.parcial.parcial02.model.MagicArticle;
import com.parcial.parcial02.model.MagicProvider;

public class ArticleMapper {
    public static ArticleDto toDto(MagicArticle article) {
        return new ArticleDto(article.getId(), article.getName(), article.getType(), article.getPrice());
    }

    public static ArticleDetailsDto toDetailsDto(MagicArticle article) {
        return new ArticleDetailsDto(article.getId(), article.getName(), article.getType(), article.getPrice(), article.getProvider());
    }

    public static MagicArticle toEntity(CreateArticleDto request, MagicProvider provider) {
        return MagicArticle.builder()
                .name(request.name())
                .price(request.price())
                .type(request.type())
                .provider(provider)
                .build();
    }
}
