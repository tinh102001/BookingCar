package controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Driver;
import utils.Table;

public class DriverController {
    private final static String Database = "DriverData.txt";
    private static int driverID = getLastIDDriver();

    public static void addNewDriver(String Name, String PhoneNo, String Email, String Address) {
        try {
            File file = new File(Database);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            Driver driver = new Driver(++driverID, Name, PhoneNo, Email, Address);
            Driver.ListDriver.add(driver);
            String data = driverID + "\t" + Name + "\t" + PhoneNo + "\t" + Email + "\t" + Address + "\t" + driver.getStatus() + "\t" + driver.getPoint();

            raf.seek(raf.length());

            raf.writeBytes("\n" + data);

            raf.close();
            System.out.println("Successfully added new driver");
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
                String[] parts = nameNumberString.split("\t", 7);
                if (parts[0].equals(Integer.toString(driverID))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                File tmpFile = new File("temp2.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);
                int id = 0;
                while (raf.getFilePointer() < raf.length()) {
                    String dataDriver = raf.readLine();
                    String[] parts = dataDriver.split("\t", 7);

                    if (Integer.toString(driverID).equals(parts[0])) {
                        Driver.ListDriver.get(id).setName(Name);
                        Driver.ListDriver.get(id).setEmail(Email);
                        Driver.ListDriver.get(id).setAddress(Address);
                        Driver.ListDriver.get(id).setPhoneNo(PhoneNo);
                        dataDriver = driverID + "\t" + Name + "\t" + PhoneNo + "\t" + Email + "\t" + Address + "\t" + Driver.ListDriver.get(id).getStatus() + "\t" + Driver.ListDriver.get(id).getPoint();
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
                System.out.println("Successfully update driver");
            } else {
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
                String[] parts = nameNumberString.split("\t", 7);
                if (parts[0].equals(Integer.toString(driverID))) {
                    found = true;
                    break;
                }
            }
            if (found) {
                File tmpFile = new File("temp2.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);
                int id = 0;
                while (raf.getFilePointer() < raf.length()) {
                    String dataDriver = raf.readLine();
                    String[] parts = dataDriver.split("\t", 7);
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
                System.out.println("Successfully delete driver");
            } else {
                raf.close();
                System.out.println("Not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStatusDriver(int driverID, String Status) {
        try {
            File file = new File(Database);
            boolean found = false;

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while (raf.getFilePointer() < raf.length()) {
                String nameNumberString = raf.readLine();
                String[] parts = nameNumberString.split("\t", 7);
                if (parts[0].equals(Integer.toString(driverID))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                File tmpFile = new File("temp2.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);
                int id = 0;
                while (raf.getFilePointer() < raf.length()) {
                    String dataDriver = raf.readLine();
                    String[] parts = dataDriver.split("\t", 7);

                    if (Integer.toString(driverID).equals(parts[0])) {

                        Driver.ListDriver.get(id).setStatus(Status);
                        dataDriver = Driver.ListDriver.get(id).getID() + "\t" + Driver.ListDriver.get(id).getName() + "\t" + Driver.ListDriver.get(id).getPhoneNo() + "\t" + Driver.ListDriver.get(id).getEmail() + "\t" + Driver.ListDriver.get(id).getAddress() + "\t" + Driver.ListDriver.get(id).getStatus() + "\t" + Driver.ListDriver.get(id).getPoint();
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
            } else {
                raf.close();
                System.out.println("Not found ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchDriverByID(int driverID) {
        ArrayList<Driver> listDriverID = new ArrayList<>();
        for (Driver driver : Driver.ListDriver) {
            if (driver.getID() == driverID) {
                listDriverID.add(driver);
            }
        }
        DriverController.showTableDriver(listDriverID);
    }

    public static void searchDriverByName(String driverName) {
        ArrayList<Driver> listDriverName = new ArrayList<>();
        for (Driver driver : Driver.ListDriver) {
            if (driver.getName().toLowerCase().contains(driverName)) {
                listDriverName.add(driver);
            }
        }
        DriverController.showTableDriver(listDriverName);
    }

    public static int getLastIDDriver() {
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

    public static boolean isAvailable(int driverID) {
        for (Driver driver : Driver.ListDriver) {
            if (driver.getID() == driverID) {
                if (driver.getStatus().equals("Busy")) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void showTableDriver(ArrayList<Driver> ListDriver) {
        Table table = new Table();
        List<String> headersList = new ArrayList<>();
        headersList.add("Id");
        headersList.add("Name");
        headersList.add("Phone Number");
        headersList.add("Email");
        headersList.add("Address");
        headersList.add("Status");
        headersList.add("Points");

        List<List<String>> rowsList = new ArrayList<>();

        for (Driver listDriver : ListDriver) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(listDriver.getID()));
            row.add(listDriver.getName());
            row.add(listDriver.getPhoneNo());
            row.add(listDriver.getEmail());
            row.add(listDriver.getAddress());
            row.add(listDriver.getStatus());
            row.add(Integer.toString(listDriver.getPoint()));

            rowsList.add(row);
        }

        System.out.println(table.generateTable(headersList, rowsList));
    }

}
