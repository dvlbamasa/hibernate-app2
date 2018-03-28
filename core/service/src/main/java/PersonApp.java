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
		Person person = Service.getPersonInput(false, null);
		Dao.create(person);
		PersonListView.printPersonInformation(person, "");
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
				switch (userInput) {

					case PERSON : 
						PersonView.showPersonView();
						break;

					case ROLE : 
						RoleView.showRoleView();
						break;

					case CONTACT_INFORMATION :
						ContactView.showContactView();
						break;

					case EXIT :
						System.out.println("Terminating Application...");
						break;

					default :
						System.out.println("***Wrong input choice!");
						break;
				}

			} catch (java.util.InputMismatchException e) {
				System.out.println("***Wrong input choice!");
				scanner.nextLine();
			}	
		}
		System.exit(0);
	}
}