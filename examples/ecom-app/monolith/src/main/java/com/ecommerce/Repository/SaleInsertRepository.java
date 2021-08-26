package com.ecommerce.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Entity.Sale;

@Repository
public class SaleInsertRepository {

	@PersistenceContext 
	private EntityManager entityManager;
	
	@Transactional
	public Long insert(Sale sale) {
		this.entityManager.persist(sale);
		// synchronize the state of the persistence context with the underlying database
		this.entityManager.flush(); 
		return sale.getSaleId();
	}
	
}
