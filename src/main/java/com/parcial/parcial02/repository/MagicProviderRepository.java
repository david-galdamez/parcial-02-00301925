package com.parcial.parcial02.repository;

import com.parcial.parcial02.model.MagicProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MagicProviderRepository extends JpaRepository<MagicProvider, UUID> {
}
