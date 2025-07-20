package io.mj2sdev.shop.model.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDTO {

	protected Long id;
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;
}
