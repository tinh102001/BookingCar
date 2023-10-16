package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Person{
	public static ArrayList<Customer> ListCustomer = getListCustomer("CustomerData.txt");

	public Customer(int iD, String name, String phoneNo, String email, String address ) {
		super(iD, name, phoneNo, email, address);
	}

	public static ArrayList<Customer> getListCustomer(String Database) {
		ArrayList<Customer> ListCustomerData = new ArrayList<>();
		try {
			Scanner x = new Scanner(new File(Database));
			while (x.hasNextLine()) {
				String line = x.nextLine();
				if (line.trim().isEmpty())
					continue;

				String[] parts = line.split("\t", 5);
				Customer customer = new Customer(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
				ListCustomerData.add(customer);
			}
		} catch (Exception e) {
			System.out.println("Couldn't find file!");
		}

		return ListCustomerData;
	}
}
