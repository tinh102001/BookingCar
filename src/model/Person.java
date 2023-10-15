package model;

import java.util.ArrayList;

public class Person {
	private int ID;
	private String Name;
	private String PhoneNo;
	private String Email;
	private String Address;
	
	public Person(int iD, String name, String phoneNo, String email, String address) {
		super();
		ID = iD;
		Name = name;
		PhoneNo = phoneNo;
		Email = email;
		Address = address;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	
}
