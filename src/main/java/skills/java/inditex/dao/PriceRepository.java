package skills.java.inditex.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skills.java.inditex.model.exceptions.PriceNotFoundException;
import skills.java.inditex.model.prices.Price;

/*
 * CRUD methods provided by Spring Data JPA to be used with Price entity
 * It has a custom method to be implemented
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Integer>{
	
	/**
	 * Return the current price for the given parameters
	 * @param brand - Brand ID to be consulted
	 * @param productId - Product ID to be consulted
	 * @param date - Requesting date for the price
	 * @return A Price object that matches the given parameters, with highest priority whenever several matches happen
	 * @throws PriceNotFoundException if no price has been found
	 */
	public Price getCurrentPriceForProduct(int brandId, int productId, LocalDateTime date) throws PriceNotFoundException;
}
