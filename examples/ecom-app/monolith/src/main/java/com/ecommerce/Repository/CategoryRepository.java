package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.Entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByCategoryName(String categoryName);
	
}
