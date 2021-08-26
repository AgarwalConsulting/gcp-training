package com.ecommerce.model.repository;

import com.ecommerce.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByCategoryName(String categoryName);

}
