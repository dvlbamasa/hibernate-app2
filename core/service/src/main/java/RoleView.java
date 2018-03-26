import java.util.Set;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RoleView {
	
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static final int CREATE_ROLE = 1;
	private static final int UPDATE_ROLE = 2;
	private static final int DELETE_ROLE = 3;
	private static final int LIST_ROLE = 4;
	private static final int ADD_PERSON_ROLE = 5;
	private static final int DELETE_PERSON_ROLE = 6;
	private static final int BACK = 7;

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
				System.out.println("\n\n*****\tSuccessfully created a new Role!");
			}
			/*
			*	Update a Role
			*/
			else if (userInput == UPDATE_ROLE) {
				System.out.print("Enter the index of the Role: ");
				roleIndex = scanner.nextInt();
				scanner.nextLine();
				Role role = (Role) Dao.get(roleIndex, "Role");
				if (role != null) {
					Dao.update(Service.getRoleInput(true, role));
					System.out.println("\n\n*****\tSuccessfully updated a Role!");
				}
				else {
					System.out.println("\n\n*****\tWrong Index!");
				}
			}
			/*
			*	Delete a Role
			*/
			else if (userInput == DELETE_ROLE) {
				System.out.print("Enter the index of the Role: ");
				roleIndex = scanner.nextInt();
				scanner.nextLine();
				Role role = (Role) Dao.get(roleIndex, "Role");
				if (role != null) {
					Dao.delete(role);
					System.out.println("\n\n*****\tSuccessfully deleted a Role!");
				}
				else {
					System.out.println("\n\n*****\tWrong Index!");
				}
			}
			/*
			*	List all the Roles
			*/
			else if (userInput == LIST_ROLE) {
				List<Role> roles = (List<Role>) Dao.getList("Role");
				System.out.println("\n\n******** List of Roles \t*********\n\n");
				if(roles.isEmpty()) {
					System.out.println("\n\n*****\tThere are no roles.");
				}
				else {
					for(Role role : roles) {
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
			/*
			*	Add a role to a Person
			*/
			else if (userInput == ADD_PERSON_ROLE) {
				System.out.print("Enter the index of the Role: ");
				roleIndex = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter the index of the Person you want to add to this Role: ");
				int personIndex = scanner.nextInt();
				scanner.nextLine();

				Role newRole = (Role)Dao.get(roleIndex, "Role");
				Person person = (Person)Dao.get(personIndex, "Person");
				person.getRoles().add(newRole);
				Dao.update(person);
				System.out.println("\n\n*****\tSuccessfully added a role to a person!");
			}
			/*
			*	Remove a role from a Person
			*/
			else if (userInput == DELETE_PERSON_ROLE) {
				System.out.print("Enter the index of the Role: ");
				roleIndex = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter the index of the Person you want to remove from this Role: ");
				int personIndex = scanner.nextInt();
				scanner.nextLine();

				Role newRole = (Role)Dao.get(roleIndex, "Role");
				Person person = (Person)Dao.get(personIndex, "Person");

				Set<Role> roles = person.getRoles();
				Iterator<Role> iterator = roles.iterator();
			    while(iterator.hasNext()) {
			        Role setRole = iterator.next();
			        if(setRole.equals(newRole)) {
			            iterator.remove();
			        }
			    }
				Dao.update(person);
				System.out.println("\n\n*****\tSuccessfully removed a role from a person!");
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