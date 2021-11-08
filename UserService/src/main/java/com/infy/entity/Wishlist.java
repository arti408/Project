package com.infy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wishlist {
	@Id
	@Column(name = "buyerid",nullable = false)
	private Integer buyerId;
	@Column(name = "prodid",nullable = false)
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
		Wishlist other = (Wishlist) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(productId, other.productId);
	}



	@Override
	public String toString() {
		return "Wishlist [buyerId=" + buyerId + ", productId=" + productId + "]";
	}
	
		
}
