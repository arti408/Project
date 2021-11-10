package com.training.dto;

import com.training.entity.SubscribedProduct;

public class SubscribedProductDTO {
	
	private String buyerId;
	private String productId;
	private Integer quantity;
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public SubscribedProductDTO() {
		super();
	}

	// Converts Entity into DTO
	public static SubscribedProduct valueOf(SubscribedProduct product) {
		SubscribedProduct subDTO= new SubscribedProduct();
		subDTO.setBuyerId(product.getBuyerId());
		subDTO.setProdId(product.getProductId());
		subDTO.setQuantity(product.getQuantity());
		return subDTO;
	}
	@Override
	public String toString() {
		return "subDTO [buyerId=" + buyerId + ", prodId=" + productId + ", quantity=" + quantity
				+ "]";
	}

}
