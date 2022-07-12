package model;

import java.time.LocalDate;

public class Performance {
	static public final int SEATS_CIRCLE = 80;
	static public final int SEATS_STALLS = 120;

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
		String s = 
				"\n----------------------------------------------------------------------------------------------------\n" +
				"		  Performance date : " + date + "||" +
				"Performance time : " + getMatString() + "\n" +
				"Stalls seats available : " + availabilityStalls + " - prices from " + getPriceAsString(getPriceStalls()) + "!" +
				"|| Circle seats available : " + availabilityCircle + " - prices from " + getPriceAsString(getPriceCircle()) + "! \n" +
//				"					PerformanceID : " + result.getInt("Performance.PerformanceID") +
				"\n----------------------------------------------------------------------------------------------------" ;
		System.out.println(s);
//		System.out.println("A performance of " + show.getTitle());
//		System.out.println("Date: " + date + " (" + getMatString() + ")");
//		System.out.println(availabilityStalls + " Seats available in the stalls at " + getPriceAsString(priceStalls));
//		System.out.println(availabilityCircle + " Seats available in the circle at " + getPriceAsString(priceCircle));
//		System.out.println();
	}
	
	
	public String getMatString() {
		if (matinee) {
			return "matinee";
		}
		return "evening";
	}
	public static String getSeatZoneString(Boolean stalls) {
		if (stalls) {
			return "stalls";
		}
		return "circle";
	}
	public static String getPriceAsString(int price) {
		return "£"+ price/100 + "." + String.format("%02d", price%100);
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
		Price d = new Price(priceStalls, SEATS_STALLS);
		return d.DoAllDiscounts(availabilityStalls, date);
	}
	public int getPriceCircle() {
		Price d = new Price(priceCircle, SEATS_CIRCLE);
		return d.DoAllDiscounts(availabilityCircle, date);
	}	
}
