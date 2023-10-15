package main;

import java.io.*;
import java.util.Scanner;

import controller.*;
import model.Car;
import model.Customer;
import model.Driver;

public class main {
//    public static void main(String[] args) throws FileNotFoundException {
//		CustomerController.showTableCustomer(CustomerController.ListCustomer);
//		System.out.println(CustomerController.getLastIDCustomer());
//		Scanner sc = new Scanner(System.in);
//		String name = sc.nextLine();
//		String phone = sc.nextLine();
//		String email = sc.nextLine();
//		String address = sc.nextLine();
//		CustomerController.addNewCustomer(name, phone, email, address);
//		CustomerController.updateCustomer(6,"Jennifer David" ,"(378) 123-8255" , "David@gmail.com", "1425 Shikarpur Manor");
//		CustomerController.deleteCustomer(1);

//		PersonController.showTablePerson(Customer.ListCustomer);
//		CustomerController.addNewCustomer("Jennifer A" ,"(378) 123-8255" , "David@gmail.com", "1425 Shikarpur Manor");


//		CustomerController.updateCustomer(3,"Jennifer B" ,"(378) 123-8255" , "David@gmail.com", "1425 Shikarpur Manor");
//		CustomerController.deleteCustomer(1);
//		PersonController.showTablePerson(Customer.ListCustomer);

//		PersonController.showTablePerson(Driver.ListDriver);
//		DriverController.addNewDriver("Jennifer David" ,"(378) 123-8255" , "David@gmail.com", "1425 Shikarpur Manor");
//		DriverController.updateDriver(3,"Jennifer David" ,"(378) 123-8255" , "David@gmail.com", "1425 Shikarpur Manor");
//		DriverController.deleteDriver(1);
//		PersonController.showTablePerson(Driver.ListDriver);
//    }

    public static void Menu() {
        System.out.println("    \nAvailable Options");
        System.out.println("    -----------------");

        System.out.println("    \nDOCUMENTATION\n");
        System.out.println("    1. View Menu");

        System.out.println("    \nMANAGE CARS\n");

        System.out.println("    2. Add a new car");
        System.out.println("    3. Edit a car's status");
        System.out.println("    4. Remove a car");
        System.out.println("    5. Show all cars");
        System.out.println("    6. Show available cars");
        System.out.println("    7. Show rent cars");
        System.out.println("    8. Show cars by category");
        System.out.println("    9. Search car");

        System.out.println("    \nMANAGE CUSTOMER\n");

        System.out.println("    10. Add a new customer");
        System.out.println("    11. Edit a customer");
        System.out.println("    12. Remove a customer");
        System.out.println("    13. View all customers");
        System.out.println("    14. Search customers by ID");
        System.out.println("    15. Search customers by name");

        System.out.println("    \nMANAGE DRIVER\n");

        System.out.println("    20. Add a new driver");
        System.out.println("    21. Edit a driver");
        System.out.println("    22. Remove a driver");
        System.out.println("    23. View all driver");
        System.out.println("    24. Search driver by ID");
        System.out.println("    25. Search driver by name");

        System.out.println("    \nMANAGE BOOKING\n");

        System.out.println("    30. Add new booking");
        System.out.println("    31. Edit booking");
        System.out.println("    32. Remove booking");
        System.out.println("    33. Update booking's status");
        System.out.println("    34. View all booking");


        System.out.println("    0. Terminate/Exit System");

        System.out.println();


    }

