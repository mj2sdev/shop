package io.mj2sdev.shop.model.entity;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "file")
@SQLRestriction("deleted = false")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FileEntity extends BaseEntity {
	
	private String originName;
	private String hashedName;
	private String path;
	private String url;
	private String contentType;
	private String extension;
	private Long size;
}
