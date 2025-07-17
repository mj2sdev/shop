package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review extends Base {
	
	@ManyToOne @JoinColumn
	private Account account;

	@ManyToOne @JoinColumn
	private Product product;

	private String image;
	private Integer star;
	private String title;
	private String content;
}