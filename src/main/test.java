package main;

import controller.CarController;

import controller.CustomerController;
import controller.PersonController;
import model.Car;
import model.Customer;
import model.Person;

import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		List<Person> per = PersonController.getLisPerson("CustomerData.txt");
		List<Car> test = CarController.getListCars("CarData.txt");
		for (Car car : test) {
			System.out.println(car.getID());
		}
	}
}
