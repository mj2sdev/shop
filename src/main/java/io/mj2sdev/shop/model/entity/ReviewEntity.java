package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "review")
public class ReviewEntity extends BaseEntity {
	
	@ManyToOne @JoinColumn
	private AccountEntity account;

	@ManyToOne @JoinColumn
	private ProductEntity product;

	private String image;
	private Integer star;
	private String title;
	private String content;
}