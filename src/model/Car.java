package model;

import controller.CarController;

import java.util.ArrayList;

public class Car {
	private int ID;
	private String Type;
	private String LicensePlate;
	private int NoOfSeats;
	private String Description;
	private String Status;
	public static ArrayList<Car> ListCar = CarController.getListCars("CarData.txt");
	
	public Car(int iD, String type, String licensePlate, int noOfSeats, String description, String status) {
		ID = iD;
		Type = type;
		LicensePlate = licensePlate;
		NoOfSeats = noOfSeats;
		Description = description;
		Status = status;
	}

	public int getID() {
		return ID;
	}


	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getLicensePlate() {
		return LicensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		LicensePlate = licensePlate;
	}

	public int getNoOfSeats() {
		return NoOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		NoOfSeats = noOfSeats;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
}
