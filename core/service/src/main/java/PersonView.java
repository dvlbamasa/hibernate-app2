import java.util.Scanner;

public class PersonView {

	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static final int CREATE_PERSON = 1;
	private static final int DELETE_PERSON = 2;
	private static final int UPDATE_PERSON = 3;
	private static final int LIST_OPTIONS = 4;
	private static final int BACK = 5;

	public static void showPersonView() {
		try {
			Util.printPersonFunctionalities();
			int userInput = scanner.nextInt();
			scanner.nextLine();
			int personIndex = 0;
			/*
			*	Create a new Person
			*/
			if (userInput == CREATE_PERSON) {
				Dao.create(Service.getPersonInput(false, null));
				System.out.println("\n\n*****\tSuccessfully created a new Person!");
			}
			/*
			*	Delete a Person
			*/
			else if (userInput == DELETE_PERSON) {
				System.out.print("Enter the index of the Person: ");
				personIndex = scanner.nextInt();
				scanner.nextLine();
				Person person = (Person) Dao.get(personIndex, "Person");
				if (person != null) {
					Dao.delete(person);
					System.out.println("\n\n*****\tSuccessfully deleted a Person!");
				}
				else {
					System.out.println("\n\n*****\tWrong Index!");
				}
			}
			/*
			*	Update a Person
			*/
			else if (userInput == UPDATE_PERSON) {
				System.out.print("Enter the index of the Person: ");
				personIndex = scanner.nextInt();
				scanner.nextLine();
				Person person = (Person) Dao.get(personIndex, "Person");
				if (person != null) {
					Dao.update(Service.getPersonInput(true, person));
					System.out.println("\n\n*****\tSuccessfully updated a Person!");
				}
				else {
					System.out.println("\n\n*****\tWrong Index!");
				}
			}
			/*
			*	List Person
			*/
			else if (userInput == LIST_OPTIONS) {
				PersonListView.showPersonListView();
			}
			/*
			*	Go Back
			*/
			else if (userInput == BACK) {
				return;
			}
			else {
				System.out.println("***Wrong input choice!");
			}
		} catch (java.util.InputMismatchException e) {
				System.out.println("***Wrong input choice!");
				scanner.nextLine();
		}
	}
}