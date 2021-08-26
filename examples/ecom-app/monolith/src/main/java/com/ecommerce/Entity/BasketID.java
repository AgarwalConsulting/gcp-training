package com.ecommerce.Entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BasketID implements Serializable {
	
	private Long userId;	
	private Long productId;
	private Long sizeId;
	
	public BasketID() { }
	
	public BasketID(Long userId, Long productId, Long sizeId) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.sizeId = sizeId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((sizeId == null) ? 0 : sizeId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketID other = (BasketID) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (sizeId == null) {
			if (other.sizeId != null)
				return false;
		} else if (!sizeId.equals(other.sizeId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	
}
