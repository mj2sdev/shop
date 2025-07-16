package io.mj2sdev.shop.model.dto;

import java.time.LocalDateTime;

import io.mj2sdev.shop.model.entity.Base;
import lombok.Getter;

@Getter
public abstract class BaseDTO {

	protected Long id;
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;

	protected void Copy(Base baseEntity) {
		this.id = baseEntity.getId();
		this.createdAt = baseEntity.getCreatedAt();
		this.updatedAt = baseEntity.getUpdatedAt();
	}
}
