package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Show;
import model.Performance;
import model.PerformanceBooking;

public class DBConnector {
	private Connection conn;

	public DBConnector() {
		conn = null;
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

	public ResultSet executeQuery(String sql) {
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
	
	public ArrayList<Performance> getPerformances(int showID) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		
		QueryFileParser qfp = new QueryFileParser("getShows.sql");
		String showQueryString = qfp.getAllQueries().get(0);
		showQueryString += " WHERE Showing.ShowID = " + showID;

		ResultSet showResults = executeQuery(showQueryString);
		Show s;
		try {
			showResults.next();
			s = populateShow(showResults);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return performances;
		}
		
		QueryFileParser qfp2 = new QueryFileParser("getPerformances.sql");
		String perfQueryString = qfp2.getAllQueries().get(0);
		perfQueryString += " WHERE Showing.ShowID = " + showID;

		ResultSet results = executeQuery(perfQueryString);
		try {
			while (results.next()) {
				performances.add(populatePerformance(results, s) );
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return performances;
		}
		return performances;
	}
	
	//0 = everything
	//1 = search by keyword
	//2 = search by date
	public ArrayList<Show> getShows(int filterType, String filter) {
		QueryFileParser qfp = new QueryFileParser("getShows.sql");
		String queryString = qfp.getAllQueries().get(0);
		if (filterType == 1) {
			queryString += " WHERE title = \""+ filter+ "\"";
		}
		else if (filterType == 2) {
			queryString += " WHERE DATE = "+ filter; //no. Will return performances or shows?
		}
		else {
		}
		
		ResultSet results = executeQuery(queryString);
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
		
		for (int i = 0; i<bookings.size(); i++) {
			int perfID = bookings.get(i).getPerformance().getID();
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
