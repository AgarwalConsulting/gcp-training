package com.ecommerce.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Entity.Basket;

@Repository
public class BasketInsertRepository {

	@PersistenceContext 
	private EntityManager entityManager;
	
	@Transactional
	public void insert(Basket basket) {
		this.entityManager.persist(basket);
	}
	
}
