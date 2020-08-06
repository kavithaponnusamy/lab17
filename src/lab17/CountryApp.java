package lab17;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CountryApp {
	private static Path filePath = Paths.get("C:\\Users\\Kavitha\\eclipse-workspace\\lab17\\.settings\\country.txt");
	private static List<Country> lists = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Welcome to the Countries Maintenance Application!");
		System.out.println();
		Scanner scnr = new Scanner(System.in);

		while (true) {
			System.out.println("1-See the list of countries");
			System.out.println("2-Add a country");
			System.out.println("3-Delete a country");
			System.out.println("4-Modify the Population");
			System.out.println("5-Exit");
			System.out.println();
			System.out.println("Enter menu number: ");
			int userInput = scnr.nextInt();
			// scnr.nextLine();
			if (userInput == 1) {
				// List<Country>
				listCountries();
			} else if (userInput == 2) {
				Country country = getCountryFromUser(scnr);
				System.out.println("Adding " + country + "\n");
				appendLineToFile(country);

			} else if (userInput == 3) {
				listCountries();
				scnr.nextLine();
				System.out.println("Which country would you like to remove?");
				String choice = scnr.nextLine();
				boolean got = false;
				for (int i = 0; i < lists.size(); i++) {
					if (lists.get(i).getCountryName().equalsIgnoreCase(choice)) {
						System.out.println("Country " + lists.get(i).getCountryName() + " deleted.\n");
						lists.remove(i);
						writeToFile(lists);
						got = true;
					}
				}
				if (!got) {
					System.out.println("This country is not in the list! Try again.\n");
				}
			} else if (userInput == 4) {
				listCountries();
				scnr.nextLine();
				System.out.println("Which country would you like to modify the population?");
				String choice = scnr.nextLine();
				boolean got = false;
				for (int i = 0; i < lists.size(); i++) {
					if (lists.get(i).getCountryName().equalsIgnoreCase(choice)) {
						System.out.println("Enter the new population");
						int population = scnr.nextInt();
						lists.get(i).setPopulation(population);
						System.out.println("Population of " + choice + " modified.\n");
						writeToFile(lists);
						got = true;
					}
				}
				if (!got) {
					System.out.println("This country is not in the list! Try again.\n");
				}
			} else if (userInput == 5) {
				break;
			} else {
				System.out.println("Invalid Choice! Try again.");
			}
		}
		System.out.println("Goodbye.");
		scnr.close();

	}

	private static Country getCountryFromUser(Scanner scnr) {
		scnr.nextLine();
		String name = Validator.getString(scnr, "Enter Country name: ");
		// name=name.toUpperCase().substring(0,1)+name.toLowerCase().substring(1);
		int population = Validator.getInt(scnr, "Enter population: ");
		String capital = Validator.getString(scnr, "Enter the capital: ");
		return new Country(name, population, capital);
	}

	public static List<Country> readFile() {
		try {
			List<String> lines = Files.readAllLines(filePath);
			Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
			List<Country> countries = new ArrayList<>();
			for (String line : lines) {
				String[] parts = line.split(",");
				// System.out.println(Arrays.toString(parts));
				String countryName = parts[0];
				int population = Integer.parseInt(parts[1]);
				String capitalName = parts[2];

				countries.add(new Country(countryName, population, capitalName));
			}

			return countries;
		} catch (IOException e) {
			System.out.println("Unable to read file.");
			return new ArrayList<>();
		}
	}

	public static void appendLineToFile(Country thing) {

		// String line = thing.getCountryName() + "," + thing.getPopulation() ;
		String line = String.format("%s,%d,%s", thing.getCountryName(), thing.getPopulation(), thing.getCapital());
		List<String> lines = Collections.singletonList(line);
		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}

	public static void writeToFile(List<Country> list) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("C:\\Users\\Kavitha\\eclipse-workspace\\lab17\\.settings\\country.txt"));
			for (Country c : list) {
				bw.write(c.getCountryName() + "," + c.getPopulation() + "," + c.getCapital());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void listCountries() {
		lists = readFile();
		System.out.printf("%-15s%-15s%-15s\n", "Country Name", "Population", "Capital");
		System.out.printf("%-15s%-15s%-15s\n", "============", "==========", "=======");
		for (Country list : lists) {
			// System.out.println(i++ + ". " + list);
			System.out.printf("%-15s%-15d%-15s\n", list.getCountryName(), list.getPopulation(), list.getCapital());

		}
		System.out.println();
	}

}
