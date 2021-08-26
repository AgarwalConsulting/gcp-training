package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.Entity.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {

	List<Image> findByProductId(Long productId);
	
}
