package com.parcial.parcial02.service;

import com.parcial.parcial02.dto.ArticleDetailsDto;
import com.parcial.parcial02.dto.ArticleDto;
import com.parcial.parcial02.dto.ArticleMapper;
import com.parcial.parcial02.dto.CreateArticleDto;
import com.parcial.parcial02.exception.BusinessRuleException;
import com.parcial.parcial02.exception.DuplicatedName;
import com.parcial.parcial02.exception.EntityNotFoundException;
import com.parcial.parcial02.model.MagicArticle;
import com.parcial.parcial02.model.enums.MagicType;
import com.parcial.parcial02.repository.MagicArticleRepository;
import com.parcial.parcial02.repository.MagicProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MagicArticleService {
    private final MagicArticleRepository articleRepository;
    private final MagicProviderRepository magicProviderRepository;

    public List<ArticleDto> getAllArticles(UUID provider, MagicType category, BigDecimal maxPrice) {
        List<MagicArticle> articles = new ArrayList<>();

        if(provider != null && category != null) {
            articles = articleRepository.findByTypeAndProviderId(category, provider);
        } else if(provider != null) {
            articles = articleRepository.findByProviderId(provider);
        } else if(category != null) {
            articles = articleRepository.findByType(category);
        } else if(maxPrice != null) {
            articles = articleRepository.findByPriceLessThanEqual(maxPrice);
        }

        return articles.stream()
                .map(ArticleMapper::toDto)
                .toList();
    }

    public ArticleDetailsDto getById(UUID id) {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Articulo no encontrado"));

        return ArticleMapper.toDetailsDto(article);
    }

    public ArticleDto createArticle(CreateArticleDto request) {
        var provider = magicProviderRepository.findById(request.provider())
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));

        var magicArticle = ArticleMapper.toEntity(request, provider);

        var exists = articleRepository.findByNameLike(magicArticle.getName());
        if(exists.isPresent()) {
            throw new DuplicatedName("El articulo ya esta registrado");
        }

        if(!provider.getType().equals(magicArticle.getType())) {
            throw new BusinessRuleException("El tipo del articulo es diferente al del proveedor");
        }

        BigDecimal zero = new BigDecimal("0.00");
        if(magicArticle.getPrice().compareTo(zero) <= 0) {
            throw new BusinessRuleException("El precio del articulo no puede ser menor a 0");
        }

        articleRepository.save(magicArticle);

        return ArticleMapper.toDto(magicArticle);
    }

    public ArticleDto updateArticle(CreateArticleDto request, UUID id) {

        var provider = magicProviderRepository.findById(request.provider())
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));

        var article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Articulo no encontrado"));

        if(!provider.getType().equals(request.type())) {
            throw new BusinessRuleException("El tipo del articulo es diferente al del proveedor");
        }

        BigDecimal zero = new BigDecimal("0.00");
        if(request.price().compareTo(zero) <= 0) {
            throw new BusinessRuleException("El precio del articulo no puede ser menor a 0");
        }

        article.setName(request.name());
        article.setPrice(request.price());
        article.setType(request.type());
        article.setProvider(provider);

        articleRepository.save(article);

        return ArticleMapper.toDto(article);
    }

    public void deleteArticle(UUID id) {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Articulo no encontrado"));

        articleRepository.delete(article);
    }
}
