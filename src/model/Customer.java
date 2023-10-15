package model;

import java.util.ArrayList;
import controller.PersonController;
public class Customer extends Person{
	public static ArrayList<Person> ListCustomer = PersonController.getLisPerson("CustomerData.txt");

	public Customer(int iD, String name, String phoneNo, String email, String address ) {
		super(iD, name, phoneNo, email, address);
	}
}
