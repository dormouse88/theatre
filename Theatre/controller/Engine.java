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
	
//TODO: Refactor Engine class (especially the switch)
	
	public void run() {
		
		//TODO: Automate initial database construction.
		// WILL NEED THIS:
//				QueryFileParser creation_sql = new QueryFileParser("DB creation script.sql");
//				ArrayList<String> cre = creation_sql.getAllQueries();
//				for (int i = 0; i < cre.size(); i++ )
//				{
//					db.executeQuery(cre.get(i) + ";");
//				}
				
		
		UserInputParser uip = new UserInputParser();
		Boolean finished = false;
		displayOptions(); //display options once when prog first starts
		while (!finished) {
			//displayOptions();
			int userInput = uip.getInt("Enter a number from the list of options or enter 0 to see the list: > ");
	        if (userInput != 100 && userInput > 9)
	        {
	        	userInput = 0;
	        }

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
				String searchDate = uip.getString( "Enter Date in dd-mm-yyyy format" ); //TODO: Decide on date search returns (show or performance)
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
				break;
			case 6:
				//Make Purchase
				if (! customer.isComplete()) {
					System.out.println("Please enter your details fully before completing the purchase.");
					break;
				}
				ArrayList<PerformanceBooking> bookings = basket.getBookings();
				if (bookings.size() > 0) {
					db.makePurchase( basket.getBookings() );
					//TODO: if successful, inform user and basket.clear();
				}
				else {
					System.out.println("Your basket has nothing in it.");
				}
				break;
			case 7:
				//Add a performance to basket
				int perfIndex = uip.getInt( "Enter Performance Number" );
				if (perfIndex < 1 || perfIndex > performanceList.size() + 1) {
					System.out.println("No such performance number in list of performances.");
				}
				else {
					Performance perf = performanceList.get(perfIndex-1);
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
					System.out.println("Your basket has been updated.");
				}
				break;
			case 8:
				basket.displayBasket();
				break;
			case 9:
				//enter customer details
				//TODO: Does the customer need to be able to check their entered details?
				String custName = uip.getString( "Enter your name" );
				String custAddress = uip.getString( "Enter your address" );
				int custCCN = uip.getInt( "Enter your credit card number" );
				customer.enterDetails(custName, custAddress, custCCN);
				break;
			case 100:
				finished = true;
				break;
			default:
				displayOptions();
				break;
			}
		}

		db.close();
	}

	
	public void displayShows() {
		for (int i = 0; i<showList.size(); i++) {
			System.out.println("Show "+ (i+1) +": " + showList.get(i).getTitle());
		}
	}
	
	public void displayPerformances() {
		for (int i = 0; i<performanceList.size(); i++) {
			System.out.print("Performance "+ (i+1) + ": ");
			performanceList.get(i).print();
		}
	}

	public void displayOptions() {
		System.out.println("0 - Display Available Options");
		System.out.println("1 - List Shows");
		System.out.println("2 - List Shows By Title"); //{title}
		System.out.println("3 - List Shows By Date"); //{date}
		System.out.println("4 - View Show Details"); //{show_index}
		System.out.println("5 - List Performances"); //{show_index}
		System.out.println("6 - Make Purchase");
		System.out.println("7 - Add A Performance To Basket"); // {perf_index} {stalls} {adults} {kids}
		System.out.println("8 - View Basket");
		System.out.println("9 - Enter Customer Details"); // {name} {address} {cc}
		System.out.println("100 - quit");
		System.out.println();
	}
	
	
}
