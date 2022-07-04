package controller;

import java.util.ArrayList;
import model.PerformanceBooking;
import model.Performance;

public class Basket {
	ArrayList<PerformanceBooking> bookings;
	
	public Basket()  {
		bookings = new ArrayList<PerformanceBooking>();
	}
	
	public void addPerformance(Performance perf, Boolean stalls, int adults, int kids) {
		bookings.add(new PerformanceBooking(perf, stalls, adults, kids));
	}
	public void displayBasket() {
		if (bookings.isEmpty()) {
			System.out.println("Your basket is empty.");
			return;
		}
		for (PerformanceBooking pb: bookings) {
			String seatZone = "";
			if (pb.getStalls()) {seatZone = "stalls";}
			else {seatZone = "circle";}
			Performance perf = pb.getPerformance();
			System.out.println("A performance of " + perf.getShow().getTitle() + " on " + perf.getDate() + " (" + perf.getMatString() + ").");
			System.out.println(pb.getAdults() + " adults and " + pb.getKids() + " children seated in the "+ seatZone + ".");
			System.out.println("Total price for this performance: "+ Performance.getPriceAsString( pb.calculatePrice() ) );
			System.out.println();
		}
		System.out.println("Total price for your basket: "+ Performance.getPriceAsString( calculatePrice() ) );
	}
	public void clearBasket() {
		bookings.clear();
	}
	public int calculatePrice() {
		int total = 0;
		for (PerformanceBooking pb : bookings) {
			total += pb.calculatePrice();
		}
		return total;
	}
	
	//This is a pretty scummy method but...
	public ArrayList<PerformanceBooking> getBookings() {
		return bookings;
	}
	

}
