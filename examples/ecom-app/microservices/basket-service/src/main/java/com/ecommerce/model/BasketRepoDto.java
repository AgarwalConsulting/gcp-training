package com.ecommerce.model;

// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections
public interface BasketRepoDto {

    // i is intentionally lower-case due to JPA limitations.
    Long getProductid();

    Integer getQuantity();

}
