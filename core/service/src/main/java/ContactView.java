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
					if (!Dao.isDBEmpty()) {
						PersonListView.printPersonNameId();
						int personIndex = 0;
						personIndex = Util.validateInputInt(personIndex, "\n\nEnter the id of the Person associated with this contact information: ");
						person = (Person)Dao.get(personIndex, "Person");
						if (person != null) {
							if (person.getContactInformation() != null) {
								System.out.println("\n\n*****\tThe person has a contact information already!");
							}
							else {
								Service.getContactInput(false, person);
								PersonListView.printPersonInformation(person, "");
								System.out.println("\n\n*****\tSuccessfully created a new contact information!");	
							}
						}
						else {
							System.out.println("\n\n*****\tWrong id!");
						}
					}
					else {
						System.out.println("\n\n*****\tThe person list is empty!");
					}
					break;

				case UPDATE_CONTACT : 
					if (!Dao.isDBEmpty()) {
						PersonListView.printPersonNameId();
						contactIndex = Util.validateInputInt(contactIndex, "\n\nEnter the id of the Contact Information: ");
						person = (Person)Dao.get(contactIndex, "Person");
						contactInformation = (ContactInformation) Dao.get(contactIndex, "ContactInformation");
						if (person != null) {
							if (person.getContactInformation() == null) {
								System.out.println("\n\n*****\tThe person has no contact information to update!");
							}
							else {
								contactInformation = person.getContactInformation();
								Dao.update(Service.getContactInput(true, contactInformation));
								PersonListView.printPersonInformation(contactInformation.getPerson(), "");
								System.out.println("\n\n*****\tSuccessfully updated a contact information!");
							}
						}
						else {
							System.out.println("\n\n*****\tWrong Id!");
						}
					}
					else {
						System.out.println("\n\n*****\tThe person list is empty!");
					}
					break;

				case DELETE_CONTACT :
					if (!Dao.isDBEmpty()) {
						PersonListView.printPersonNameId();
						contactIndex = Util.validateInputInt(contactIndex, "\n\nEnter the id of the Contact Information: ");
						person = (Person)Dao.get(contactIndex, "Person");
						if (person != null) {
							if (person.getContactInformation() == null) {
								System.out.println("\n\n*****\tThe person has no contact information already!");
							}
							else {
								contactInformation = person.getContactInformation();
								Dao.delete(contactInformation);	
								person.setContactInformation(null);
								PersonListView.printPersonInformation(person, "");
								System.out.println("\n\n*****\tSuccessfully deleted a contact information!");
							}
						}
						else {
							System.out.println("\n\n*****\tWrong Id!");
						}
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