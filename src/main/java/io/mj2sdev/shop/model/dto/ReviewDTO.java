package io.mj2sdev.shop.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
	private Long accountId;
	private Long productId;
	private Integer star;
	private String image;
	private String title;
	private String content;
}
