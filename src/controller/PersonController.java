package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Customer;
import model.Person;
import utils.Table;

public class PersonController {

	public static ArrayList<Person> getLisPerson(String Database) {
		ArrayList<Person> ListPersonData = new ArrayList<>();
		try {
			Scanner x = new Scanner(new File(Database));
			while (x.hasNextLine()) {
				String line = x.nextLine();
				if (line.trim().isEmpty())
					continue;

				String[] parts = line.split("\t", 5);
				Customer customer = new Customer(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
				ListPersonData.add(customer);
			}
		} catch (Exception e) {
			System.out.println("Couldn't find file!");
		}

		return ListPersonData;
	}

	public static void showTablePerson(ArrayList<Person> ListPerson) {
		Table table = new Table();
		List<String> headersList = new ArrayList<>();
		headersList.add("Id");
		headersList.add("Name");
		headersList.add("Phone Number");
		headersList.add("Email");
		headersList.add("Address");

		List<List<String>> rowsList = new ArrayList<>();

        for (Person listPerson : ListPerson) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(listPerson.getID()));
            row.add(listPerson.getName());
            row.add(listPerson.getPhoneNo());
            row.add(listPerson.getEmail());
            row.add(listPerson.getAddress());

            rowsList.add(row);
        }

		System.out.println(table.generateTable(headersList, rowsList));
	}

}
