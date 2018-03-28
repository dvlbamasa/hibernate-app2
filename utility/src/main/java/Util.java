import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;


public class Util {
	
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);

	public static String validateInputString(String property, int stringLength, String prompt) {
		while (!validateInputLength(property, stringLength)) {
			System.out.print(prompt);
			property = scanner.nextLine();
			if (!validateInputLength(property, stringLength)) {
				System.out.println("***** The maximum length is " + stringLength + " characters and must not be null!");
			}
		}
		return property;	
	}

	public static int validateInputInt(int property, String prompt) {
		boolean invalid = true;
		while (invalid) {
			try {
				System.out.print(prompt);
				property = scanner.nextInt();
				scanner.nextLine();
				invalid = false;
			} catch (java.util.InputMismatchException e) {
				System.out.println("***** Invalid input!");
				scanner.nextLine();
				invalid = true;
			}
		}
		return property;
	}

	public static String validateInputDate(String property, String prompt) {
		boolean invalid = true;
		while (invalid) {
			try {
				System.out.print(prompt);
				property = scanner.nextLine();	
				LocalDate.parse(property, formatter);
				invalid = false;
			} catch (java.time.format.DateTimeParseException e) {
				System.out.println("***** Wrong input for date! Please follow the format and try again.");
				invalid = true;
			}
		}
		return property;
	}

	public static boolean validateInputLength(String property, int stringLength) {
		if (property.length() < stringLength && !property.equals("")) {
			return true;
		}
		if (property.equals("")) {
			return false;
		}
		else {
			return false;
		}

	}

	public static void printStartScreen() {
		System.out.println("\n\n*****	Welcome to the Simple Hibernate App!	*****");
	}

	public static void printDBEmpty() {
		System.out.println("The Database is empty. Add a new person first.");
	}

	public static void printMenu() {
		System.out.print("\n\nChoose the entity that you want to modify:" +
							"\n1. Person" +
							"\n2. Role" +
							"\n3. Contact Information" +
							"\n0. Exit App" +
							"\nEnter your choice: ");
	}

	public static void printPersonFunctionalities() {
		System.out.print("Person Functionalities: " +
							"\n1. Create Person" +
							"\n2. Delete Person" +
							"\n3. Update Person" +
							"\n4. List Person" +
							"\n5. Back" +
							"\nEnter your choice: ");
	}

	public static void printListPersonOptions() {
		System.out.print("List Person by: " +
							"\n1. GWA" +
							"\n2. Date Hired" +
							"\n3. Last Name" +
							"\n4. Back" +
							"\nEnter your choice: ");
	}

	public static void printRoleFunctionalities() {
		System.out.print("Role Functionalities: " +
							"\n1. Add Role" +
							"\n2. Update Role" +
							"\n3. List Role" +
							"\n4. Add Person Role" +
							"\n5. Delete Person Role" +
							"\n6. Back" +
							"\nEnter your choice: ");
	}

	public static void printContactFunctionalities() {
		System.out.print("Contact Functionalities: " +
							"\n1. Add Contact" +
							"\n2. Update Contact" +
							"\n3. Delete Contact" +
							"\n4. Back" +
							"\nEnter your choice: ");
	}
}