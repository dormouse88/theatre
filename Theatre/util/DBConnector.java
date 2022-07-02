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
		//Create SQL Query String here...
		String queryString = "" + showID + "";
		
		ResultSet results = executeQuery(queryString);
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			while (results.next()) {
				//run nested query to get performance's show details...
				String showQueryString = "" + results.getInt(1) + ""; //get a single show from perf id //is it "1"?
				ResultSet showResults = executeQuery(showQueryString);
				Show s = populateShow(showResults);
				performances.add(populatePerformance(results, s) );
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return performances;
	}
	
	public ArrayList<Show> getAllShows() {
		//Get correct Query String
		String queryString = ""; //HERE!
		
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
		return new Performance(
				rs.getInt(0),
				s,
				rs.getDate(1).toLocalDate(),
				rs.getBoolean(2),
				rs.getInt(3),
				rs.getInt(4),
				rs.getInt(5)
				);
	}
	
	private Show populateShow(ResultSet rs) throws SQLException {
		return new Show(
				rs.getInt(0),
				rs.getString(1),
				rs.getString(2),
				rs.getString(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getString(6)			
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
