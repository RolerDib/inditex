package skills.java.inditex.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import skills.java.inditex.dao.PriceRepository;
import skills.java.inditex.dto.PriceDto;
import skills.java.inditex.model.prices.Price;


@RestController
public class PriceConsultingController {

	@Autowired
	PriceRepository priceDao;
	

	@GetMapping("/priceConsulting")
	public PriceDto retrieveFinalPrice(
			@RequestParam(required=true) int brand, 
			@RequestParam(required=true) int productId, 
			@RequestParam(name="date", required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date askingDate) throws Exception {
		PriceDto priceInformation = new PriceDto();
		
		List<Price> prices = priceDao.getCurrentPriceForProduct(brand, productId, askingDate);
		
		if(prices.isEmpty()) {
			throw new Exception ("No prices found!");
		}
		
		populatePrice(prices.get(0), priceInformation);
		
		return priceInformation;
	}
	
	private void populatePrice (Price price, PriceDto priceInformation) {
		priceInformation.setBrandId(price.getBrandId());
		priceInformation.setProductId(price.getProductId());
		priceInformation.setStartDate(price.getStartDate());
		priceInformation.setEndDate(price.getEndDate());
		
		priceInformation.setPrice(price.getPrice() + " " + price.getCurrency());
	}
}