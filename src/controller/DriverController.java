package controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;
import model.Driver;
import model.Person;

public class DriverController {
	private final static String Database = "DriverData.txt";
	private static int driverID = getLastIDDriver();

	public static void addNewDriver(String Name, String PhoneNo, String Email, String Address) {
		try {
			File file = new File(Database);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");

			Driver driver = new Driver(++driverID, Name, PhoneNo, Email, Address);
			Driver.ListDriver.add(driver);
			String data = driverID + "\t" + Name + "\t" + PhoneNo + "\t" + Email + "\t" + Address;

			raf.seek(raf.length());

			raf.writeBytes("\n" + data);

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateDriver(int driverID, String Name, String PhoneNo, String Email, String Address) {
		try {
			File file = new File(Database);
			boolean found = false;

			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			while (raf.getFilePointer() < raf.length()) {
				String nameNumberString = raf.readLine();
				String[] parts = nameNumberString.split("\t", 5);
				if (parts[0].equals(Integer.toString(driverID))) {
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
					String dataDriver = raf.readLine();
					String[] parts = dataDriver.split("\t", 5);

					if (Integer.toString(driverID).equals(parts[0])) {
						Driver.ListDriver.get(id).setName(Name);
						Driver.ListDriver.get(id).setEmail(Email);
						Driver.ListDriver.get(id).setAddress(Address);
						Driver.ListDriver.get(id).setPhoneNo(PhoneNo);
						dataDriver = driverID + "\t" + Name + "\t" + PhoneNo + "\t" + Email + "\t" + Address;
					}

					tmpraf.writeBytes(dataDriver);
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

	public static void deleteDriver(int driverID) {
		try {
			File file = new File(Database);
			boolean found = false;

			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			while (raf.getFilePointer() < raf.length()) {
				String nameNumberString = raf.readLine();
				String[] parts = nameNumberString.split("\t", 5);
				if (parts[0].equals(Integer.toString(driverID))) {
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
					String dataDriver = raf.readLine();
					String[] parts = dataDriver.split("\t", 5);
					if (Integer.toString(driverID).equals(parts[0])) {
						Driver.ListDriver.remove(id);
						continue;
					}
					tmpraf.writeBytes(dataDriver);
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
	public static void searchDriverByID(int driverID) {
		ArrayList<Person> listDriverID = new ArrayList<>();
		for (Person driver : Driver.ListDriver) {
			if (driver.getID() == driverID) {
				listDriverID.add(driver);
			}
		}
		PersonController.showTablePerson(listDriverID);
	}

	public static void searchDriverByName(String driverName) {
		ArrayList<Person> listDriverName = new ArrayList<>();
		for (Person driver : Driver.ListDriver) {
			if (driver.getName().toLowerCase().contains(driverName)) {
				listDriverName.add(driver);
			}
		}
		PersonController.showTablePerson(listDriverName);
	}
	public static int getLastIDDriver() {
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
