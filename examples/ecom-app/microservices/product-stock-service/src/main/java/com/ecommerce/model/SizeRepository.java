package com.ecommerce.model;

import org.springframework.data.repository.CrudRepository;

public interface SizeRepository extends CrudRepository<Size, Long> {

    Size findBySizeId(Long sizeId);

}
