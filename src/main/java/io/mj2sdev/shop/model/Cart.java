package io.mj2sdev.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart extends Common {
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	private Integer amount;
	private Integer totalPrice;
	
	// 자 설계 해 봅시다
	/**
	 * 상품 - 유저 다대다
	 * 상품 - 카트 - 유저
	 * 
	 * 1상품 - N유저의상품(카트) 하나의 상품이 여러유저에게 담길 수 있음 
	 * 1유저 - N유저의상품(카트) 한명의 유저는 여러 상품을 담을 수 있음 
	 */

	//  1상품 - 다 카트 - 1유저 
}
