package skills.java.inditex;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import skills.java.inditex.dto.PriceDto;

/*
 * Requirements are as follow:
 * Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:
 * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
 * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
 * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
 * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
 * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
 * 
 * Implementation has been done using the random port strategy and AssertJ framework
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private static int ZARA_BRAND = 1;
	private static int PRODUCT_ID = 35455;
	
	@Test
	public void test1() throws Exception {
		PriceDto price = getPriceFromTemplate(ZARA_BRAND, PRODUCT_ID, LocalDateTime.parse("2020-06-14T10:00:00"));
		
		assertThat(price.getBrandId()).isEqualTo(ZARA_BRAND);
		assertThat(price.getProductId()).isEqualTo(PRODUCT_ID);
		assertThat(price.getPriceList()).isEqualTo(1);
	}
	
	@Test
	public void test2() throws Exception {
		PriceDto price = getPriceFromTemplate(ZARA_BRAND, PRODUCT_ID, LocalDateTime.parse("2020-06-14T16:00:00"));

		assertThat(price.getBrandId()).isEqualTo(ZARA_BRAND);
		assertThat(price.getProductId()).isEqualTo(PRODUCT_ID);
		assertThat(price.getPriceList()).isEqualTo(2);
	}
	
	@Test
	public void test3() throws Exception {
		PriceDto price = getPriceFromTemplate(ZARA_BRAND, PRODUCT_ID, LocalDateTime.parse("2020-06-14T21:00:00"));

		assertThat(price.getBrandId()).isEqualTo(ZARA_BRAND);
		assertThat(price.getProductId()).isEqualTo(PRODUCT_ID);
		assertThat(price.getPriceList()).isEqualTo(1);
	}
	
	@Test
	public void test4() throws Exception {
		PriceDto price = getPriceFromTemplate(ZARA_BRAND, PRODUCT_ID, LocalDateTime.parse("2020-06-15T10:00:00"));

		assertThat(price.getBrandId()).isEqualTo(ZARA_BRAND);
		assertThat(price.getProductId()).isEqualTo(PRODUCT_ID);
		assertThat(price.getPriceList()).isEqualTo(3);
	}
	
	@Test
	public void test5() throws Exception {
		PriceDto price = getPriceFromTemplate(ZARA_BRAND, PRODUCT_ID, LocalDateTime.parse("2020-06-16T21:00:00"));

		assertThat(price.getBrandId()).isEqualTo(ZARA_BRAND);
		assertThat(price.getProductId()).isEqualTo(PRODUCT_ID);
		assertThat(price.getPriceList()).isEqualTo(4);
	}
	
	/*
	 * Auxiliary method to perform requests
	 */
	private PriceDto getPriceFromTemplate(int brand, int productId, LocalDateTime date) {
		URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
				.path("/priceConsulting")
                .queryParam("brandId", brand)
                .queryParam("productId", productId)
                .queryParam("date", date)
                .build().toUri();
		
		return this.restTemplate.getForObject(uri,PriceDto.class);
	}
	
}
