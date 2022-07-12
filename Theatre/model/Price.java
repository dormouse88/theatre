package model;

/**
 * class models the pricing of a ticket - a basePrice is retrieved from the db through a query that retrieves a specific Seat price.
 * Discounts are then applied to this 'basePrice' at different levels 1,2 or 3 (at 20% discounts per level). 
 * The basePrice should be subjected to the following in the following order:
 * priceObject.getHighAvailabilityDiscount(db.getTotalAvailableSeatsFromPerformance);
 * priceObject.getDateDiscount(db.getpdateFromPerformance);
 * priceObject.flashSalePricing(db.getTotalAvailableSeatsFromPerformance, db.getUpdateFromPerformance);
 * priceObject.applyDiscounts();
 * 
 * priceObject.getPaidPrice();
 * this will return the updated price and will be up to 60% off. If 0 discounts are available (if seat availability > 30% and there are 7 days before the show)
 * then the paidPrice will be the basePrice. 
 * 
 * 
 * Author: AN
 */


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Price {
	private double paidPrice;
	private int discountLevel;
	private double discount;
	private LocalDate currentDate;
	private int totalSeats;
	private int remainingSeats;
	
	public Price(int basePrice) {
		paidPrice = basePrice; //baseprice retrieved from db (seatZone)
		discountLevel = 0; //currently is set at 0, no discounts applied
		discount = 0.2;  //discount is currently set to 20% per level - MS analysis
		currentDate = LocalDate.now();
		totalSeats = 200; //currently hardcoded
		remainingSeats = 200;
		
	}

	public long getDateDiscount(LocalDate date) {
		//TODO check if datediscount can be applied
		//get date of performance using user input, get current date.
		long daysUntil = currentDate.until(date, ChronoUnit.DAYS);
		if (daysUntil > 7 ) { 
			discountLevel +=1;}
		return daysUntil;
	}
	
	
	
	
	/*
	 * gets the available number of seats and is called in the getHighAvailabilityDiscount() method;
	 * @param remainingSeats is called from the db via a query getting the total number of available seats from the Seat table
	 * @returns available seats as a %
	 * is returned in this manner as int/int = 0 if <0.5;
	 */
	public int getAvailability(int remainingSeats) {
		this.remainingSeats = remainingSeats;
		return (remainingSeats*100)/totalSeats;
		
		
	}
	/*
	 * if the remaining number of seats exceeds 30%, there is high availability and so the discountLevel is increased by 1. When called
	 * by engine this is checked before the dateDiscount is applied.
	 * @param remainingSeats is called from a query from the db and passed to the getAvailability call
	 * @returns a % of available seats as an int
	 */
	public boolean getHighAvailabilityDiscount(int remainingSeats) {
		//TODO check how many seats are remaining, decided 30% remaining though this could be changed.
		//create a method in dbdriver to get number of remaining seats and pass to this method
		double availableSeats = getAvailability(remainingSeats);
		//check if available seats is >30% 
		if (availableSeats > 30) {
			discountLevel += 1;
			return true;
		}
		return false;
		}

		
	/*
	 * flashSalePricing is checked last and has highest discountLevel applied. 
	 * @params, remainingSeats and date of the show - both values obtained from db queries.
	 * if both checks are true, there is high availability and the show will be on in less than a day so the price is drastically cut.
	 */
	
	public void flashSalePricing(int remainingSeats, LocalDate date) {
		//TODO if todays date is 1 day from performance, and totalSeats > 30%, give maximum discounts on tickets.
		//market research says this is 60% disc
		if (getDateDiscount(date) <= 1 && getHighAvailabilityDiscount(remainingSeats) == true); {
			discountLevel = 3;} 
		applyDiscounts();
	}
	
	/*
	 * applies the discount by multiplying the discountLevel by the discount by the current paidPrice. 
	 * if the discountLevel is 0, the paidPrice is unchanged.
	 */

	public void applyDiscounts() {
		// apply any discounts.
		if (discountLevel == 0) {
			paidPrice = paidPrice;
		} else {
			paidPrice = Math.round(paidPrice *  (1-(discountLevel * discount)));
		}
		
	}


	
	/**
	 * @return the paidPrice
	 */
	public double getPaidPrice() {
		return paidPrice;
	}

	/**
	 * @return the discountLevel
	 */
	public int getDiscountLevel() {
		return discountLevel;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @return the date
	 */
	public LocalDate getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param date the date to set
	 */
	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	/**
	 * @return the totalSeats
	 */
	public int getTotalSeats() {
		return totalSeats;
	}

	/**
	 * @param totalSeats the totalSeats to set
	 */
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	/**
	 * @return the remainingSeats
	 */
	public int getRemainingSeats() {
		return remainingSeats;
	}

	/**
	 * @param remainingSeats the remainingSeats to set
	 */
	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	/**
	 * @param paidPrice the paidPrice to set
	 */
	public void setPaidPrice(double paidPrice) {
		this.paidPrice = paidPrice;
	}

	/**
	 * @param discountLevel the discountLevel to set
	 */
	public void setDiscountLevel(int discountLevel) {
		this.discountLevel = discountLevel;
	}

	
	
}
	
