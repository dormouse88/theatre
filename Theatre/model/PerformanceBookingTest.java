/**
 * 
 */
package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author soumyakcherian
 *
 */
class PerformanceBookingTest {
	
	
	int id = 1;
	int showID = 23;
	String title= "testtitle";
	String type = "musicals";
	String description = "description of show";
	int duration = 120;
	String language = "english";
	String performer = "Performer1";
	
	LocalDate dateStatic = LocalDate.of(2022, 7, 22);
	LocalDate dateDynamic = LocalDate.now().plusDays(-5);
	Boolean isMatinee = true;
	int availabilityStalls = 120;
	int availabilityCircle = 80;
	int priceStalls = 450;
	int priceCircle = 2450;
	
	Show show = null;
	
	Performance performance1 = null;
	Performance performance2 = null;

	@BeforeEach
	public void setUp() {
		ArrayList<String> performers = new ArrayList<String>();
		performers.add("Performer1");
		performers.add("Performer2");
		show = new Show(showID, title, type, description, duration, language, performers );
		performance1 = new Performance(id,show, dateStatic,isMatinee,availabilityStalls, availabilityCircle, priceStalls, priceCircle);
		performance2 = new Performance(id,show, dateDynamic,isMatinee,availabilityStalls, availabilityCircle, priceStalls, priceCircle);
	}

	
	/**
	 * Test method for {@link model.PerformanceBooking#PerformanceBooking(model.Performance, java.lang.Boolean, int, int)}.
	 */
	@Test
	void testPerformanceBookingConstructor() {
		PerformanceBooking performanceBooking = new PerformanceBooking(performance1,true,2,3);
		assertNotNull(performanceBooking, "Verifying Performance object creation ");
		assertEquals(performance1,performanceBooking.getPerformance(),"Verifying getPerformance");
		assertTrue(performanceBooking.getStalls(),"Verfying getStalls");
		assertEquals(2, performanceBooking.getAdults(),"Verifying getAdults");
		assertEquals(3, performanceBooking.getKids(), "Verifying getKids");
	}


	/**
	 * Test method for {@link model.PerformanceBooking#calculatePrice()}.
	 */
	@Test
	void testCalculatePrice() {
		PerformanceBooking performanceBooking = new PerformanceBooking(performance2,true,2,3);
		assertEquals(765, performanceBooking.calculatePrice(),"Verifying stall total performance booking cost");
		
		PerformanceBooking performanceBooking2 = new PerformanceBooking(performance2,false,2,3);
		assertEquals(4165, performanceBooking2.calculatePrice(),"Verifying circle total performance booking cost");
	}
	
	/**
	 * Test method for {@link model.PerformanceBooking#print()}.
	 */
	@Test
	void testPrint() {
		
		PerformanceBooking performanceBooking = new PerformanceBooking(performance1,true,2,3);
		String printedString = performanceBooking.print();
		
		assertTrue(printedString.contains(performance1.getShow().getTitle()), "Verify title in print string.");
		assertTrue(printedString.contains(performance1.getMatString()), "Verify getMatString in print string.");
		assertTrue(printedString.contains(performance1.getDate().toString()), "Verify getDate in print string.");
		assertTrue(printedString.contains("2022-07-22"), "Verify getDate in print string.");
		assertTrue(printedString.contains("2 adults"), "Verify adult tickets in print string.");
		assertTrue(printedString.contains("3 children"), "Verify children tickets in print string.");
	}

	
}
