package model;

/**
 * This class models customers and corresponds to customer data in the database.
 * @author dor
 */
public class Customer {
//	private int id;
	private String username;
	private String name;
	private String address;

	public Customer(String username, String name, String address) {
//		this.id = id;
		this.username = username;
		this.name = name;
		this.address = address;
	}

	/**
	 * Checks if all necessary customer details are entered for a payment
	 * @return true If details are complete
	 */
	public Boolean isComplete(){
		Boolean checked = true;
		if (name == null || name.isBlank()) {
			checked = false;
		}
		if(address == null || address.isBlank()) {
			checked = false;
		}
		return checked;
	}

//	public int getID() {
//		return id;
//	}
	public String getUsername() {
		return username;
	}
	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
}

