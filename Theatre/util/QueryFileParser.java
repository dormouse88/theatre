package util;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QueryFileParser {
	private Scanner s;
	
	public QueryFileParser(String filename) {
		s = null;
		try {
			s = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getAllQueries() {
		ArrayList<String> queries = new ArrayList<String>(); 
		s.useDelimiter(";");
		while ( s.hasNext() ) {
			queries.add( s.next() );
		}
		queries.remove( queries.size()-1 );
		
		for (int i = 0; i< queries.size(); i++) {
			String q = queries.get(i);
//			if (q.endsWith(";")) {
//				q = q.substring(0, q.length() -1);
//			}
		}
		return queries;
	}
	
	//TODO: Possibly add support for getting single queries

};