package controller;
import util.QueryFileParser;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.Show;
import util.DBConnector;

public class Engine {

	public static void main(String[] args) {
		DBConnector db = new DBConnector();
		db.connect();		

		QueryFileParser creation_sql = new QueryFileParser("creation.sql");
		ArrayList<String> cre = creation_sql.getAllQueries();
		for (int i = 0; i < cre.size(); i++ )
		{
			db.executeQuery(cre.get(i));
		}
		
		ArrayList<Show> showList = db.getAllShows();

		db.close();
	}

}
