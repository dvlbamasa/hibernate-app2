import java.util.logging.Level;

public class Main {

	public static void main(String [] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		new PersonApp();
	}
}