    public static void Back() {
        System.out.println("  0. Terminate/Exit System");
        System.out.println("  1. Back to Menu");
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**                                              **");
        System.out.println("**   WELCOME TO CAR BOOKING MANAGEMENT SYSTEM   **");
        System.out.println("**                                              **");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println();
        Menu();
        int option;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Choose a command option: ");
            option = in.nextInt();
            in.nextLine();
            switch (option) {
                case 1:
                    Menu();
                    break;
                case 2:
                    System.out.print("Car's type: ");
                    String Type = in.nextLine();
                    System.out.print("License plate: ");
                    String LicensePlate = in.nextLine();
                    System.out.print("Number of seats: ");
                    int NoOfSeats = in.nextInt();
                    System.out.print("Description: ");
                    String Description = in.nextLine();
                    System.out.print("Status: ");
                    String Status = in.nextLine();
                    CarController.addNewCar(Type, LicensePlate, NoOfSeats, Description, Status);

                    System.out.println("\nSuccessfully added new car");

                    Back();
                    break;
                case 3:
                    System.out.print("Car's ID: ");
                    int carID = in.nextInt();
                    System.out.print("New car's status: ");
                    String NewStatus = in.nextLine();

                    CarController.updateCarStatus(carID, NewStatus);

                    System.out.println("\nSuccessfully update car");

                    Back();

                    break;
                case 4:
                    System.out.print("Car's ID: ");
                    int removeCarID = in.nextInt();

                    CarController.deleteCar(removeCarID);

                    Back();
                    break;
                case 5:
                    CarController.showListCar(Car.ListCar);
                    Back();
                    break;
                case 6:
                    CarController.showCarStatus(CarController.availableCars(Car.ListCar));
                    Back();
                    break;
                case 7:
                    CarController.showCarStatus(CarController.rentedCars(Car.ListCar));
                    Back();
                    break;
                case 8:
                    System.out.print("Car's type: ");
                    String typeCar = in.nextLine();
                    CarController.showCarByCategory(Car.ListCar, typeCar);
                    Back();
                    break;
                case 9:
                    System.out.println();
                    break;

                case 10:
                    System.out.print("Customer's name: ");
                    String NameCustomer = in.nextLine();
                    System.out.print("Customer's phone number: ");
                    String PhoneNoCustomer = in.nextLine();
                    System.out.print("Customer's email: ");
                    String EmailCustomer = in.nextLine();
                    System.out.print("Customer's address: ");
                    String AddressCustomer = in.nextLine();
                    CustomerController.addNewCustomer(NameCustomer, PhoneNoCustomer, EmailCustomer, AddressCustomer);

                    System.out.println("\nSuccessfully added new customer");

                    Back();
                    break;
                case 11:
                    System.out.print("Customer's ID: ");
                    int customerID = in.nextInt();
                    System.out.print("New customer's name: ");
                    String NewNameCustomer = in.nextLine();
                    System.out.print("New customer's phone number: ");
                    String NewPhoneNoCustomer = in.nextLine();
                    System.out.print("New customer's email: ");
                    String NewEmailCustomer = in.nextLine();
                    System.out.print("New customer's address: ");
                    String NewAddressCustomer = in.nextLine();
                    CustomerController.updateCustomer(customerID, NewNameCustomer, NewPhoneNoCustomer, NewEmailCustomer, NewAddressCustomer);

                    System.out.println("\nSuccessfully update customer");

                    Back();
                    break;
                case 12:
                    System.out.print("Customer's ID: ");
                    int RemoveCustomerID = in.nextInt();
                    CustomerController.deleteCustomer(RemoveCustomerID);

                    System.out.println("\nSuccessfully delete customer");

                    Back();
                    break;
                case 13:
                    PersonController.showTablePerson(Customer.ListCustomer);
                    Back();
                    break;
                case 14:
                    System.out.print("Search customer's by ID: ");
                    int searchCustomerID = in.nextInt();
                    CustomerController.searchCustomerByID(searchCustomerID);
                    break;
                case 15:
                    System.out.print("Search customer's by name: ");
                    String searchCustomerName = in.nextLine();
                    CustomerController.searchCustomerByName(searchCustomerName);
                    break;
                case 20:
                    System.out.print("Driver's name: ");
                    String NameDriver = in.nextLine();
                    System.out.print("Driver's phone number: ");
                    String PhoneNoDriver = in.nextLine();
                    System.out.print("Driver's Email: ");
                    String EmailDriver = in.nextLine();
                    System.out.print("Driver's address: ");
                    String AddressDriver = in.nextLine();
                    DriverController.addNewDriver(NameDriver, PhoneNoDriver, EmailDriver, AddressDriver);

                    System.out.println("\nSuccessfully added new driver");

                    Back();
                case 21:
                    System.out.print("Driver's ID: ");
                    int driverID = in.nextInt();
                    System.out.print("New driver's name: ");
                    String NewNameDriver = in.nextLine();
                    System.out.print("New driver's phone number: ");
                    String NewPhoneNoDriver = in.nextLine();
                    System.out.print("New driver's Email: ");
                    String NewEmailDriver = in.nextLine();
                    System.out.print("New driver's address: ");
                    String NewAddressDriver = in.nextLine();
                    DriverController.updateDriver(driverID, NewNameDriver, NewPhoneNoDriver, NewEmailDriver, NewAddressDriver);

                    System.out.println("\nSuccessfully update driver");

                    Back();
                    break;
                case 22:
                    System.out.print("Driver's ID: ");
                    int RemoveDriverID = in.nextInt();
                    DriverController.deleteDriver(RemoveDriverID);

                    System.out.println("\nSuccessfully delete driver");

                    Back();
                    break;
                case 23:
                    PersonController.showTablePerson(Driver.ListDriver);
                    Back();
                    break;
                case 24:
                    System.out.print("Search driver's by ID: ");
                    int searchDriverID = in.nextInt();
                    CustomerController.searchCustomerByID(searchDriverID);
                    break;
                case 25:
                    System.out.print("Search driver's by name: ");
                    String searchDriverName = in.nextLine();
                    CustomerController.searchCustomerByName(searchDriverName);
                    break;


                case 0:
                    System.out.println();
                    System.out.println("Shut down Car Booking Management System!");
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid Option... Please select a valid operation from the list provided!");
                    Back();
            }
        } while (option != 0);
    }
}
