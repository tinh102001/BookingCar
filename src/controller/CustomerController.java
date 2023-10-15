package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;
import model.Person;

public class CustomerController {
	private final static String Database = "CustomerData.txt";
	private static int customerID = getLastIDCustomer();

	public static void addNewCustomer(String Name, String PhoneNo, String Email, String Address)  {
		try {
			Customer customer = new Customer(++customerID, Name, PhoneNo, Email, Address);
			Customer.ListCustomer.add(customer);
			String data = customerID + "\t" + Name + "\t" + PhoneNo + "\t" + Email + "\t" + Address;
			File file = new File(Database);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(raf.length());

			raf.writeBytes("\n" + data);

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void updateCustomer(int customerID, String Name, String PhoneNo, String Email, String Address) {
		try {
			File file = new File(Database);
			boolean found = false;

			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			while (raf.getFilePointer() < raf.length()) {
				String nameNumberString = raf.readLine();
				String[] parts = nameNumberString.split("\t", 5);
				if (parts[0].equals(Integer.toString(customerID))) {
					found = true;
					break;
				}
			}

			if (found == true) {
				File tmpFile = new File("temp.txt");
				RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
				raf.seek(0);
				int id = 0;
				while (raf.getFilePointer() < raf.length()) {
					String dataCustomer = raf.readLine();
					String[] parts = dataCustomer.split("\t", 5);

					if (Integer.toString(customerID).equals(parts[0])) {
						Customer.ListCustomer.get(id).setName(Name);
						Customer.ListCustomer.get(id).setEmail(Email);
						Customer.ListCustomer.get(id).setAddress(Address);
						Customer.ListCustomer.get(id).setPhoneNo(PhoneNo);
						dataCustomer = customerID + "\t" + Name + "\t" + PhoneNo + "\t" + Email + "\t" + Address;
					}

					tmpraf.writeBytes(dataCustomer);
					tmpraf.writeBytes(System.lineSeparator());
					id++;
				}

				raf.seek(0);
				tmpraf.seek(0);

				while (tmpraf.getFilePointer() < tmpraf.length()) {
					raf.writeBytes(tmpraf.readLine());
					raf.writeBytes(System.lineSeparator());
				}

				raf.setLength(tmpraf.length());

				tmpraf.close();
				raf.close();
				tmpFile.delete();
			}

			else {
				raf.close();
				System.out.println("Not found ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteCustomer(int customerID) {
		try {
			File file = new File(Database);
			boolean found = false;

			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			while (raf.getFilePointer() < raf.length()) {
				String nameNumberString = raf.readLine();
				String[] parts = nameNumberString.split("\t", 5);
				if (parts[0].equals(Integer.toString(customerID))) {
					found = true;
					break;
				}
			}
			if (found) {
				File tmpFile = new File("temp.txt");
				RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
				raf.seek(0);
				int id = 0;
				while (raf.getFilePointer() < raf.length()) {
					String dataCustomer = raf.readLine();
					String[] parts = dataCustomer.split("\t", 5);
					if (Integer.toString(customerID).equals(parts[0])) {
						Customer.ListCustomer.remove(id);
                        continue;
                    }
					tmpraf.writeBytes(dataCustomer);
					tmpraf.writeBytes(System.lineSeparator());
					id++;
				}

				raf.seek(0);
				tmpraf.seek(0);

				while (tmpraf.getFilePointer() < tmpraf.length()) {
					raf.writeBytes(tmpraf.readLine());
					raf.writeBytes(System.lineSeparator());
				}

				raf.setLength(tmpraf.length());

				tmpraf.close();
				raf.close();
				tmpFile.delete();
			}

			else {
				raf.close();
				System.out.println("Not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void searchCustomerByID(int customerID) {
		ArrayList<Person> listCustomerID = new ArrayList<>();
		for (Person customer : Customer.ListCustomer) {
			if (customer.getID() == customerID) {
				listCustomerID.add(customer);
			}
		}
		PersonController.showTablePerson(listCustomerID);
 	}

	public static void searchCustomerByName(String customerName) {
		ArrayList<Person> listCustomerName = new ArrayList<>();
		for (Person customer : Customer.ListCustomer) {
			if (customer.getName().toLowerCase().contains(customerName)) {
				listCustomerName.add(customer);
			}
		}
		PersonController.showTablePerson(listCustomerName);
	}

	public static int getLastIDCustomer() {
		int ID = 0;
		try {
			Scanner x = new Scanner(new File(Database));
			while (x.hasNextLine()) {
				String line = x.nextLine();
				if (line.trim().isEmpty())
					continue;

				String[] parts = line.split("\t", 5);
				ID = Integer.parseInt(parts[0]);
			}
		} catch (Exception e) {
			System.out.println("Couldn't find file!");
			return 0;
		}
		return ID;
	}


}
