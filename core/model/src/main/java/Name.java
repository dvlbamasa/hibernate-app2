import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Embeddable
public class Name {

	private String firstName;
	private String middleName;
	private String lastName;

	public Name() {}

	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "middle_name", nullable = false)
	public String getMiddleName() {
		return middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
}