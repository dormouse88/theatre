package model;

public class PerformanceBooking {
	private Performance perf;
	private Boolean stalls;
	private int adults;
	private int kids;
	
	public PerformanceBooking(Performance perf, Boolean stalls, int adults, int kids) {
		this.perf = perf;
		this.stalls = stalls;
		this.adults = adults;
		this.kids = kids;
	}
	
	public int calculatePrice() {
		int price = 0;
		if (stalls) {
			price = getPerformance().getPriceStalls();
		}
		else {
			price = getPerformance().getPriceCircle();
		}
		int total = 0;
		total += getAdults() * price;
		total += (int)(getKids() * price * 0.75);
		return total;
	}
	
	public Performance getPerformance() {
		return perf;
	}
	public Boolean getStalls() {
		return stalls;
	}
	public int getAdults() {
		return adults;
	}
	public int getKids() {
		return kids;
	}
}
