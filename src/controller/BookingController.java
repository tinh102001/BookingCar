package controller;

import model.Booking;
import model.Car;
import model.Customer;
import model.Driver;
import utils.Table;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingController {
    private static final String Database = "BookingData.txt";
    private static int bookingID = getLastIDBooking();

    public static void addNewBooking(int customerID, int driverID, int carID, String description, String date, String status) {
        if (!CarController.isAvailable(carID)) {
            System.out.println("This car has been rented");
        } else if (!DriverController.isAvailable(driverID)) {
            System.out.println("This driver are busy");
        } else {
            try {
                Booking booking = new Booking(++bookingID, customerID, driverID, carID, description, date, status);
                Booking.ListBooking.add(booking);
                String data = bookingID + "\t" + customerID + "\t" + driverID + "\t" + carID + "\t" + description + "\t" + date + "\t" + status;
                File file = new File(Database);
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                raf.seek(raf.length());

                raf.writeBytes("\n" + data);
                CarController.updateCarStatus(carID, "In Progress");
                DriverController.updateStatusDriver(driverID, "Busy");
                System.out.println("Successfully add new booking");
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void checkoutBooking(int bookingID) {
        for (Booking booking : Booking.ListBooking) {
            if (booking.getID() == bookingID) {
                if (booking.getStatus().equals("Done")) {
                    System.out.println("Booking already done");
                } else {
                    try {
                        File file = new File(Database);
                        boolean found = false;

                        RandomAccessFile raf = new RandomAccessFile(file, "rw");
                        while (raf.getFilePointer() < raf.length()) {
                            String nameNumberString = raf.readLine();
                            String[] parts = nameNumberString.split("\t", 7);
                            if (parts[0].equals(Integer.toString(bookingID))) {
                                found = true;
                                break;
                            }
                        }

                        if (found) {
                            File tmpFile = new File("temp1.txt");
                            RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                            raf.seek(0);
                            int id = 0;
                            while (raf.getFilePointer() < raf.length()) {
                                String dataBooking = raf.readLine();
                                String[] parts = dataBooking.split("\t", 7);

                                if (Integer.toString(bookingID).equals(parts[0])) {
                                    Booking.ListBooking.get(id).setStatus("Done");
                                    dataBooking = Booking.ListBooking.get(id).getID() + "\t" + Booking.ListBooking.get(id).getCustomerID() + "\t" + Booking.ListBooking.get(id).getDriverID() + "\t" + Booking.ListBooking.get(id).getCarID() + "\t" + Booking.ListBooking.get(id).getDescription() + "\t" + Booking.ListBooking.get(id).getBookingDate() + "\t" + Booking.ListBooking.get(id).getStatus();
                                    CarController.updateCarStatus(Booking.ListBooking.get(id).getCarID(), "Available");
                                    DriverController.updateStatusDriver(Booking.ListBooking.get(id).getDriverID(), "Ready");
                                }

                                tmpraf.writeBytes(dataBooking);
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
                        } else {
                            raf.close();
                            System.out.println("Not found ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static ArrayList<Booking> getListBooking(String Database) {
        ArrayList<Booking> ListBooking = new ArrayList<>();
        try {
            Scanner x = new Scanner(new File(Database));
            while (x.hasNextLine()) {
                String line = x.nextLine();
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\t", 7);
                Booking booking = new Booking(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4], parts[5], parts[6]);
                ListBooking.add(booking);
            }
        } catch (Exception e) {
            System.out.println("Couldn't find file!");
        }

        return ListBooking;
    }

    public static int getLastIDBooking() {
        int ID = 0;
        try {
            Scanner x = new Scanner(new File(Database));
            while (x.hasNextLine()) {
                String line = x.nextLine();
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\t", 7);
                ID = Integer.parseInt(parts[0]);
            }
        } catch (Exception e) {
            System.out.println("Couldn't find file!");
            return 0;
        }
        return ID;
    }

    public static void showListBooking(ArrayList<Booking> ListBooking) {
        Table table = new Table();
        List<String> headersList = new ArrayList<>();
        headersList.add("Id");
        headersList.add("Customer's name");
        headersList.add("Driver's name");
        headersList.add("Car");
        headersList.add("Description");
        headersList.add("Date");
        headersList.add("Status");

        List<List<String>> rowsList = new ArrayList<>();

        for (Booking booking : ListBooking) {

            List<String> row = new ArrayList<>();
            row.add(Integer.toString(booking.getID()));
            for (Customer customer : Customer.ListCustomer) {
                if (customer.getID() == booking.getCustomerID()) {
                    row.add(customer.getName());
                }
            }
            for (Driver driver : Driver.ListDriver) {
                if (driver.getID() == booking.getDriverID()) {
                    row.add(driver.getName());
                }
            }
            for (Car car : Car.ListCar) {
                if (car.getID() == booking.getCarID()) {
                    row.add(car.getDescription());
                }
            }
            row.add(booking.getDescription());
            row.add(booking.getBookingDate());
            row.add(booking.getStatus());

            rowsList.add(row);
        }

        System.out.println(table.generateTable(headersList, rowsList));
    }
}
