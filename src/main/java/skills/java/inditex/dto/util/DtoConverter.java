package skills.java.inditex.dto.util;

import java.util.function.Function;

import skills.java.inditex.dto.PriceDto;
import skills.java.inditex.model.prices.Price;

/*
 * Utility class for Entity to Dto mappings
 */
public class DtoConverter {
	public static Function<Price, PriceDto> convertToDto = price -> {
	    PriceDto priceInformation = new PriceDto();

	    priceInformation.setBrandId(price.getBrandId());
		priceInformation.setProductId(price.getProductId());
		priceInformation.setStartDate(price.getStartDate());
		priceInformation.setEndDate(price.getEndDate());
		priceInformation.setPriceList(price.getPriceList());
		
		priceInformation.setPrice(price.getPrice() + " " + price.getCurrency());

	    return priceInformation;
    };
}