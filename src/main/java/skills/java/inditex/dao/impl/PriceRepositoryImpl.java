package skills.java.inditex.dao.impl;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import skills.java.inditex.model.exceptions.PriceNotFoundException;
import skills.java.inditex.model.prices.Price;

/*
 * Implementation of PriceRepository using Spring Data JPA
 * Naming convention by documentation is same as interface + "Impl"
 */
@Component
public class PriceRepositoryImpl {

	@PersistenceContext
    private EntityManager entityManager;

    public Price getCurrentPriceForProduct(int brandId, int productId, LocalDateTime date) throws PriceNotFoundException {
        String hql = "SELECT p "
        		+ "FROM Price p "
        		+ "WHERE p.brandId = :brandId "
        		+ "AND p.productId = :productId "
        		+ "AND p.startDate <= :date "
        		+ "AND p.endDate > :date "
        		+ "ORDER BY p.priority DESC ";
        TypedQuery<Price> query = entityManager.createQuery(hql, Price.class);
        query.setParameter("brandId", brandId);
        query.setParameter("productId", productId);
        query.setParameter("date", date);
        query.setMaxResults(1); 
        
        try {
        	return query.getSingleResult();
        } catch (NoResultException e) {
			throw new PriceNotFoundException ();
		}
    }

}
