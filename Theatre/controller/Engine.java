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

/**
 * The main class of the Theatre application containing the execution loop.
 * 
 * The most recent search for shows or performances is cached in the ArrayList fields.
 * These cached searches are used to allow the user to refer to a particular show
 * or performance for other actions (adding to basket, etc) by means of the index
 * position of each show/performance in the container. 
 * 
 * The customer field represents a customer account and is null if not logged in.
 * 
 * The basket holds all the items the user has selected for purchase and is cleared
 * when they are successfully purchased.
 * @author dor
 */
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
	
//TODO: Should: Customer history -TOM
//TODO: Should: Dynamic pricing -ANDY
//TODO: SQL Injection protection -ANDY
//TODO: Should: Postage of tickets
//TODO: Printing tickets to file for the user
//TODO: Substitute a file for user input for running automated tests
//TODO: Confirm behaviour of a failed order (clear basket?)

	/**
	 * The main execution loop.
	 */
	public void run() {
		db.createDatabase();
		Boolean finished = false;
		displayOptions(); //display options once when prog first starts
		System.out.println("The use of a large console window is recommended");
		while (!finished) {
			int userInput = uip.getInt("Enter a number from the list of options or enter 0 to see the list:");

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
			case 6: //Add a performance to basket
				addToBasket();
				break;
			case 7:
				basket.displayBasket();
				break;
			case 8: //Order Your Basket
				makePurchase();
				break;
			case 10: //Create or login to customer account
				customerLogin();
				break;
			case 11: //Logout
				customer = null;
				System.out.println("You are now logged out.");
				break;
			case 12: //See history
				viewCustomerHistory();
				break;
			case 100: //quit
				finished = true;
				break;
			default: //any other user input will display the list of options
				displayOptions();
				break;
			}
		}
		db.close();
	}

	/**
	 * Prints to the screen the list of primary options available to the user.
	 * Additionally shows the login status of the customer account.
	 */
	public void displayOptions() {
		if (customer == null) {
			System.out.println("Not Logged in.");
		}
		else {
			System.out.println("Logged in as " + customer.getUsername() + ", name: " + customer.getName() + ", address: "+ customer.getAddress());
		}
		System.out.println("0  : Display Available Options");
		System.out.print  ("1  : List Shows                        |  ");
		System.out.print  ("2  : List Shows By Title               |  "); //{title}
		System.out.println("3  : List Shows By Date                |  "); //{date}
		System.out.print  ("4  : View Show Details                 |  "); //{show_index}
		System.out.print  ("5  : List Performances                 |  "); //{show_index}
		System.out.println("6  : Add a Performance to Basket       |  "); // {perf_index} {stalls} {adults} {kids}
		System.out.print  ("7  : View Basket                       |  ");
		System.out.println("8  : Order Your Basket                 |  ");
		System.out.print  ("10 : Create/Login to Customer Account  |  "); // {name} {address} {cc}
		System.out.print  ("11 : Logout of Customer Account        |  "); // {name} {address} {cc}
		System.out.println("12 : View Your Order History           |  "); // {name} {address} {cc}
		System.out.println("100 : quit");
		System.out.println();
	}

	/**
	 * Displays information from the database on the previous orders made by the
	 * currently logged in customer.
	 */
	public void viewCustomerHistory() {
		if (customer == null) {
			System.out.println("You need to be logged in to see your order history.");
		}
		else {
			//get performance bookings
			ArrayList<String> bookings = db.getBookings(customer.getUsername());
			for (String b: bookings) {
				System.out.println(b);
			}
			//print them
		}
	}	
	
	/**
	 * Attempts to add a performance to the basket (along with information on seats required).
	 * @return whether anything was actually added to the basket
	 */
	public Boolean addToBasket() {
		Boolean success = false;
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
				return false;
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
				return false;
			}
			if (kids + adults == 0) {
				System.out.println("No seats requested. Nothing added to basket.");
				return false;
			}
			basket.addPerformance(perf, stalls, adults, kids);
			System.out.println("Your basket has been updated.");
			success = true;
		}
		return success;
	}
	
	
	/**
	 * Attempts to purchase the contents of the basket.
	 * Either the entire purchase will succeed: all tickets will be booked and payment taken.
	 * Or the entire purchase will fail: no tickets will be booked and no payment taken.
	 * @return whether the purchase succeeded or not
	 */
	public Boolean makePurchase() {
		Boolean success = false;
		if (customer == null) {
			System.out.println("Please login or create an account before making a purchase.");
			return success;
		}
		if (! customer.isComplete()) {
			System.out.println("Please enter your customer details fully before making a purchase.");
			return success;
		}
		ArrayList<PerformanceBooking> bookings = basket.getBookings();
		if (bookings.size() > 0) {
			basket.displayBasket();
			String custCCN = uip.getString( "Enter your credit card number" );
			long numeric = 0;
			try {
				numeric = Long.parseLong( custCCN );
			}
			catch (NumberFormatException e) {} // numeric stays as 0
			if ( custCCN.length() != 16  || numeric == 0) {
				System.out.println("Credit Card Declined!");
				System.out.println("Credit card numbers must be 16 digits and numerals only.");
			}
			else {
				success = db.makePurchase( basket.getBookings(), customer.getUsername() );
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
		return success;
	}
	
	/**
	 * Does nothing if customer is already logged in.
	 * Otherwise, attempts to login to a customer account using the provided username.
	 * If no matching account is found, a new one is created on the database.
	 */
	public void customerLogin() {
		if (customer != null) {
			System.out.println("You are already logged in.");
		}
		else {
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
	}

	/**
	 * Prints to the screen the cached list of shows.
	 * Only the name of the show is displayed.
	 * Each show is numbered with an index that the user can use
	 * to select the show (eg. to browse the performances of that show)
	 */
	public void displayShows() {
		if ( showList.isEmpty()) {
			System.out.println("No hits.");
		}
		for (int i = 0; i<showList.size(); i++) {
			System.out.println("Show "+ (i+1) +": " + showList.get(i).getTitle());
		}
	}
	
	/**
	 * Prints to the screen the cached list of performances with all their details.
	 * The cache is populated from the most recent search for performances.
	 * Each performance is numbered with an index that the user can use
	 * to select the performance (eg. to book tickets for it) 
	 */
	public void displayPerformances() {
		if ( performanceList.isEmpty()) {
			System.out.println("No hits.");
		}
		for (int i = 0; i<performanceList.size(); i++) {
			System.out.print("Performance "+ (i+1) + ": ");
			performanceList.get(i).print();
		}
	}
}
