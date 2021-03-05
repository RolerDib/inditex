package skills.java.inditex.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import skills.java.inditex.dao.PriceRepository;
import skills.java.inditex.model.prices.Price;

@Component
public class PriceRepositoryImpl {

	@PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PriceRepository priceRepository;

    @SuppressWarnings("unused")
    public List<Price> getCurrentPriceForProduct(int brand, int productId, Date date) {
        String hql = "SELECT p "
        		+ "FROM Price p "
        		+ "WHERE p.brandId = :brandId "
        		+ "AND p.productId = :productId "
        		+ "AND p.startDate <= :date "
        		+ "AND p.endDate > :date "
        		+ "ORDER BY p.priority ";
        TypedQuery<Price> query = entityManager.createQuery(hql, Price.class);
        query.setParameter("brandId", brand);
        query.setParameter("productId", productId);
        query.setParameter("date", date);
        return query.getResultList();
    }

}
