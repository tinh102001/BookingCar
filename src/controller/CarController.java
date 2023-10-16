package controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Car;
import utils.Table;

public class CarController {
    private static final String Database = "CarData.txt";
    private static int carID = getLastIDCar();

    public static void addNewCar(String Type, String LicensePlate, int NoOfSeats, String Description, String Status) {
        try {
            Car car = new Car(++carID, Type, LicensePlate, NoOfSeats, Description, Status);
            Car.ListCar.add(car);
            String data = carID + "\t" + Type + "\t" + LicensePlate + "\t" + NoOfSeats + "\t" + Description + "\t" + Status;
            File file = new File(Database);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(raf.length());

            raf.writeBytes("\n" + data);

            raf.close();
            System.out.println("Successfully added new car");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCarStatus(int carID, String Status) {
        try {
            File file = new File(Database);
            boolean found = false;

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while (raf.getFilePointer() < raf.length()) {
                String nameNumberString = raf.readLine();
                String[] parts = nameNumberString.split("\t", 6);
                if (parts[0].equals(Integer.toString(carID))) {
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
                    String dataCar = raf.readLine();
                    String[] parts = dataCar.split("\t", 6);

                    if (Integer.toString(carID).equals(parts[0])) {

                        Car.ListCar.get(id).setStatus(Status);
                        dataCar = Car.ListCar.get(id).getID() + "\t" + Car.ListCar.get(id).getType() + "\t" + Car.ListCar.get(id).getLicensePlate() + "\t" + Car.ListCar.get(id).getNoOfSeats() + "\t" + Car.ListCar.get(id).getDescription() + "\t" + Car.ListCar.get(id).getStatus();
                    }

                    tmpraf.writeBytes(dataCar);
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

    public static void deleteCar(int carID) {
        try {
            File file = new File(Database);
            boolean found = false;

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while (raf.getFilePointer() < raf.length()) {
                String nameNumberString = raf.readLine();
                String[] parts = nameNumberString.split("\t", 6);
                if (parts[0].equals(Integer.toString(carID))) {
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
                    String dataCar = raf.readLine();
                    String[] parts = dataCar.split("\t", 5);
                    if (Integer.toString(carID).equals(parts[0])) {
                        Car.ListCar.remove(id);
                        continue;
                    }
                    tmpraf.writeBytes(dataCar);
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
                System.out.println("Successfully delete car");
            } else {
                raf.close();
                System.out.println("Not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchCarByName(String carName) {
        ArrayList<Car> listCarName = new ArrayList<>();
        for (Car car : Car.ListCar) {
            if (car.getDescription().toLowerCase().contains(carName)) {
                listCarName.add(car);
            }
        }
        CarController.showListCar(listCarName);
    }

    public static ArrayList<Car> availableCars(ArrayList<Car> listCar) {
        ArrayList<Car> listAvailableCars = new ArrayList<>();
        for (Car car : listCar) {
            if (car.getStatus().equals("Available")) {
                listAvailableCars.add(car);
            }
        }
        return listAvailableCars;
    }

    public static ArrayList<Car> rentedCars(ArrayList<Car> listCar) {
        ArrayList<Car> listRentedCars = new ArrayList<>();
        for (Car car : listCar) {
            if (car.getStatus().equals("In Progress")) {
                listRentedCars.add(car);
            }
        }
        return listRentedCars;
    }

    public static ArrayList<Car> getListCars(String Database) {
        ArrayList<Car> ListCar = new ArrayList<>();
        try {
            Scanner x = new Scanner(new File(Database));
            while (x.hasNextLine()) {
                String line = x.nextLine();
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\t", 6);
                Car car = new Car(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5]);
                ListCar.add(car);
            }
        } catch (Exception e) {
            System.out.println("Couldn't find file!");
        }

        return ListCar;
    }

    public static void showCarStatus(ArrayList<Car> ListCar) {
        Table table = new Table();
        List<String> headersList = new ArrayList<>();
        headersList.add("Id");
        headersList.add("Type");
        headersList.add("License Plate");
        headersList.add("Number Of Seats");
        headersList.add("Description");

        List<List<String>> rowsList = new ArrayList<>();

        for (Car car : ListCar) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(car.getID()));
            row.add(car.getType());
            row.add(car.getLicensePlate());
            row.add(Integer.toString(car.getNoOfSeats()));
            row.add(car.getDescription());

            rowsList.add(row);
        }

        System.out.println(table.generateTable(headersList, rowsList));
    }

    public static void showCarByCategory(ArrayList<Car> ListCar, String type) {
        Table table = new Table();
        List<String> headersList = new ArrayList<>();
        headersList.add("Id");
        headersList.add("Type");
        headersList.add("License Plate");
        headersList.add("Number Of Seats");
        headersList.add("Description");
        headersList.add("Status");

        List<List<String>> rowsList = new ArrayList<>();

        for (Car car : ListCar) {
            if (car.getType().toLowerCase().equals(type)) {
                List<String> row = new ArrayList<>();
                row.add(Integer.toString(car.getID()));
                row.add(car.getType());
                row.add(car.getLicensePlate());
                row.add(Integer.toString(car.getNoOfSeats()));
                row.add(car.getDescription());
                row.add(car.getStatus());

                rowsList.add(row);
            }
        }

        System.out.println(table.generateTable(headersList, rowsList));
    }

    public static void showListCar(ArrayList<Car> ListCar) {
        Table table = new Table();
        List<String> headersList = new ArrayList<>();
        headersList.add("Id");
        headersList.add("Type");
        headersList.add("License Plate");
        headersList.add("Number Of Seats");
        headersList.add("Description");
        headersList.add("Status");

        List<List<String>> rowsList = new ArrayList<>();

        for (Car car : ListCar) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(car.getID()));
            row.add(car.getType());
            row.add(car.getLicensePlate());
            row.add(Integer.toString(car.getNoOfSeats()));
            row.add(car.getDescription());
            row.add(car.getStatus());

            rowsList.add(row);
        }

        System.out.println(table.generateTable(headersList, rowsList));
    }

    public static int getLastIDCar() {
        int ID = 0;
        try {
            Scanner x = new Scanner(new File(Database));
            while (x.hasNextLine()) {
                String line = x.nextLine();
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split("\t", 6);
                ID = Integer.parseInt(parts[0]);
            }
        } catch (Exception e) {
            System.out.println("Couldn't find file!");
            return 0;
        }
        return ID;
    }

    public static boolean isAvailable(int carID) {
        for (Car car : Car.ListCar) {
            if (car.getID() == carID) {
                if (car.getStatus().equals("In Progress")) {
                    return false;
                }
            }
        }
        return true;
    }


}
