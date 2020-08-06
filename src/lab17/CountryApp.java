package lab17;


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
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Countires Maintenance Application!");
		System.out.println();
		Scanner scnr = new Scanner(System.in);

		while (true) {
			System.out.println("1-See the list of countries");
			System.out.println("2-Add a country");
			System.out.println("3-Exit");
			System.out.println();
			System.out.println("Enter menu number: ");
			int userInput = scnr.nextInt();
			if (userInput == 1) {
				List<Country> lists = readFile();
				//Collections.sort(lists);
				//int i = 1;
				System.out.printf("%-15s%-15s\n","Country Name","Population");
				System.out.printf("%-15s%-15s\n","======= ====","==========");
				for (Country list : lists) {
					//System.out.println(i++ + ". " + list);
					System.out.printf("%-15s%-15d\n",list.getCountryName(),list.getPopulation());
					
				}System.out.println();
			} else if (userInput == 2) {
				Country country = getCountryFromUser(scnr);
				System.out.println("Adding " + country);
				appendLineToFile(country);

			} else if (userInput == 3) {
				break;
			} 			
				else {
				System.out.println("Invalid Choice!. Try again.");
			}
		}
		System.out.println("Goodbye.");
		scnr.close();

	}

	private static Country getCountryFromUser(Scanner scnr) {
		scnr.nextLine();
		String name = Validator.getString(scnr, "Enter Country name: ");	
		//name=name.toUpperCase().substring(0,1)+name.toLowerCase().substring(1);
		int population = Validator.getInt(scnr, "Enter population: ");		
		return new Country(name, population);
	}

	public static List<Country> readFile() {
		try {
			List<String> lines = Files.readAllLines(filePath);
			Collections.sort(lines,String.CASE_INSENSITIVE_ORDER);
			List<Country> countries = new ArrayList<>();
			for (String line : lines) {
				String[] parts = line.split(",");
				//System.out.println(Arrays.toString(parts));
				String countryName = parts[0];
				int population = Integer.parseInt(parts[1]);
				countries.add(new Country(countryName, population));
			}
			
			return countries;
		} catch (IOException e) {
			System.out.println("Unable to read file.");
			return new ArrayList<>();
		}
	}public static void appendLineToFile(Country thing) {
	
		//String line = thing.getCountryName() + "," + thing.getPopulation() ;	
		String line = String.format("%s,%d", thing.getCountryName(), thing.getPopulation());
		List<String> lines = Collections.singletonList(line);
		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}
	



}
