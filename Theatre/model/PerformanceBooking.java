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
	
	public String print() {
		Performance perf = getPerformance();
		String seatZone = Performance.getSeatZoneString(stalls);
		String ret = "A performance of " + perf.getShow().getTitle() + " on " + perf.getDate() + " (" + perf.getMatString() + ").";
		ret += System.lineSeparator();
		ret += getAdults() + " adults and " + getKids() + " children seated in the "+ seatZone + ".";
		ret += System.lineSeparator();
		ret += "Total price for this performance: "+ Performance.getPriceAsString( calculatePrice() );
		ret += System.lineSeparator();
		return ret;
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
