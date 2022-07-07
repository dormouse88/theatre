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
			System.out.print(pb.print());
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
