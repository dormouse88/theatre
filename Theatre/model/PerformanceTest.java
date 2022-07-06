/**
 * 
 */
package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author soumyakcherian
 *
 */
class PerformanceTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	
	int id = 1;
	int showID = 23;
	String title= "testtitle";
	String type = "musicals";
	String description = "description of show";
	int duration = 120;
	String language = "english";
	String performer = "Performer1";
	Show show = new Show(showID, title, type, description, duration, language, performer);
	
	LocalDate date = LocalDate.of(2022, 7, 22);
	Boolean isMatinee = true;
	int availabilityStalls = 120;
	int availabilityCircle = 80;
	int price = 50;

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@AfterEach
	public void tearDown() {
	    System.setOut(standardOut);
	}

	/**
	 * Test method for {@link model.Performance#Performance(int, model.Show, java.time.LocalDate, java.lang.Boolean, int, int, int)}.
	 */
	@Test
	void testPerformanceConstructor() {
		
		Performance performance = new Performance(id,show, date,isMatinee,availabilityStalls, availabilityCircle, price);
		
		assertNotNull(performance, "Verify performance constructor");
		assertEquals(id, performance.getID(),"Verify getID");
		assertNotNull(performance.getShow(), "Verify getShow");
		assertEquals(date, performance.getDate(), "Verify performace getDate");
		assertEquals(isMatinee,performance.getMatinee(),"Verify getMatinee");
	    assertEquals(availabilityStalls,performance.getAvailabilityStalls(),"Verify getAvailabilityStalls");
		assertEquals(availabilityCircle,performance.getAvailabilityCircle(),"Verify getAvailabilityCircle");
		assertEquals(price, performance.getPrice(), "Verify getPrice");
	}

	/**
	 * Test method for {@link model.Performance#print()}.
	 */
	@Test
	void testPrint() {
		
		Performance performance = new Performance(id,show, date,isMatinee,availabilityStalls, availabilityCircle, price);
		performance.print();
		
		assertTrue(outputStreamCaptor.toString().contains(performance.getShow().getTitle()), "Verify title is printed out.");
		assertTrue(outputStreamCaptor.toString().contains(performance.getMatString()), "Verify getMatString is printed out.");
		assertTrue(outputStreamCaptor.toString().contains(Integer.toString(availabilityStalls)), "Verify count of availabilityStalls is printed out");
		assertTrue(outputStreamCaptor.toString().contains(Integer.toString(availabilityCircle)), "Verify count of availabilityCircle is printed out");
	}

	/**
	 * Test method for {@link model.Performance#getMatString()}.
	 */
	@Test
	void testGetMatString() {
		
		isMatinee = true;
		Performance performance = new Performance(id,show, date,isMatinee,availabilityStalls, availabilityCircle, price);
		assertEquals("matinee", performance.getMatString(), "Verify getMatString matinee");
		
		isMatinee = false;
		Performance performance2 = new Performance(id,show, date,isMatinee,availabilityStalls, availabilityCircle, price);
		assertEquals("evening", performance2.getMatString(), "Verify getMatString evening");
	}

	/**
	 * Test method for {@link model.Performance#getPriceAsString(int)}.
	 */
	@Test
	void testGetPriceAsString() {
		price=5525;
		assertEquals("£55.25", Performance.getPriceAsString(price), "Verify getPriceAsString");
	}

}
