package com.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(Stock.PK.class)
public class Stock {

	@Id
	private Long productId;

	@Id private Long sizeId;

	private Integer stock;

	@EqualsAndHashCode
	public static class PK implements Serializable {

		private Long productId;

		private Long sizeId;

	}

}
