package io.mj2sdev.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.mj2sdev.shop.model.entity.FileEntity;

public interface FileService {
	
	public List<FileEntity> save(List<MultipartFile> files);

	public FileEntity save(MultipartFile file);
}
