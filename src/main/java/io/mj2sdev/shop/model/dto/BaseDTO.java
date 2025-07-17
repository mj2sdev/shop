package io.mj2sdev.shop.model.dto;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.mj2sdev.shop.model.entity.Base;
import lombok.Getter;

@Getter
public abstract class BaseDTO {

	protected Long id;
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;
}
