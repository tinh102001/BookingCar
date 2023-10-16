package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver extends Person{
	private String status;
	private int point;
	public static ArrayList<Driver> ListDriver = getLisDriver("DriverData.txt");
	public Driver(int iD, String name, String phoneNo, String email, String address) {
		super(iD, name, phoneNo, email, address);
		this.status = "Ready";
		this.point = 0;
	}

	public Driver(int iD, String name, String phoneNo, String email, String address, String status, int point) {
		super(iD, name, phoneNo, email, address);
		this.status = status;
		this.point = point;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public static ArrayList<Driver> getLisDriver(String Database) {
		ArrayList<Driver> ListDriverData = new ArrayList<>();
		try {
			Scanner x = new Scanner(new File(Database));
			while (x.hasNextLine()) {
				String line = x.nextLine();
				if (line.trim().isEmpty())
					continue;

				String[] parts = line.split("\t", 7);
				Driver driver = new Driver(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6]));
				ListDriverData.add(driver);
			}
		} catch (Exception e) {
			System.out.println("Couldn't find file!");
		}

		return ListDriverData;
	}
}
