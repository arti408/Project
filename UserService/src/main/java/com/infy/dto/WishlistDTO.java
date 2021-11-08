package com.infy.dto;

public class WishlistDTO {

	private Integer buyerId;
	private Integer productId;
	
	public Integer getBuyerId() {
		return buyerId;
	}
	
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "WishlistDTO [buyerId=" + buyerId + ", productId=" + productId + "]";
	}
	
	
}