import java.util.Scanner;
import java.util.Collections;  
import java.util.List;  
import java.util.ArrayList;

public class PersonListView {
	
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static final int GWA = 1;
	private static final int DATE_HIRED = 2;
	private static final int LAST_NAME = 3;
	private static final int BACK = 4;


	public static void showPersonListView() {
		try {
			Util.printListPersonOptions();
			int userInput = scanner.nextInt();
			scanner.nextLine();
			switch (userInput) {
				case GWA :
					listGwa();
					break;

				case DATE_HIRED :
					listDateHired();
					break;

				case LAST_NAME : 
					listLastName();
					break;

				case BACK :
					return;

				default :
					System.out.println("***Wrong input choice!");
					break;

			}
		} catch (java.util.InputMismatchException e) {
				System.out.println("***Wrong input choice!");
				scanner.nextLine();
		}
	}

	public static void listGwa() {
		List<Person> persons = (List<Person>) Dao.getList("Person");
					Collections.sort(persons, (person1, person2) -> {
						return Float.compare(person1.getGwa(), person2.getGwa());
					});
		printOrderedList(persons, "GWA");
	}

	public static void listDateHired() {
		List<Person> persons = (List<Person>) Dao.getOrderedList("Person", "dateHired");
		printOrderedList(persons, "Date Hired");
	}

	public static void listLastName() {
		List<Person> persons = (List<Person>) Dao.getOrderedList("Person", "name.lastName");
		printOrderedList(persons, "Last Name");
	}

	private static void printOrderedList(List<Person> persons, String order) {
		if (persons.isEmpty()) {
			System.out.println("\n\n*****\tThe person list is empty!");
		}
		else {
			for(Person person : persons) {
				printPersonInformation(person, order);	
			}
		}
	}

	public static void printPersonInformation(Person person, String order) {
		System.out.println((order.equals("") ? "\n\n*********\t Updated Person Information \t***********" : ("\n\n*********\tList of Persons Ordered by " + order + "\t***********")) +
							"\nId: " + person.getId()+
							"\nName- " + 
							"\n\tFirst Name: " + person.getName().getFirstName() + 
							"\n\tMiddle Name: " + person.getName().getMiddleName() + 
							"\n\tLast Name: " + person.getName().getLastName() +
							"\nGender: " + person.getGender() +
							"\nAddress- " + 
							"\n\tStreet Number: " + person.getAddress().getStreetNo() + 
							"\n\tBarangay: " + person.getAddress().getBarangay() + 
							"\n\tMuncipality/City: " + person.getAddress().getMunicipality() +
							"\nBirthday: " + person.getBirthday() +
							"\nGWA: " + person.getGwa() +
							"\nDate Hired: " + person.getDateHired() +
							"\nCurrently Employed: " + (person.getCurrentlyEmployed() ? "Yes" : "No") +
							"\nContact Information -" +
							"\n\tLandline: " + ((person.getContactInformation() == null) ? "" : person.getContactInformation().getLandline()) +
							"\n\tMobile Number: " + ((person.getContactInformation() == null) ? "" : person.getContactInformation().getMobileNumber()) +
							"\n\tEmail Address: " + ((person.getContactInformation() == null) ? "" : person.getContactInformation().getEmail()));	
		List<Role> roles = new ArrayList<Role>(person.getRoles());
		System.out.print("\nRoles: ");
		roles.forEach(
			(role) -> System.out.print(role.getName().trim() + " "));	
	}

	public static void printPersonNameId() {
		List<Person> persons = (List<Person>) Dao.getList("Person");
		System.out.println("\n\n****** List of Persons ******");
		for (Person person : persons) {
			System.out.print("\nId: " + person.getId() + " Name: " + person.getName().getFirstName().trim() + " "
									+ person.getName().getMiddleName().trim() + " "
									+ person.getName().getLastName().trim() + " ");	
		}
	}
}