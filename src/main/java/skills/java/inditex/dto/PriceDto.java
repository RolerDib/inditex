package skills.java.inditex.dto;

import java.util.Date;

public class PriceDto {

	private int productId;
	private int brandId;
	private int priceList;
	private Date startDate;
	private Date endDate;
	private String price;
	
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getPriceList() {
		return priceList;
	}
	public void setPriceList(int priceList) {
		this.priceList = priceList;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
