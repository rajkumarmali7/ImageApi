package com.glasses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glasses.entity.Glasses;

public interface GlassesRepository extends JpaRepository<Glasses, Long>{

	Optional<Glasses>findByname(String filename);
}
