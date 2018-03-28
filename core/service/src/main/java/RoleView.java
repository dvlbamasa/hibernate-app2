import java.util.Set;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RoleView {
	
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static final int CREATE_ROLE = 1;
	private static final int UPDATE_ROLE = 2;
	private static final int LIST_ROLE = 3;
	private static final int ADD_PERSON_ROLE = 4;
	private static final int DELETE_PERSON_ROLE = 5;
	private static final int BACK = 6;

	public static void showRoleView() {
		try {
			Util.printRoleFunctionalities();
			int userInput = scanner.nextInt();
			scanner.nextLine();
			int roleIndex = 0;
			/*
			*	Create a new Role
			*/
			if (userInput == CREATE_ROLE) {
				Dao.create(Service.getRoleInput(false, null));
				printRoleInformationList();
				System.out.println("\n\n*****\tSuccessfully created a new Role!");
			}
			/*
			*	Update a Role
			*/
			else if (userInput == UPDATE_ROLE) {
				printRoleList();
				roleIndex = Util.validateInputInt(roleIndex, "\n\nEnter the id of the Role: ");
				Role role = (Role) Dao.get(roleIndex, "Role");
				if (role != null) {
					Dao.update(Service.getRoleInput(true, role));
					printRoleInformationList();
					System.out.println("\n\n*****\tSuccessfully updated a Role!");
				}
				else {
					System.out.println("\n\n*****\tWrong Id!");
				}
			}
			/*
			*	List all the Roles
			*/
			else if (userInput == LIST_ROLE) {
				printRoleInformationList();
			}
			/*
			*	Add a role to a Person
			*/
			else if (userInput == ADD_PERSON_ROLE) {
				if (!Dao.isDBEmpty()) {
					int personIndex = 0;
					PersonListView.printPersonNameId();
					personIndex = Util.validateInputInt(personIndex, "\n\nEnter the id of the Person: ");
					Person person = (Person)Dao.get(personIndex, "Person");
					if (person != null) {
						Service.addRolesToPerson(person.getRoles());				
						Dao.update(person);
						printRoleInformationList();
						System.out.println("\n\n*****\tSuccessfully added roles!");
					}
					else {
						System.out.println("\n\n*****\tWrong Id!");
					}
				}
				else {
					System.out.println("\n\n*****\tThe person list is empty!");
				}
			}
			/*
			*	Remove a role from a Person
			*/
			else if (userInput == DELETE_PERSON_ROLE) {
				if (!Dao.isDBEmpty()) {
					printRoleList();
					roleIndex = Util.validateInputInt(roleIndex, "\n\nEnter the id of the Role: ");
					Role newRole = (Role)Dao.get(roleIndex, "Role");
					if (newRole != null) {
						if (!printPersonsOnRole(newRole)) {
							int personIndex = 0;
							personIndex = Util.validateInputInt(personIndex, "\n\nEnter the id of the Person you want to remove from this Role: ");
							Person person = (Person)Dao.get(personIndex, "Person");
							if (person != null) {
								Set<Role> roles = person.getRoles();
								Iterator<Role> iterator = roles.iterator();
							    while (iterator.hasNext()) {
							        Role setRole = iterator.next();
							        if (setRole.equals(newRole)) {
							            iterator.remove();
							        }
							    }
								Dao.update(person);
								printRoleInformationList();
								System.out.println("\n\n*****\tSuccessfully removed a role from a person!");
							}
							else {
								System.out.println("\n\n*****\tWrong Id!");
							}	
						}
					}
					else {
						System.out.println("\n\n*****\tWrong Id!");
					}
				}
				else {
					System.out.println("\n\n*****\tThe person list is empty!");
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

	public static void printRoleInformationList() {
		List<Role> roles = (List<Role>) Dao.getList("Role");
		System.out.println("\n\n******** List of Roles with information \t*********\n");
		if (roles.isEmpty()) {
			System.out.println("\n\n*****\tThere are no roles.");
		}
		else {
			for (Role role : roles) {
				List<Person> persons = new ArrayList<Person>(role.getPersons());
				System.out.println("\nRole Id: " + role.getId() + " Role Name: " + role.getName());
				System.out.println("\tPersons having this role: ");
				if (persons.isEmpty()) {
					System.out.print("\t\tNone");
				}
				else {
					persons.forEach(
						(person) -> System.out.println("\t\tPerson Id: " + person.getId()+
														" Person Name: " + person.getName().getFirstName().trim() + " " +
														person.getName().getMiddleName().trim() + " " + 
														person.getName().getLastName().trim()));
				}
			}
		}
	}

	public static boolean printPersonsOnRole(Role role) {
		boolean isEmpty = false;
		List<Person> persons = new ArrayList<Person>(role.getPersons());
		System.out.println("\n\nPersons having this role: ");
		if (persons.isEmpty()) {
			isEmpty = true;
			System.out.println("\n\n*****\tThere are no persons on this role!");
		}
		else {
			isEmpty = false;
			persons.forEach(
				(person) -> System.out.println("\tPerson Id: " + person.getId()+
												" Person Name: " + person.getName().getFirstName().trim() + " " +
												person.getName().getMiddleName().trim() + " " + 
												person.getName().getLastName().trim()));
		}
		return isEmpty;
	}

	public static void printRoleList () {
		List<Role> roles = (List<Role>) Dao.getList("Role");
		System.out.println("\n\n******** List of Roles \t*********\n");
		if (roles.isEmpty()) {
			System.out.println("\n\n*****\tThere are no roles.");
		}
		else {
			for(Role role : roles) {
				System.out.print("\nRole Id: " + role.getId() + " Role Name: " + role.getName());
			}
		}
	}
}