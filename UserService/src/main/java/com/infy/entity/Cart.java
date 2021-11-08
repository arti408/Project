package com.infy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@Column(name = "buyerid",nullable = false)
	private Integer buyerId;
	@Column(name = "prodid",nullable = false)
	private Integer productId;
	@Column(name = "quantity",nullable = false)
	private Integer quantity;
	
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
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyerId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "Cart [buyerId=" + buyerId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
		
}
