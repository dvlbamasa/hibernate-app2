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
			Person person;
			/*
			*	Create a new Person
			*/
			switch (userInput) {

				case CREATE_PERSON :
					person = Service.getPersonInput(false, null);
					Dao.create(person);
					PersonListView.printPersonInformation(person, "");
					System.out.println("\n\n*****\tSuccessfully created a new Person!");
					break;

				case DELETE_PERSON :
					if (!Dao.isDBEmpty()) {
						PersonListView.printPersonNameId();
						personIndex = Util.validateInputInt(personIndex, "\n\nEnter the id of the Person: ");
						person = (Person) Dao.get(personIndex, "Person");
						if (person != null) {
							Dao.delete(person);
							if (!Dao.isDBEmpty()) {
								PersonListView.listLastName();
							}
							System.out.println("\n\n*****\tSuccessfully deleted a Person!");
						}
						else {
							System.out.println("\n\n*****\tWrong Index!");
						}
					}
					else {
						System.out.println("\n\n*****\tThe person list is empty!");
					}
					break;

				case UPDATE_PERSON :
					if (!Dao.isDBEmpty()) {
						PersonListView.printPersonNameId();
						personIndex = Util.validateInputInt(personIndex, "\n\nEnter the id of the Person: ");
						person = (Person) Dao.get(personIndex, "Person");
						if (person != null) {
							Dao.update(Service.getPersonInput(true, person));
							PersonListView.printPersonInformation(person, "");
							System.out.println("\n\n*****\tSuccessfully updated a Person!");
						}
						else {
							System.out.println("\n\n*****\tWrong Index!");
						}
					}
					else {
						System.out.println("\n\n*****\tThe person list is empty!");
					}
					break;

				case LIST_OPTIONS :
					if (!Dao.isDBEmpty()) {
						PersonListView.showPersonListView();
					}
					else {
						System.out.println("\n\n*****\tThe person list is empty!");
					}
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
}