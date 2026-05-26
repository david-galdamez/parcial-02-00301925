package com.parcial.parcial02.repository;

import com.parcial.parcial02.model.MagicArticle;
import com.parcial.parcial02.model.enums.MagicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MagicArticleRepository extends JpaRepository<MagicArticle, UUID> {
    Optional<MagicArticle> findByNameLike(String name);
    List<MagicArticle> findByProviderId(UUID id);

    List<MagicArticle> findByType(MagicType type);
    List<MagicArticle> findByPriceLessThanEqual(BigDecimal price);

    List<MagicArticle> findByTypeAndProviderId(MagicType type, UUID id);
}
