package com.ecommerce.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sale {
	
	protected Sale() { }

	public Sale(Long userId, Date orderDate) {
		this.userId = userId;
		this.orderDate = orderDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long saleId;
	
	private Long userId;
	private Date orderDate;
	
	
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
