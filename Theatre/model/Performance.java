package model;

import java.time.LocalDate;

public class Performance {
	private int id;
	private Show show;
	private LocalDate date;
	private Boolean matinee; //(mat or eve)
	private int availabilityStalls;
	private int availabilityCircle;
	private int priceStalls;
	private int priceCircle;
	
	public Performance(int id, Show show, LocalDate date, Boolean matinee, int availabilityStalls, int availabilityCircle, int priceStalls, int priceCircle) {
		this.id = id;
		this.show = show;
		this.date = date;
		this.matinee = matinee;
		this.availabilityStalls = availabilityStalls;
		this.availabilityCircle = availabilityCircle;
		this.priceCircle = priceCircle;
		this.priceStalls = priceStalls;
	}
	
	public void print() {
		System.out.println("A performance of " + show.getTitle());
		System.out.println("Date: " + date + " (" + getMatString() + ")");
		System.out.println(availabilityStalls + " Seats available in the stalls at " + getPriceAsString(priceStalls));
		System.out.println(availabilityCircle + " Seats available in the circle at " + getPriceAsString(priceCircle));
//		System.out.println("Price: " + getPriceAsString(price) );
		System.out.println();
	}
	public String getMatString() {
		if (matinee) {
			return "matinee";
		}
		return "evening";
	}
	public static String getPriceAsString(int price) {
		return "�"+ price/100 + "." + String.format("%02d", price%100);
	}

	public int getID() {
		return id;
	}
	public Show getShow() {
		return show;
	}
	public LocalDate getDate() {
		return date;
	}
	public Boolean getMatinee() {
		return matinee;
	}
	public int getAvailabilityStalls() {
		return availabilityStalls;
	}
	public int getAvailabilityCircle() {
		return availabilityCircle;
	}
	public int getPriceStalls() {
		return priceStalls;
	}	
	public int getPriceCircle() {
		return priceCircle;
	}	
}
