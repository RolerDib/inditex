package skills.java.inditex.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import skills.java.inditex.dao.PriceRepository;
import skills.java.inditex.dto.PriceDto;
import skills.java.inditex.dto.util.DtoConverter;
import skills.java.inditex.model.exceptions.PriceNotFoundException;
import skills.java.inditex.model.prices.Price;


/*
 * Application controller
 */
@RestController
public class PriceConsultingController {

	@Autowired
	PriceRepository priceDao;
	

	/**
	 * As described by the exercise's description, the endpoint must return the actual price for requested brand/product/date information
	 * @param brand - Brand ID to be consulted
	 * @param productId - Product ID to be consulted
	 * @param date - Requesting date for the price
	 * @return A PriceDto object that contains requested information (product, price, date range)
	 */
	@GetMapping("/priceConsulting")
	public PriceDto retrieveFinalPrice(
			@RequestParam(required=true) int brandId, 
			@RequestParam(required=true) int productId, 
			@RequestParam(name="date", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
		
		Price price;
		
		try {
			 price = priceDao.getCurrentPriceForProduct(brandId, productId, date);
		} catch (PriceNotFoundException e) {
			// Here we should manage the exception and return some "no results" response.
			// For the sake of simplicity of this exercise I just return an empty dto
			return new PriceDto();
		}
		
		return DtoConverter.convertToDto.apply(price);
	}
}