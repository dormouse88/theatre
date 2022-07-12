
package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/*
 * Author: Andrew Nguyen
 */
class PriceTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}



	@Test
	public void testConstructor() {
		//basePrice (to be returned by db) is set to 300, the paidprice should be 300 without any discounts. Current discount is 0.2% but now applied yet.
		Price price = new Price(300, 200);
		assertEquals(300, price.getPaidPrice());
		assertEquals(0.2, price.getDiscount());
		assertEquals(0, price.getDiscountLevel());
		}
	
	
	@Test
	public void applyDiscountTest() {
		//applies discount to the returned paid price.
		Price price = new Price(100, 200);
		price.applyDiscounts();
		assertEquals(100, price.getPaidPrice());
		price.setDiscountLevel(1);
		price.applyDiscounts();
		assertEquals(80, price.getPaidPrice());
		price.setPaidPrice(100);
		price.setDiscountLevel(2);
		price.applyDiscounts();
		assertEquals(60, price.getPaidPrice());
		price.setPaidPrice(100);
		price.setDiscountLevel(3);
		price.applyDiscounts();
		assertEquals(40, price.getPaidPrice());
	}
	
	@Test
	public void getDateDiscountTest() {
		//discounts should initially be 0, but if date is <7 days in advance, then discount level is incremented.
		Price price = new Price(100, 200);
		assertEquals(100, price.getPaidPrice());
		assertEquals(0, price.getDiscountLevel());
		assertFalse( price.isDateDiscount(LocalDate.now()) );
		// no discount level increases applied as the ticket is being bought on the day.
		assertTrue( price.isDateDiscount(LocalDate.parse("2025-01-01")) ); //set day in the future; could also do an assertTrue here to see if it is successful
		//assertEquals(1, price.getDiscountLevel());
		
	}
	 
	
	
	@Test 
	public void getRemainingSeatsDiscountTest() {
		//if number of total remaining seats is >30%, the discount level is incremented.
		Price price = new Price(100, 200);
		//100% availability, level 1 discount observed, paidPrice is cut by 20% and should return 80.
		assertEquals(100, price.getAvailability(200));
		assertTrue(price.isHighAvailabilityDiscount(200));
		//assertEquals(1, price.getDiscountLevel());
		//price.applyDiscounts();
		//assertEquals(80, price.getPaidPrice());
		
		Price price2 = new Price(100, 200);
		//set availability down to <30%
		assertEquals(30, price2.getAvailability(60));
		assertFalse(price2.isHighAvailabilityDiscount(60));
		//assertEquals(0, price2.getDiscountLevel());
		//price.applyDiscounts();
		//assertEquals(100, price2.getPaidPrice());
	}
	
	
	
	public void getFlashSaleDiscountTest() {
		//if number of seats remaining is >30% and days until performance is <1, then max discount level is applied (set to 3).
		Price price = new Price(100, 200);
	}
	
	@Test
	public void doAllDiscountsTest() {
		Price price = new Price(100, 200);
		int d1 = price.DoAllDiscounts(200, LocalDate.now().plusDays(17));
		assertEquals(60, d1);
	}
}

