package controller;

public class CustomerDetails {
	private String name;
	private String address;
	private int creditCardNo;

	public CustomerDetails(){
		name = "";
		address = "";
		creditCardNo = 0;
	}

  //enter details asks for all details in one field, if time this will be separated
	public void enterDetails(String name,  String address, int creditCardNo){
		this.name = name;
		this.address = address;
		this.creditCardNo = creditCardNo;
	}
	public Boolean isComplete(){
		Boolean checked = true;
		if (name == null || name.isBlank()) {
			checked = false;
		}
		if(address == null || address.isBlank()) {
			checked = false;
		}
		if (creditCardNo == 0){
			checked = false;
		}
		return checked;
	}

	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public int getCreditCardNo(){
		return creditCardNo;
	}
}

