package model;

import controller.BookingController;

import java.util.ArrayList;

public class Booking {
	private int ID;
	private int CustomerID;
	private int DriverID;
	private int CarID;
	private String Description;
	private String BookingDate;
	private String Status;
	public static ArrayList<Booking> ListBooking = BookingController.getListBooking("BookingData.txt");

	public Booking(int ID, int customerID, int driverID, int carID, String description, String bookingDate, String status) {
		this.ID = ID;
		CustomerID = customerID;
		DriverID = driverID;
		CarID = carID;
		Description = description;
		BookingDate = bookingDate;
		Status = status;
	}

	public int getID() {
		return ID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public int getDriverID() {
		return DriverID;
	}

	public int getCarID() {
		return CarID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getBookingDate() {
		return BookingDate;
	}

	public void setBookingDate(String bookingDate) {
		BookingDate = bookingDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public ArrayList<Booking> getListBooking() {
		return ListBooking;
	}

	public void setListBooking(ArrayList<Booking> listBooking) {
		ListBooking = listBooking;
	}
}
