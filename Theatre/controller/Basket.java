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
		//print out some shit
	}
	public void clearBasket() {
		bookings.clear();
	}
	public int calculatePrice() {
		int total = 0;
		for (PerformanceBooking pb : bookings) {
			int price = pb.getPerformance().getPrice(); 
			total += pb.getAdults() * price;
			total += (int)(pb.getKids() * price * 0.75);
		}
		return total;
	}
	
	//This is a pretty scummy method but...
	public ArrayList<PerformanceBooking> getBookings() {
		return bookings;
	}
	

}
