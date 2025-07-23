package io.mj2sdev.shop.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.mj2sdev.shop.model.entity.FileEntity;
import io.mj2sdev.shop.repository.FileRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileManager implements FileService {

	private final FileRepo fileRepo;

	private final String FOLDER_NAME = "uploads";

	private final String UPLOAD_PATH =
		System.getProperty("user.home") + File.separator + "Documents" + File.separator + FOLDER_NAME;

	@Override
	public List<FileEntity> save(List<MultipartFile> files) {
		List<FileEntity> list = new ArrayList<>();
		for (MultipartFile file : files) {
			final FileEntity entity = save(file);
			list.add(entity);
		}
		return list;
	}

	@Override
	public FileEntity save(MultipartFile file) {
		final String hash = getHash(file);
		final String originName = file.getOriginalFilename();
		final String contentType = file.getContentType();

		if (!StringUtils.hasText(originName))
			throw new IllegalArgumentException("multipart file 내부에 orignalFilename이 비어있음");
		
		String extension = "";
		int dotIndex = originName.lastIndexOf(".");
		if (dotIndex >= 0) {
			extension = originName.substring(dotIndex);
		}
		String hashedName = hash + extension;

		FileEntity entity = FileEntity.builder()
			.originName(originName)
			.hashedName(hashedName)
			.contentType(contentType)
			.extension(extension)
			.path(UPLOAD_PATH + File.separator + hashedName)
			.url("/" + FOLDER_NAME + "/" + hashedName)
			.size(file.getSize())
			.build();
			
		boolean finded = fileRepo.existsByHashedName(hashedName);
		if (!finded) {
			saveFile(file, hashedName);
			fileRepo.save(entity);
		}
		return entity;
	}

	private void saveFile(MultipartFile file, String filename) {
		try {
			File dest = new File(UPLOAD_PATH, filename);
			dest.getParentFile().mkdirs();
			file.transferTo(dest);
		} catch (IOException e) {
			throw new RuntimeException("파일 저장 실패", e);
		}
	}

	private String getHash(MultipartFile file) {
		try (InputStream is = file.getInputStream()) {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1) {
				digest.update(buffer, 0, bytesRead);
			}
			byte[] hashBytes = digest.digest();
			return Hex.encodeHexString(hashBytes);
		} catch (Exception e) {
			throw new RuntimeException("Hash calculation failed", e);
		}
	}
}
