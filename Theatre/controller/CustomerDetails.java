package controller;

public class CustomerDetails {
  public CustomerDetails(){
    name = "";
    address = "";
    creditCardNo = 0;
  }
  private String name;
  private String address;
  private int creditCardNo;

  //enter details asks for all details in one field, if time this will be separated
  public void enterDetails(String name,  String address, int creditCardNo){
    this.name = name;
    this.address = address;
    this.creditCardNo = creditCardNo;
  }
  public Boolean isComplete(){
    Boolean checked = true;
    if (name == ""){
      checked = false;
    }
    if(address == ""){
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

