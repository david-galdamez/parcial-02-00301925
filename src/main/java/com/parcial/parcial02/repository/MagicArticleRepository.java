package com.parcial.parcial02.repository;

import com.parcial.parcial02.model.MagicArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MagicArticleRepository extends JpaRepository<MagicArticle, UUID> {
}
