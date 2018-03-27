import java.util.Scanner;

public class ContactView {
	
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static final int CREATE_CONTACT = 1;
	private static final int UPDATE_CONTACT = 2;
	private static final int DELETE_CONTACT = 3;
	private static final int BACK = 4;

	public static void showContactView() {
		try {
			Util.printContactFunctionalities();
			int userInput = scanner.nextInt();
			scanner.nextLine();
			int contactIndex = 0;
			ContactInformation contactInformation;
			Person person;
			switch (userInput) {
				case CREATE_CONTACT :
					PersonListView.listLastName();
					System.out.print("\n\nEnter the id of the Person associated with this contact information: ");
					int personIndex = scanner.nextInt();
					scanner.nextLine();
					person = (Person)Dao.get(personIndex, "Person");
					if (person != null) {
						if (person.getContactInformation() != null) {
							System.out.println("\n\n*****\tThe person has a contact information already!");
						}
						else {
							Service.getContactInput(false, person);
							System.out.println("\n\n*****\tSuccessfully created a new contact information!");	
						}
					}
					else {
						System.out.println("\n\n*****\tWrong id!");
					}
					break;

				case UPDATE_CONTACT : 
					PersonListView.listLastName();
					System.out.print("\n\nEnter the id of the Contact Information: ");
					contactIndex = scanner.nextInt();
					scanner.nextLine();
					contactInformation = (ContactInformation) Dao.get(contactIndex, "ContactInformation");
					if (contactInformation != null) {
						Dao.update(Service.getContactInput(true, contactInformation));
						System.out.println("\n\n*****\tSuccessfully updated a contact information!");
					}
					else {
						System.out.println("\n\n*****\tWrong Id!");
					}
					break;

				case DELETE_CONTACT :
					PersonListView.listLastName();
					System.out.print("\n\nEnter the id of the Contact Information: ");
					contactIndex = scanner.nextInt();
					scanner.nextLine();
					contactInformation = (ContactInformation) Dao.get(contactIndex, "ContactInformation");
					if (contactInformation != null) {
						person = contactInformation.getPerson();
						Dao.delete(contactInformation);
						person.setContactInformation(null);
						System.out.println("\n\n*****\tSuccessfully deleted a contact information!");
					}
					else {
						System.out.println("\n\n*****\tWrong Id!");
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