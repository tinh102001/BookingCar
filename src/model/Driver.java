package model;

import java.util.ArrayList;

import controller.PersonController;

public class Driver extends Person{
	public static ArrayList<Person> ListDriver = PersonController.getLisPerson("DriverData.txt");
	public Driver(int iD, String name, String phoneNo, String email, String address) {
		super(iD, name, phoneNo, email, address);
	}
	
}
