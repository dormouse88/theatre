package controller;
import util.QueryFileParser;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import model.Show;
import model.Customer;
import model.Performance;
import model.PerformanceBooking;

import util.DBConnector;
import util.UserInputParser;

public class Engine {
	private Basket basket;
	private Customer customer;
	private ArrayList<Show> showList;
	private ArrayList<Performance> performanceList;
	private UserInputParser uip;
	private DBConnector db;
	
	public Engine() {
		basket = new Basket();
		customer = null;
		showList = new ArrayList<Show>();
		performanceList = new ArrayList<Performance>();
		uip = new UserInputParser();
		db = new DBConnector();
		db.connect();
	}
	
//TODO: Refactor Engine class (especially the switch)
	
	public void run() {
		db.createDatabase();
//		UserInputParser uip = new UserInputParser();
		Boolean finished = false;
		displayOptions(); //display options once when prog first starts
		System.out.println("The use of a large console window is recommended");
		while (!finished) {
			int userInput = uip.getInt("Enter a number from the list of options or enter 0 to see the list: > ");
	        if (userInput != 100 && userInput > 9)
	        {
	        	userInput = 0;
	        }

			switch(userInput) {
			case 1: //all shows
				showList = db.getAllShows();
				displayShows();
				break;
			case 2: //shows by title
				String searchTerm = uip.getString( "Enter Search Term" );
				showList = db.getShowsByTitle(searchTerm ); 
				displayShows();
				break;
			case 3: //performances by date
				try {
					LocalDate searchDate = uip.getDate();
					performanceList = db.getPerformancesByDate(searchDate );
					displayPerformances();
				}
				catch (DateTimeParseException e) {
					System.out.println("Your input could not be recognised as a date.");
				}
				break;
			case 4: //View Show Details
				int showIndex = uip.getInt( "Enter Show Number") - 1;
				if (showIndex < 0 || showIndex >= showList.size()) {
					System.out.println("No such show number in list of shows.");
				}
				else {
					showList.get(showIndex).print();
				}
				break;
			case 5: //List Performances
				int showIndex2 = uip.getInt( "Enter Show Number" ) - 1;
				if (showIndex2 < 0 || showIndex2 >= showList.size() ) {
					System.out.println("No such show number in list of shows.");
				}
				else {
					performanceList = db.getPerformancesByShowID( showList.get(showIndex2).getID() );
					displayPerformances();
				}
				break;
			case 6: //Make Purchase
				if (! customer.isComplete()) {
					System.out.println("Please enter your customer details fully before making a purchase.");
					break;
				}
				ArrayList<PerformanceBooking> bookings = basket.getBookings();
				if (bookings.size() > 0) {
					int custCCN = uip.getInt( "Enter your credit card number" );
					if ( custCCN == 0 ) {
						System.out.println("Credit Card Declined!");
					}
					else {
						boolean success = db.makePurchase( basket.getBookings() );
						if (success) {
							System.out.println("Payment taken. Your tickets have been successfully ordered.");
							basket.clearBasket();
						}
						else {
							System.out.println("Your purchase failed. You have not been charged.");
						}
					}
				}
				else {
					System.out.println("Your basket has nothing in it.");
				}
				break;
			case 7: //Add a performance to basket
				int perfIndex = uip.getInt( "Enter Performance Number" ) - 1;
				if (perfIndex < 0 || perfIndex >= performanceList.size()) {
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
					System.out.println("Your basket has been updated.");
				}
				break;
			case 8:
				basket.displayBasket();
				break;
			case 9: //Create or login to customer account
				customerLogin();
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

	public void customerLogin() {
		String username = uip.getString("Enter a username");
		//query database for such a user account
		Customer c = db.getCustomer(username);
		if (c != null) {
			//if exists, login to it
			customer = c;
		}
		else {
			//else create it (+be logged in)
			String name = uip.getString("Enter your name");
			String address = uip.getString("Enter your address");
			customer = new Customer(username, name, address);
			db.newCustomer(customer);
		}
	}
	
	public void displayShows() {
		if ( showList.isEmpty()) {
			System.out.println("No hits.");
		}
		for (int i = 0; i<showList.size(); i++) {
			System.out.println("Show "+ (i+1) +": " + showList.get(i).getTitle());
		}
	}
	
	public void displayPerformances() {
		if ( performanceList.isEmpty()) {
			System.out.println("No hits.");
		}
		for (int i = 0; i<performanceList.size(); i++) {
			System.out.print("Performance "+ (i+1) + ": ");
			performanceList.get(i).print();
		}
	}

	public void displayOptions() {
		if (customer == null) {
			System.out.println("Not Logged in.");
		}
		else {
			System.out.println("Logged in as " + customer.getUsername() + ", name: " + customer.getName());
		}
		System.out.println("0 - Display Available Options");
		System.out.println("1 - List Shows");
		System.out.println("2 - List Shows By Title"); //{title}
		System.out.println("3 - List Shows By Date"); //{date}
		System.out.println("4 - View Show Details"); //{show_index}
		System.out.println("5 - List Performances"); //{show_index}
		System.out.println("6 - Make Purchase");
		System.out.println("7 - Add A Performance To Basket"); // {perf_index} {stalls} {adults} {kids}
		System.out.println("8 - View Basket");
		System.out.println("9 - Create or Login to Customer Account"); // {name} {address} {cc}
		System.out.println("100 - quit");
		System.out.println();
	}
}
