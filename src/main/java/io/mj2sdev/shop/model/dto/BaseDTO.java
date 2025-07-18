package io.mj2sdev.shop.model.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public abstract class BaseDTO {

	protected Long id;
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;
}
