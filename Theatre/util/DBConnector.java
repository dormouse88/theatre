package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.Show;
import model.Performance;
import model.PerformanceBooking;
import model.Customer;

/**
 * This class handles all database interactions for the Theatre application.
 * @author dor
 */
public class DBConnector {
	private Connection conn;
	private QueryFileParser qfp;

	public DBConnector() {
		conn = null;
		qfp = new QueryFileParser();
	}
	
	/**
	 * Attempts to connect to a database.
	 * Connection configuration can be set in connection.txt file.
	 * Connection credentials can be set in credentials.txt file.
	 */
	public void connect() {
		try {
			Scanner s = new Scanner(new File("credentials.txt"));
			String uname = s.nextLine().trim();
			String pwd = s.nextLine().trim();
			Scanner s2 = new Scanner(new File("connection.txt"));
			String url = s2.nextLine().trim();
			String dbName = s2.nextLine().trim();
			conn = DriverManager.getConnection(url + dbName, uname, pwd);
		} catch (IOException e) {
			System.out.println("File error.");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
			return;
		}
		if (conn == null) {
			System.out.println("Connection null still.");
		}
	}

	/**
	 * Runs an SQL script that creates the database and populates it with sample data.
	 */
	public void createDatabase() {
		ArrayList<String> cre = qfp.getCreationScript();
		for (int i = 0; i < cre.size(); i++ )
		{
			executeQuery(cre.get(i) + ";");
		}
	}
	
	/**
	 * Retrieves and returns all shows from the database.
	 */
	public ArrayList<Show> getAllShows() {
		String query = qfp.getShow();
		return getShows(query);
	}

	/**
	 * Retrieves and returns all shows from the database that contain the
	 * parameter string in their title.
	 * @param title A substring to search for
	 */
	public ArrayList<Show> getShowsByTitle(String title) {
		String query = qfp.getShow() + " WHERE title LIKE '%"+ title + "%'";
		return getShows(query);
	}

	/**
	 * Retrieves and returns all performances from the database which are performances 
	 * of the show whose ID was passed in.
	 * @param showID The primary key of the matching show
	 */
	public ArrayList<Performance> getPerformancesByShowID(int showID) {
		String perfQueryString = qfp.getPerformance() + " WHERE Showing.ShowID = " + showID;
		return getPerformances(perfQueryString);
	}

	/**
	 * Retrieves and returns all performances from the database on the given date.
	 */
	public ArrayList<Performance> getPerformancesByDate(LocalDate date) {
		String perfQueryString = qfp.getPerformance() + " WHERE pdate = '" + date.toString() + "'";
		return getPerformances(perfQueryString);
	}

	/**
	 * This method attempts to book seats on the database for multiple performances. 
	 * If any one performance's booking fails to be booked (perhaps because of a lack of seats), the
	 * whole transaction will abort and no database changes will be effected.
	 * @param bookings An ArrayList of Performance Bookings that specify the full set of performances
	 * requested and the details of number of tickets required, etc.
	 * @return true if purchase succeeded, otherwise false.
	 */
	public Boolean makePurchase(ArrayList<PerformanceBooking> bookings) {
		int failures = 0;
		executeQuery("START TRANSACTION;");
		for (int i = 0; i<bookings.size(); i++) {
			PerformanceBooking pb = bookings.get(i);
			
			int perfID = pb.getPerformance().getID();
			int kids = pb.getKids(); 
			int adults = pb.getAdults();
			int seats = kids+adults;
			Boolean stalls = pb.getStalls();
			
			//run an sql update query using these details
			String seatZone = "NumberOfSeatsCircle";
			if (stalls) {
				seatZone = "NumberOfSeatsStalls";
			}
			String update = "UPDATE Performance SET "+seatZone+" = "+seatZone+" - "+seats+" WHERE PerformanceID = " +perfID+ " AND "+seatZone+" >= "+seats+";";
			int matches = executeUpdate(update);
			if (matches == 0) {
				failures++;
			}
		}
		if (failures == 0) {
			executeQuery("COMMIT;");
			return true;
		}
		else {
			executeQuery("ROLLBACK;");
			return false;
		}
	}

	/**
	 * Attempts to create a new customer account.
	 * It fails if one already exists with that name.
	 */
	public Boolean newCustomer(Customer c) {
		String update = "INSERT INTO Customer (fname, lname, address) VALUES ('"+ c.getUsername() + "', '" + c.getName() + "', '" + c.getAddress() + "')";
		executeUpdate(update);
		return true; //TODO: should check if insert worked
	}
	
	/**
	 * Retrieves a customer's details from a username.
	 * @param username
	 */
	public Customer getCustomer(String username) {
		String query = qfp.getCustomer() + " WHERE fname = '" + username + "'";
		ResultSet results = executeQuery(query);
		Customer c = null;
		try {
			if (results.next()) {
				c = populateCustomer(results);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return c;
	}

	/**
	 * Closes the database connection.
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection not closed.");
			e.printStackTrace();
		}
	}

/////////////////////////////  PRIVATE METHODS BEGIN:  ///////////////////////////////////////////

	private ArrayList<Show> getShows(String query) {
		ResultSet results = executeQuery(query);
		ArrayList<Show> shows = new ArrayList<Show>();
		try {
			while (results.next()) {
				shows.add(populateShow(results) );
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return shows;
	}
	
	private ArrayList<Performance> getPerformances(String perfQueryString) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			ResultSet perfResults = executeQuery(perfQueryString);
			while (perfResults.next()) {
				int showID = perfResults.getInt("showID");
				String showQuery = qfp.getShow() + " WHERE Showing.ShowID = " + showID;
				ResultSet showResults = executeQuery(showQuery);
				Show s;
				try {
					showResults.next();
					s = populateShow(showResults);
				}
				catch (SQLException e) {
					e.printStackTrace();
					return performances;
				}
				performances.add(populatePerformance(perfResults, s) );
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return performances;
		}
		return performances;
	}
	
	private Performance populatePerformance(ResultSet rs, Show s) throws SQLException {
		Boolean mat = false;
		if (rs.getString("ptime").equals("Matinee")) {
			mat = true;
		}
		return new Performance(
				rs.getInt("PerformanceID"),
				s,
				rs.getDate("pdate").toLocalDate(),
				mat,
				rs.getInt("StallSeats"),
				rs.getInt("CircleSeats"),
				rs.getInt("Price")
				);
	}
	
	private Show populateShow(ResultSet rs) throws SQLException {
		return new Show(
				rs.getInt("showID"),
				rs.getString("Title"),
				rs.getString("Genre"),
				rs.getString("Description"),
				rs.getInt("RunTimeMinutes"),
				rs.getString("Language"),
				rs.getString("Performer")
				);
	}

	private Customer populateCustomer(ResultSet rs) throws SQLException {
		return new Customer(
//				rs.getInt("CustomerID"),
				rs.getString("username"),
				rs.getString("name"),
				rs.getString("Address")
				);
	}
	
	private int executeUpdate(String sql) {
		try {
			PreparedStatement pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(sql + "\n failed to run.");
			e.printStackTrace();
			return 0;
		}
	}
	
	private ResultSet executeQuery(String sql) {
		try {
			PreparedStatement pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pst.execute();
			ResultSet results = pst.getResultSet();
			return results;
		} catch (SQLException e) {
			System.out.println(sql + "\n failed to run.");
			e.printStackTrace();
			return null;
		}
	}
}
