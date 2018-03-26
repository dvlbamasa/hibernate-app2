public class Util {
	
	public static void printStartScreen() {
		System.out.println("\n\n*****	Welcome to the Simple Hibernate App!	*****");
	}

	public static void printDBEmpty() {
		System.out.println("The Database is empty. Add a new person first.");
	}

	public static void printMenu() {
		System.out.print("\n\nChoose the entity that you want to modify:" +
							"\n1. Person" +
							"\n2. Role" +
							"\n3. Contact Information" +
							"\n0. Exit App" +
							"\nEnter your choice: ");
	}

	public static void printPersonFunctionalities() {
		System.out.print("Person Functionalities: " +
							"\n1. Create Person" +
							"\n2. Delete Person" +
							"\n3. Update Person" +
							"\n4. List Person" +
							"\n5. Back" +
							"\nEnter your choice: ");
	}

	public static void printListPersonOptions() {
		System.out.print("List Person by: " +
							"\n1. GWA" +
							"\n2. Date Hired" +
							"\n3. Last Name" +
							"\n4. Back" +
							"\nEnter your choice: ");
	}

	public static void printRoleFunctionalities() {
		System.out.print("Role Functionalities: " +
							"\n1. Add Role" +
							"\n2. Update Role" +
							"\n3. Delete Role" +
							"\n4. List Role" +
							"\n5. Add Person Role" +
							"\n6. Delete Person Role" +
							"\n7. Back" +
							"\nEnter your choice: ");
	}

	public static void printContactFunctionalities() {
		System.out.print("Contact Functionalities: " +
							"\n1. Add Contact" +
							"\n2. Update Contact" +
							"\n3. Delete Contact" +
							"\n4. Back" +
							"\nEnter your choice: ");
	}
}