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
			List<Person> persons;
			switch (userInput) {
				case GWA :
					persons = (List<Person>) Dao.getList("Person.class");
					Collections.sort(persons, (person1, person2) -> {
						return Float.compare(person1.getGwa(), person2.getGwa());
					});
					printOrderedList(persons, "GWA");
					break;

				case DATE_HIRED :
					persons = (List<Person>) Dao.getOrderedList("Person.class", "date_hired");
					printOrderedList(persons, "Date Hired");
					break;

				case LAST_NAME : 
					persons = (List<Person>) Dao.getOrderedList("Person.class", "last_name");
					printOrderedList(persons, "Last Name");
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

	private static void printOrderedList(List<Person> persons, String order) {
		if (persons.isEmpty()) {
			System.out.println("The person list is empty!");
		}
		else {
			for(Person person : persons) {
				System.out.println("\n\n*********\tList of Persons Ordered by " + order + "\t***********" +
									"\nId: " + person.getId()+
									"\nName- " + 
									"\n\tFirst Name: " + person.getName().getFirstName() + 
									"\n\tMiddle Name: " + person.getName().getMiddleName() + 
									"\n\tLast Name: " + person.getName().getLastName() +
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
		}
	}
}