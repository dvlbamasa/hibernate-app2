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
			/*
			*	Create a new Contact Information
			*/
			if (userInput == CREATE_CONTACT) {
				System.out.print("Enter the index of the Person associated with this contact information: ");
				int personIndex = scanner.nextInt();
				scanner.nextLine();
				Person person = (Person)Dao.get(personIndex, "Person");
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
					System.out.println("\n\n*****\tWrong index!");
				}
				
			}
			/*
			*	Update a Contact Information
			*/
			else if (userInput == UPDATE_CONTACT) {
				System.out.print("Enter the index of the Contact Information: ");
				contactIndex = scanner.nextInt();
				scanner.nextLine();
				ContactInformation contactInformation = (ContactInformation) Dao.get(contactIndex, "ContactInformation");
				if (contactInformation != null) {
					Dao.update(Service.getContactInput(true, contactInformation));
					System.out.println("\n\n*****\tSuccessfully updated a contact information!");
				}
				else {
					System.out.println("\n\n*****\tWrong Index!");
				}	
			}
			/*
			*	Delete a Contact Information
			*/
			else if (userInput == DELETE_CONTACT) {
				System.out.print("Enter the index of the Contact Information: ");
				contactIndex = scanner.nextInt();
				scanner.nextLine();
				ContactInformation contactInformation = (ContactInformation) Dao.get(contactIndex, "ContactInformation");
				if (contactInformation != null) {
					Person person = contactInformation.getPerson();
					Dao.delete(contactInformation);
					person.setContactInformation(null);
					System.out.println("\n\n*****\tSuccessfully deleted a contact information!");
				}
				else {
					System.out.println("\n\n*****\tWrong Index!");
				}	
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