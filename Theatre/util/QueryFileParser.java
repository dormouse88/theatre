package util;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QueryFileParser {
	//basically could be a static class as it is now
	public QueryFileParser() {
	}

	public ArrayList<String> getCreationScript() {
		return getAllQueries("DB creation script.sql");
	}
	public String getShow() {
		return getAllQueries("getShows.sql").get(0);
	}
	public String getPerformance() {
		return getAllQueries("getPerformances.sql").get(0);
	}
	public String getCustomer() {
		return getAllQueries("getCustomer.sql").get(0);
	}

	private ArrayList<String> getAllQueries(String filename) {
		Scanner s = null;
		try {
			s = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> queries = new ArrayList<String>(); 
		s.useDelimiter(";");
		while ( s.hasNext() ) {
			queries.add( s.next() );
		}
		queries.remove( queries.size()-1 );
		
		for (int i = 0; i< queries.size(); i++) {
			String q = queries.get(i);
		}
		return queries;
	}

};