package model;

import java.time.LocalDate;

public class Performance {
	private int id;
	private Show show;
	private LocalDate date;
	private Boolean matinee; //(mat or eve)
	private int availabilityStalls;
	private int availabilityCircle;
	private int price;
	
	public Performance(int id, Show show, LocalDate date, Boolean matinee, int availabilityStalls, int availabilityCircle, int price) {
		this.id = id;
		this.show = show;
		this.date = date;
		this.matinee = matinee;
		this.availabilityStalls = availabilityStalls;
		this.availabilityCircle = availabilityCircle;
		this.price = price;
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
	public int getPrice() {
		return price;
	}	
}
