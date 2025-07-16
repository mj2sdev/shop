package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReviewImage extends Base {

	@ManyToOne
	private Review review;

	private String image;
}
