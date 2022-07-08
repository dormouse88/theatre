package controller;

import java.util.ArrayList;
import java.time.LocalDate;
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
	
	public boolean isTicketPostagePossible() {
		boolean ret = true;
		LocalDate latestDate = LocalDate.now().plusDays(-7);
		for (PerformanceBooking pb: bookings) {
			LocalDate d = pb.getPerformance().getDate();
			if (d.isAfter(latestDate)) {
				ret = false;
			}
		}
		return ret;
	}
	public int getPostageCharge() {
		int ret;
		int totalAdults = 0;
		int totalKids = 0;
		for (PerformanceBooking pb: bookings) {
			totalAdults += pb.getAdults();
			totalKids += pb.getKids();
		}
		if (totalAdults == 0) {
			ret = 0;
		}
		else if (totalKids > 0) {
			ret = 100;
		}
		else {
			ret = totalAdults * 100;
		}
		return ret;
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
