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

public class DBConnector {
	private Connection conn;
	private QueryFileParser qfp;

	public DBConnector() {
		conn = null;
		qfp = new QueryFileParser();
	}
	
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
		if (conn != null) {
			System.out.println("Connection established.");
		} else {
			System.out.println("Connection null still.");
		}
	}

	public void CreateDatabase() {
		ArrayList<String> cre = qfp.getCreationScript();
		for (int i = 0; i < cre.size(); i++ )
		{
			executeQuery(cre.get(i) + ";");
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

	public ArrayList<Show> getAllShows() {
		String query = qfp.getShow();
		return getShows(query);
	}

	public ArrayList<Show> getShowsByTitle(String title) {
		String query = qfp.getShow() + " WHERE title = \""+ title + "\"";
		return getShows(query);
	}

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
	
	public ArrayList<Performance> getPerformancesByShowID(int showID) {
		String perfQueryString = qfp.getPerformance() + " WHERE Showing.ShowID = " + showID;
		return getPerformances(perfQueryString);
	}

	public ArrayList<Performance> getPerformancesByDate(LocalDate date) {
		String perfQueryString = qfp.getPerformance() + " WHERE pdate = '" + date.toString() + "'";
		return getPerformances(perfQueryString);
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
	
	public Boolean makePurchase(ArrayList<PerformanceBooking> bookings) {
		//TODO: Making a purchase
		
		for (int i = 0; i<bookings.size(); i++) {
			PerformanceBooking pb = bookings.get(i);
			
			int perfID = pb.getPerformance().getID();
			int kids = pb.getKids(); 
			int adults = pb.getAdults();
			Boolean stalls = pb.getStalls();
			
			//also get other details from each booking
			
			//run an sql update query using these details
		}
		// sql commit
		
		//Boolean success = was it successful?
				
		return true; //(or false if transaction didn't succeed)
	}

	public void close() {
		try {
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println("Connection not closed.");
			e.printStackTrace();
		}
	}
}
