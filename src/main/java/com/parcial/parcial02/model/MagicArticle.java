package com.parcial.parcial02.model;

import com.parcial.parcial02.model.enums.MagicType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "magic_article")
public class MagicArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MagicType type;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private MagicProvider provider;
}
