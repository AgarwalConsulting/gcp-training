package com.ecommerce.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Entity.SalesDetail;

@Repository
public class SalesDetailInsert {

	@PersistenceContext 
	private EntityManager entityManager;
	
	@Transactional
	public void insert(SalesDetail salesDetail) {
		this.entityManager.persist(salesDetail);
	}
	
}
