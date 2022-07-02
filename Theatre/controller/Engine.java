package controller;
import util.QueryFileParser;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.Show;
import model.Performance;
import model.PerformanceBooking;

import util.DBConnector;
import util.UserInputParser;

public class Engine {
	private Basket basket;
	private CustomerDetails customer;
	private ArrayList<Show> showList;
	private ArrayList<Performance> performanceList;
	private DBConnector db;
	
	public Engine() {
		basket = new Basket();
		customer = new CustomerDetails();
		showList = new ArrayList<Show>();
		performanceList = new ArrayList<Performance>();
		db = new DBConnector();
		db.connect();
	}
	
	public void run() {
		UserInputParser uip = new UserInputParser();
		Boolean finished = false;
		while (!finished) {
			int userInput = uip.getCommand();

			switch(userInput) {
			case 1:
				showList = db.getShows(0, ""); //all shows
				displayShows();
				break;
			case 2:
				String searchTerm = uip.getString( "Enter Search Term" );
				showList = db.getShows(1, searchTerm ); //by title
				displayShows();
				break;
			case 3:
				String searchDate = uip.getString( "Enter Date in dd-mm-yyyy format" );
				showList = db.getShows(2, searchDate ); //by date
				displayShows();
				break;
			case 4:
				//View Show Details
				int showIndex = uip.getInt( "Enter Show Number" );
				if (showIndex < 1 || showIndex > showList.size() + 1) {
					System.out.println("No such show number in list of shows.");
				}
				else {
					showList.get(showIndex).print();
				}
				break;
			case 5:
				//List Performances
				int showIndex2 = uip.getInt( "Enter Show Number" );
				if (showIndex2 < 1 || showIndex2 > showList.size() ) {
					System.out.println("No such show number in list of shows.");
				}
				else {
					performanceList = db.getPerformances( showList.get(showIndex2-1).getID() );
					displayPerformances();
				}
			case 6:
				//Make Purchase
				if (! customer.isComplete()) {
					System.out.println("Please enter your details fully before completing the purchase.");
					break;
				}
				ArrayList<PerformanceBooking> bookings = basket.getBookings();
				if (bookings.size() > 0) {
					db.makePurchase( basket.getBookings() );
					//if successful, basket.clear();
				}
				break;
			case 7:
				//Add a performance to basket
				int perfIndex = uip.getInt( "Enter Performance Number" );
				if (perfIndex < 1 || perfIndex > performanceList.size() + 1) {
					System.out.println("No such performance number in list of performances.");
				}
				else {
					Performance perf = performanceList.get(perfIndex);
					String seatZone = uip.getString( "Enter S for stalls seats or C for circle seats" );
					Boolean stalls;
					if (seatZone.equals("S")) {
						stalls = true;
					}
					else if (seatZone.equals("C")) {
						stalls = false;
					}
					else {
						System.out.println("That's not a recognised seating area.");
						break;
					}
					int kids = uip.getInt("Enter Number of Child Tickets Required");
					int adults = uip.getInt("Enter Number of Adult Tickets Required");
					int seatsAvailable = 0;
					if (stalls == true) {
						seatsAvailable = perf.getAvailabilityStalls();
					}
					else {
						seatsAvailable = perf.getAvailabilityCircle();
					}
					if (kids + adults > seatsAvailable) {
						System.out.println("There aren't enough seats available in your requested zone for your group.");
						break;
					}
					if (kids + adults == 0) {
						System.out.println("No seats requested. Nothing added to basket.");
						break;
					}
					basket.addPerformance(perf, stalls, adults, kids);
				}
				break;
			case 8:
				basket.displayBasket();
				break;
			case 9:
				//enter customer details
				String custName = uip.getString( "Enter your name" );
				String custAddress = uip.getString( "Enter your address" );
				int custCCN = uip.getInt( "Enter your credit card number" );
				customer.enterDetails(custName, custAddress, custCCN);
				break;
			case 100:
				finished = true;
				break;
			}
		}

// WILL NEED THIS:
//		QueryFileParser creation_sql = new QueryFileParser("DB creation script.sql");
//		ArrayList<String> cre = creation_sql.getAllQueries();
//		for (int i = 0; i < cre.size(); i++ )
//		{
//			db.executeQuery(cre.get(i));
//		}
		

		db.close();
	}

	
	public void displayShows() {
		for (int i = 0; i<showList.size(); i++) {
			System.out.println("Show "+ (i+1) +": " + showList.get(i).getTitle());
		}
	}
	
	public void displayPerformances() {
		System.out.println("PERFORMANCE LIST SIZE: " + performanceList.size());
		for (int i = 0; i<performanceList.size(); i++) {
			performanceList.get(i).print();
		}
	}

	
}
