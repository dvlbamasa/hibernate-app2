import java.util.Scanner;

public class PersonApp {
	
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static final int PERSON = 1;
	private static final int ROLE = 2;
	private static final int CONTACT_INFORMATION = 3;
	private static final int EXIT = 0;

	public PersonApp() {
		startApp();
	}

	public void startApp() {
		if(Dao.isDBEmpty()) {
			Util.printDBEmpty();
			insertInitialInput();
			showMenu();
		}
		else {
			showMenu();
		}
	}

	public void insertInitialInput() {
		Dao.create(Service.getPersonInput(false, null));
		System.out.println("\n\n*****\tSuccessfully created a new Person!");
	}

	public void showMenu() {
		int userInput = -1;
		while (userInput != EXIT) {
			try {
				Util.printStartScreen();
				Util.printMenu();
				userInput = scanner.nextInt();
				scanner.nextLine();
				if (userInput == PERSON) {
					PersonView.showPersonView();
				}
				else if (userInput == ROLE) {
					RoleView.showRoleView();
				}
				else if (userInput == CONTACT_INFORMATION) {
					ContactView.showContactView();
				}
				else if (userInput == EXIT) {
					System.out.println("Terminating Application...");
				}
				else {
					System.out.println("***Wrong input choice!");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("***Wrong input choice!");
				scanner.nextLine();
			}
		}
		System.exit(0);
	}
}