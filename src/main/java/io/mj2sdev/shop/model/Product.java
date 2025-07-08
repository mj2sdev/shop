package io.mj2sdev.shop.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Product {
	
	@Id
	private Integer pseq;
	private String name;
	private String kind;
	private Integer price1;
	private Integer price2;
	private Integer price3;
	private String content;
	private String image;
	private boolean useyn;
	private boolean bestyn;
	private LocalDateTime indate;
}
