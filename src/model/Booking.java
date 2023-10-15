package model;

import java.util.ArrayList;
import java.util.Date;

public class Booking {
	private int ID;
	private int CustomerID;
	private int DriverID;
	private int CarID;
	private String Description;
	private Date BookingDate;
	private String Status;
	private ArrayList<Booking> ListBooking;
}
