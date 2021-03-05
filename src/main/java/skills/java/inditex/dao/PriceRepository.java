package skills.java.inditex.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skills.java.inditex.model.prices.Price;
import skills.java.inditex.model.prices.PriceId;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId>{
	
	public List<Price> getPricesByProductId(int productId);
	
	public List<Price> getCurrentPriceForProduct(int brand, int productId, Date date);
}
