import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Iterator;

public class Service {
	
	private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
	

	public static Person getPersonInput(boolean update, Person personToUpdate) {
		String firstName = "";
		String middleName = "";
		String lastName = "";
		int gender = 0;
		int streetNo = 0;
		String barangay = "";
		String municipality = "";
		int zipCode = 0;
		String birthday = "";
		float gwa = 0;
		String dateHired = "";
		int currentlyEmployed = 0;
		String landline = "";
		String mobileNumber = "";
		String email = "";
		String roleName = "";
		Set<Role> roles = new HashSet<Role>();
		Person person = null;
		try {

			firstName = Util.validateInputString(firstName, 20, "Enter the first name: ");
			middleName = Util.validateInputString(middleName, 20, "Enter the middle name: ");
			lastName = Util.validateInputString(lastName, 20, "Enter the last name: ");

			Name personName = new Name(firstName, middleName, lastName);
			
			while (gender != 1 && gender != 2) {
				try {
					System.out.print("Enter the gender, 1 for MALE or 2 for FEMALE: ");
					gender = scanner.nextInt();
					scanner.nextLine();
					if (gender != 1 && gender != 2) {
						System.out.println("***** The value should only be 1 for MALE or 2 for FEMALE!");
					}
				} catch (java.util.InputMismatchException e) {
					System.out.println("***** Invalid input for gender!");
					scanner.nextLine();
				}
			}

			birthday = Util.validateInputDate(birthday, "Enter the birthday (MM-DD-YYYY): ");

			while (gwa < 1.0 || gwa > 3.0) {
				try{
					System.out.print("Enter the gwa: ");
					gwa = scanner.nextFloat();
					scanner.nextLine();
					if(gwa < 1.0 || gwa > 3.0) {
						System.out.println("***** Invalid GWA input!");
					}
				} catch (java.util.InputMismatchException e) {
					System.out.println("***** Invalid GWA input!");
					scanner.nextLine();
				}
			}

			dateHired = Util.validateInputDate(dateHired, "Enter the date hired (MM-DD-YYYY): ");
			
			while (currentlyEmployed != 1 && currentlyEmployed != 2) {
				try{
					System.out.print("Enter 1 if currently employed or 2 if not: ");
					currentlyEmployed = scanner.nextInt();
					scanner.nextLine();
					if (currentlyEmployed != 1 && currentlyEmployed != 2) {
						System.out.println("***** The value should only be 1 for true or 2 for false!");
					}
				} catch (java.util.InputMismatchException e) {
					System.out.println("***** Invalid input!");
					scanner.nextLine();
				}
			}

			streetNo = Util.validateInputInt(streetNo, "The following entries are for the address info.\nEnter the street number: ");
			barangay = Util.validateInputString(barangay, 20, "Enter the barangay name: ");	
			municipality = Util.validateInputString(municipality, 20, "Enter the municipality name: ");
			zipCode = Util.validateInputInt(zipCode, "Enter the zip code: ");			
			landline = Util.validateInputString(landline, 20, "The following entries are for the contact information.\nEnter the landline: ");
			mobileNumber = Util.validateInputString(mobileNumber, 20, "Enter the mobile number: ");
			email = Util.validateInputString(email, 30, "Enter the email: ");

			Address personAddress = new Address(streetNo, barangay, municipality, zipCode);
			Dao.create(personAddress);

			if (update) {
				personToUpdate.setName(personName);
				personToUpdate.setGender((gender == 1 ? Gender.MALE : Gender.FEMALE));
				personToUpdate.setAddress(personAddress);
				personToUpdate.setBirthday(java.sql.Date.valueOf(LocalDate.parse(birthday, formatter)));
				personToUpdate.setGwa(gwa);
				personToUpdate.setDateHired(java.sql.Date.valueOf(LocalDate.parse(dateHired, formatter)));
				personToUpdate.setCurrentlyEmployed((currentlyEmployed == 1 ? true : false));
				if (personToUpdate.getContactInformation() != null) {
					personToUpdate.getContactInformation().setLandline(landline);
					personToUpdate.getContactInformation().setMobileNumber(mobileNumber);
					personToUpdate.getContactInformation().setEmail(email);
				}
				else {
					ContactInformation personContactInformation = new ContactInformation(landline, mobileNumber, email);
					personToUpdate.setContactInformation(personContactInformation);
					personContactInformation.setPerson(personToUpdate);
				}
				personToUpdate.setRoles(roles);
				addRolesToPerson(personToUpdate.getRoles());
			}
			else {
				ContactInformation personContactInformation = new ContactInformation(landline, mobileNumber, email);
				person = new Person(personName, (gender == 1 ? Gender.MALE : Gender.FEMALE), personAddress, java.sql.Date.valueOf(LocalDate.parse(birthday, formatter)), gwa, 
								java.sql.Date.valueOf(LocalDate.parse(dateHired, formatter)), (currentlyEmployed == 1 ? true : false));
				personContactInformation.setPerson(person);
				person.setContactInformation(personContactInformation);
				person.setRoles(roles);
				addRolesToPerson(person.getRoles());
			}

		} catch (java.util.InputMismatchException e) {
			e.printStackTrace();
		}

		return update ? personToUpdate : person;
	}

	public static void addRolesToPerson(Set<Role> roles) {
		int roleInput = -1;
		Role role;
		while (roleInput != 0) {
			try {
				RoleView.printRoleList();
				System.out.print("\n\nEnter the role id you want to add (press 0 if you are done): ");
				roleInput = scanner.nextInt();
				scanner.nextLine();
				role = (Role) Dao.get(roleInput, "Role");
				if (role != null) {
					Iterator<Role> iterator = roles.iterator();
					boolean roleExist = false;
				    while (iterator.hasNext()) {
				        Role setRole = iterator.next();
				        if(setRole.equals(role)) {
				            roleExist = true;
				        }
				    }
					if (roleExist) {
						System.out.println("\n\n*****\tThe role has already been added on this person!");
					}
					else {
						roles.add(role);
						System.out.println("\n\n*****\tSuccessfully added role " + role.getName() + " on this person!");
					}
				}
				else if (roleInput != 0) {
					System.out.println("\n\n*****\tWrong Id!");
				}
			} catch (java.util.InputMismatchException e) {
				e.printStackTrace();
			}
		}
	}

	public static Role getRoleInput(boolean update, Role roleToUpdate) {
		String roleName = "";
		roleName = Util.validateInputString(roleName, 20, "\n\nEnter the new role name: ");
		if (update) {
			roleToUpdate.setName(roleName);
		}
		return update ? roleToUpdate : new Role(roleName);
	}

	public static <T> ContactInformation getContactInput(boolean update, final T object) {
		ContactInformation contactInformation = null;
		Person person = null;
		String landline = "";
		String mobileNumber = "";
		String email = "";

		landline = Util.validateInputString(landline, 20, "\n\nThe following entries are for the contact information.\nEnter the new landline: ");
		mobileNumber = Util.validateInputString(mobileNumber, 20, "Enter the new mobile number: ");
		email = Util.validateInputString(email, 30, "Enter the new email: ");

		if (update) {
			contactInformation = (ContactInformation) object;
			contactInformation.setLandline(landline);
			contactInformation.setMobileNumber(mobileNumber);
			contactInformation.setEmail(email);
			
		}
		else {
			contactInformation = new ContactInformation(landline, mobileNumber, email);
			person = (Person) object;
			person.setContactInformation(contactInformation);
			contactInformation.setPerson(person);
			Dao.update(person);	
		}

		return contactInformation;
	}
}