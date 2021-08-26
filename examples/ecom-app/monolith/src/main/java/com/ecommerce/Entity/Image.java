package com.ecommerce.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {

	protected Image() {}
	
	public Image(Long productId, String imageUrl) {
		this.productId = productId;
		this.imageUrl = imageUrl;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long imageId;
	private Long productId;
	private String imageUrl;
	
	
	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
	
}
