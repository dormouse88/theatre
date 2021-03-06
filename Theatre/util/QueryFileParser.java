package util;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class parses SQL files to extract queries and returns them as Strings
 * or ArrayLists of Strings.
 * @author dor
 */
public class QueryFileParser {
	//basically could be a static class as it is now
	public QueryFileParser() {
	}

	public ArrayList<String> getCreationScript() {
		return getAllQueries("DB creation script.sql");
	}
	public ArrayList<String> getSampleDataScript() {
		return getAllQueries("sample data script.sql");
	}
	public String getShowsStub() {
		return getAllQueries("getShowsStub.sql").get(0);
	}
	public String getShowsByTitle() {
		return getAllQueries("getShowsByTitle.sql").get(0);
	}
//	public String getPerformance() {
//		return getAllQueries("getPerformances.sql").get(0);
//	}
	public String getPerformancesByShowID() {
		return getAllQueries("getPerformancesByShowID.sql").get(0);
	}
	public String getPerformancesByDate() {
		return getAllQueries("getPerformancesdate.sql").get(0);
	}
	public String getCustomer() {
		return getAllQueries("getCustomer.sql").get(0);
	}
	public String getPerformers() {
		return getAllQueries("getPerformers.sql").get(0);
	}
	public String updateSeats() {
		return getAllQueries("updateSeats.sql").get(0);
	}
	public String insertBooking() {
		return getAllQueries("insertBooking.sql").get(0);
	}
	public String getBookings() {
		return getAllQueries("getBookings.sql").get(0);
	}
	

	/**
	 * Parses an SQL file and splits it into a separate string for each query.
	 * The trailing ; is removed from each String (each query) so as to allow
	 * additional clauses to be appended (eg. WHERE ...).  
	 * @param filename a string that specifies the name of the file to parse
	 * @return An ArrayList of Strings where each String is a single query
	 */
	private ArrayList<String> getAllQueries(String filename) {
		ArrayList<String> queries = new ArrayList<String>(); 
		Scanner s = null;
		try {
			s = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println("A critical SQL Query file was not found.");
			return queries;
		}
		
		s.useDelimiter(";");
		while ( s.hasNext() ) {
			queries.add( s.next() );
		}
		if (queries.get(queries.size()-1).isBlank()) {
			queries.remove( queries.size()-1 );
		}
		return queries;
	}

};