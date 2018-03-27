import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

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
		Set<Person> persons = new HashSet<Person>();
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
			//roleName = Util.validateInputString(roleName, 20, "Enter the role name: ");
			

			Address personAddress = new Address(streetNo, barangay, municipality, zipCode);
			
			ContactInformation personContactInformation = new ContactInformation(landline, mobileNumber, email);

			//Role role = new Role(roleName);

			Dao.create(personAddress);

			person = new Person(personName, (gender == 1 ? Person.Gender.MALE : Person.Gender.FEMALE), personAddress, java.sql.Date.valueOf(LocalDate.parse(birthday, formatter)), gwa, 
								java.sql.Date.valueOf(LocalDate.parse(dateHired, formatter)), (currentlyEmployed == 1 ? true : false));
			
			personContactInformation.setPerson(person);
			person.setContactInformation(personContactInformation);

			roles = addRolesToPerson(roles, person);
			//roles.add(new Role(roleName));
			//persons.add(person);

			//role.setPersons(persons);
			person.setRoles(roles);

			if (update) {
				personToUpdate.setName(personName);
				personToUpdate.setGender((gender == 1 ? Person.Gender.MALE : Person.Gender.FEMALE));
				personToUpdate.setAddress(personAddress);
				personToUpdate.setBirthday(java.sql.Date.valueOf(LocalDate.parse(birthday, formatter)));
				personToUpdate.setGwa(gwa);
				personToUpdate.setDateHired(java.sql.Date.valueOf(LocalDate.parse(dateHired, formatter)));
				personToUpdate.setCurrentlyEmployed((currentlyEmployed == 1 ? true : false));
				person.setContactInformation(personContactInformation);
				person.setRoles(roles);
			}

		} catch (java.util.InputMismatchException e) {
			e.printStackTrace();
		}

		return update ? personToUpdate : person;
	}

	public static Set<Role> addRolesToPerson(Set<Role> roles, Person person) {
		int roleInput = -1;
		Role role;
		while (roleInput != 0) {
			try {
				RoleView.printRoleList();
				System.out.print("\n\nEnter the role id you want to add to a person (press 0 if you are done): ");
				roleInput = scanner.nextInt();
				role = (Role) Dao.get(roleInput, "Role");
				if(role != null) {
					roles.add(role);
					role.getPersons().add(person);
				}
				else {
					System.out.println("\n\n*****\tWrong Id!");
				}
			} catch (java.util.InputMismatchException e) {
				e.printStackTrace();
			}
		}
		return roles;
		
	}

	public static Role getRoleInput(boolean update, Role roleToUpdate) {
		String roleName;
		System.out.print("Enter the new name of the role: ");
		roleName = scanner.nextLine();
		if (update) {
			roleToUpdate.setName(roleName);
		}
		return update ? roleToUpdate : new Role(roleName);
	}

	public static <T> ContactInformation getContactInput(boolean update, final T object) {
		ContactInformation contactInformation = null;
		Person person = null;
		String landline;
		String mobileNumber;
		String email;
		int index;

		System.out.print("\nThe following entries are for the contact information.\nEnter the new landline: ");
		landline = scanner.nextLine();
		System.out.print("Enter the new mobile number: ");
		mobileNumber = scanner.nextLine();
		System.out.print("Enter the new email: ");
		email = scanner.nextLine();

		if (!update) {
			contactInformation = new ContactInformation(landline, mobileNumber, email);
			person = (Person) object;
			person.setContactInformation(contactInformation);
			contactInformation.setPerson(person);
			Dao.update(person);
		}
		else {
			contactInformation = (ContactInformation) object;
			contactInformation.setLandline(landline);
			contactInformation.setMobileNumber(mobileNumber);
			contactInformation.setEmail(email);	
		}

		return contactInformation;
	}
}