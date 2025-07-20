package io.mj2sdev.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.entity.FileEntity;

@Repository
public interface FileRepo extends JpaRepository<FileEntity, Long> {
	
	boolean existsByHashedName(String hashedName);
}
