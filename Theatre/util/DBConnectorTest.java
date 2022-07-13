/**
 * 
 */
package util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.Performance;
import model.PerformanceBooking;
import model.Show;

/**
 * @author soumyakcherian
 *
 */
class DBConnectorTest {
	static DBConnector dbConnector = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dbConnector = new DBConnector();
		dbConnector.connect();
		dbConnector.createDatabase();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dbConnector.getConnection().setAutoCommit(false);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dbConnector.getConnection().rollback();
	}

	/**
	 * Test method for {@link util.DBConnector#getAllShows()}.
	 */
	@Test
	void testGetAllShows() {
		//Already one record in DB via dbConnector.createDatabase()
		//Inserting extra show to Showing table
		String sql = "INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) "
				+ "VALUES (\"Mamma Mia-2\", 210, \"Spanish\", \"Mamma Mia-2! is a jukebox\",3);\n";
		dbConnector.executeQuery(sql);
		
		ArrayList<Show> showList = dbConnector.getAllShows();
		
		assertEquals(2, showList.size(), "Verify only 2 shows.");
		
		Show show1 = showList.get(0);
		assertEquals("Mamma Mia", show1.getTitle(), "Verify getTitle for first show.");
		assertEquals(195, show1.getDuration(), "Verify getDuration for first show.");
		assertEquals("English", show1.getLanguage(), "Verify getLanguage for first show.");
		assertEquals("Musical", show1.getType(), "Verify getType for first show.");
		String infoForFirstShow = "Mamma Mia! is a jukebox musical written by British playwright Catherine Johnson, "
				+ "based on the songs of ABBA composed by Benny Andersson and Björn Ulvaeus, members of the band. "
				+ "The title of the musical is taken from the group's 1975 chart-topper 'Mamma Mia'.";
		assertEquals(infoForFirstShow, show1.getDescription(), "Verify getDescription for first show.");
		
		Show show2 = showList.get(1);
		assertEquals("Mamma Mia-2", show2.getTitle(), "Verify getTitle for second show.");
		assertEquals(210, show2.getDuration(), "Verify getDuration for second show.");
		assertEquals("Spanish", show2.getLanguage(), "Verify getLanguage for first show.");
		assertEquals("Opera", show2.getType(), "Verify getType for second show.");
		assertEquals("Mamma Mia-2! is a jukebox", show2.getDescription(), "Verify getDescription for second show.");
		
	}

	/**
	 * Test method for {@link util.DBConnector#getShowsByTitle(java.lang.String)}.
	 */
	@Test
	void testGetShowsByTitle() {
		//Already one record in DB via dbConnector.createDatabase()
		//Inserting extra show to Showing table
		String sql = "INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) "
				+ "VALUES (\"Mamma Mia-2\", 210, \"Spanish\", \"Mamma Mia-2! is a jukebox\",3);\n";
		dbConnector.executeQuery(sql);
		
		//Insert extra show with different title in Showing table
		sql = "INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) "
				+ "VALUES (\"Mia-3\", 220, \"French\", \"Mia-3! is a jukebox\",4);\n";
		dbConnector.executeQuery(sql);
		
		ArrayList<Show> showList = dbConnector.getShowsByTitle("Mamma");
		
		assertEquals(2, showList.size(), "Verify only 2 shows that contains Mamma as title");
		
		Show show1 = showList.get(0);
		assertEquals("Mamma Mia", show1.getTitle(), "Verify getTitle for first show.");
		assertEquals(195, show1.getDuration(), "Verify getDuration for first show.");
		assertEquals("English", show1.getLanguage(), "Verify getLanguage for first show.");
		assertEquals("Musical", show1.getType(), "Verify getType for first show.");
		String infoForFirstShow = "Mamma Mia! is a jukebox musical written by British playwright Catherine Johnson, "
				+ "based on the songs of ABBA composed by Benny Andersson and Björn Ulvaeus, members of the band. "
				+ "The title of the musical is taken from the group's 1975 chart-topper 'Mamma Mia'.";
		assertEquals(infoForFirstShow, show1.getDescription(), "Verify getDescription for first show.");
		
		
		Show show2 = showList.get(1);
		assertEquals("Mamma Mia-2", show2.getTitle(), "Verify getTitle for second show.");
		assertEquals(210, show2.getDuration(), "Verify getDuration for second show.");
		assertEquals("Spanish", show2.getLanguage(), "Verify getLanguage for first show.");
		assertEquals("Opera", show2.getType(), "Verify getType for second show.");
		assertEquals("Mamma Mia-2! is a jukebox", show2.getDescription(), "Verify getDescription for second show.");
		
		
		ArrayList<Show> showList2 = dbConnector.getShowsByTitle("Mia-3");
		
		assertEquals(1, showList2.size(), "Verify only 1 show that contains Mia-3 as title");
		
		Show show3 = showList2.get(0);
		assertEquals("Mia-3", show3.getTitle(), "Verify getTitle Mia-3 show.");
		assertEquals(220, show3.getDuration(), "Verify getDuration Mia-3 show.");
		assertEquals("French", show3.getLanguage(), "Verify getLanguage Mia-3 show.");
		assertEquals("Concert", show3.getType(), "Verify getType Mia-3 show.");
		assertEquals("Mia-3! is a jukebox", show3.getDescription(), "Verify getDescription Mia-3 show.");
		
	}

	/**
	 * Test method for {@link util.DBConnector#getPerformancesByShowID(int)}.
	 */
	@Test
	void testGetPerformancesByShowID() {
		
		ArrayList<Performance> performanceList = dbConnector.getPerformancesByShowID(1);
		assertEquals(2, performanceList.size(), "Verify 2 performance for showId 1");
		
		Performance performance1 = performanceList.get(0);
		assertNotNull(performance1.getShow(), "Verify getShow");
		LocalDate date = LocalDate.of(2022, 7, 13);
		assertEquals(date, performance1.getDate(), "Verify performace getDate");
		assertTrue(performance1.getMatinee(),"Verify getMatinee");
	    assertEquals(115,performance1.getAvailabilityStalls(),"Verify getAvailabilityStalls");
		assertEquals(80,performance1.getAvailabilityCircle(),"Verify getAvailabilityCircle");
		assertEquals(1600, performance1.getPriceStalls(), "Verify getPrice Stalls");
		assertEquals(1600, performance1.getPriceCircle(), "Verify getPrice Circle");
		
		Performance performance2 = performanceList.get(1);
		assertNotNull(performance2.getShow(), "Verify getShow");
		LocalDate date2 = LocalDate.of(2022, 7, 13);
		assertEquals(date2, performance2.getDate(), "Verify performace getDate");
		assertFalse(performance2.getMatinee(),"Verify getMatinee");
	    assertEquals(120,performance2.getAvailabilityStalls(),"Verify getAvailabilityStalls");
		assertEquals(80,performance2.getAvailabilityCircle(),"Verify getAvailabilityCircle");
		assertEquals(1600, performance2.getPriceStalls(), "Verify getPrice Stalls");
		assertEquals(1600, performance2.getPriceCircle(), "Verify getPrice Circle");
	}

	/**
	 * Test method for {@link util.DBConnector#getPerformancesByDate(java.time.LocalDate)}.
	 */
	@Test
	void testGetPerformancesByDate() {
		
		String sql1 = "INSERT INTO Performance (pdate, ptime, ShowingID) VALUES (\"2022-07-15\", \"Matinee\", 1);";
		String sql2 = "INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES (\"Circle\", 80, 5000, 3), (\"Stalls\", 120, 5000, 3);";
		dbConnector.executeQuery(sql1);
		dbConnector.executeQuery(sql2);
		
		LocalDate date = LocalDate.of(2022, 7, 15);
		ArrayList<Performance> performanceList = dbConnector.getPerformancesByDate(date);
		assertEquals(1, performanceList.size(), "Verify performance for date 2022/7/15");
		
		Performance performance1 = performanceList.get(0);
		assertNotNull(performance1.getShow(), "Verify getShow");
		
		assertEquals(date, performance1.getDate(), "Verify performace getDate");
		assertTrue(performance1.getMatinee(),"Verify getMatinee");
	    assertEquals(120,performance1.getAvailabilityStalls(),"Verify getAvailabilityStalls");
		assertEquals(80,performance1.getAvailabilityCircle(),"Verify getAvailabilityCircle");
		assertEquals(4000, performance1.getPriceStalls(), "Verify getPrice Stalls");
		assertEquals(4000, performance1.getPriceCircle(), "Verify getPrice Circle");
	}

	/**
	 * Test method for {@link util.DBConnector#makePurchase(java.util.ArrayList, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	void testMakePurchase() throws SQLException {
		//Creating new customer
		String sql = "INSERT INTO Customer (lname, email, address, username, password) "
				+ "VALUES (\"Thomas Neil\", \"TNeil@email.com\", \"108 Dunkirk Road\", \"TaNe2\", \"password\")";
		dbConnector.executeQuery(sql);
		
		Performance performance = dbConnector.getPerformancesByShowID(1).get(0);
		PerformanceBooking performanceBooking = new PerformanceBooking(performance,true,2,3);
		ArrayList<PerformanceBooking> basket = new ArrayList<PerformanceBooking>();
		basket.add(performanceBooking);
		
		dbConnector.makePurchase(basket,"TaNe2");
		
		String sqlForBooked = "SELECT NumberOfAdults, NumberOfChildren, SeatID "
				+ "from Booking where CustomerId=(select CustomerID from customer where username='TaNe2')";
		ResultSet resultSet = dbConnector.executeQuery(sqlForBooked);
		resultSet.next();
		assertEquals(2, resultSet.getInt("NumberOfAdults"), "Verify NumberOfAdults");
		assertEquals(3, resultSet.getInt("NumberOfChildren"), "Verify NumberOfChildren");
		assertEquals(2, resultSet.getInt("SeatID"), "Verify PaymentID");
	}

	/**
	 * Test method for {@link util.DBConnector#newCustomer(model.Customer)}.
	 * @throws SQLException 
	 */
	@Test
	void testNewCustomer(){
		
		Customer customer = new Customer("TaNe", "Thomas Neil", "108 Dunkirk Road");
		
		dbConnector.newCustomer(customer);
		
		String sql = "SELECT fname, lname, username, address from Customer where username='TaNe'";
		ResultSet resultSet = dbConnector.executeQuery(sql);
		try {
			resultSet.next();
			assertEquals("Thomas Neil", resultSet.getString("lname"), "Verify last name.");
			assertEquals("TaNe", resultSet.getString("username"),  "Verify username.");
			assertEquals("108 Dunkirk Road", resultSet.getString("address"), "Verify address.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link util.DBConnector#getCustomer(java.lang.String)}.
	 */
	@Test
	void testGetCustomer() {
		String sql = "INSERT INTO Customer (lname, email, address, username, password) "
				+ "VALUES (\"Thomas Neil\", \"TNeil@email.com\", \"108 Dunkirk Road\", \"TaNe\", \"password\")";
		dbConnector.executeQuery(sql);
		
		Customer customer = dbConnector.getCustomer("TaNe");
		
		assertEquals("Thomas Neil", customer.getName(), "Verify name.");
		assertEquals("TaNe", customer.getUsername(),  "Verify username.");
		assertEquals("108 Dunkirk Road", customer.getAddress(), "Verify address.");
	}

	/**
	 * Test method for {@link util.DBConnector#getBookings(java.lang.String)}.
	 */
	@Test
	void testGetBookings() {
		ArrayList<String> bookingList = dbConnector.getBookings("JaDo");
		assertEquals(1, bookingList.size(),"Verify booking list count");
		String booking = bookingList.get(0);
		assertTrue(booking.contains("Mamma Mia"), "Verify title in booking.");
		assertTrue(booking.contains("Matinee"), "Verify time of performance in booking.");
		assertTrue(booking.contains("2022-07-13"), "Verify bookng date in booking.");
		assertTrue(booking.contains("1 adults"), "Verify adult tickets in booking.");
		assertTrue(booking.contains("0 children"), "Verify children tickets in booking.");
		assertTrue(booking.contains("Circle"), "Verify seat zone in booking.");
	}

}
