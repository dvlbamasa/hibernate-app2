import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "contact_information")
public class ContactInformation {
	
	private long id;
	private String landline;
	private String mobileNumber;
	private String email;
	private Person person;

	public ContactInformation() {}

	public ContactInformation(String landline, String mobileNumber, String email) {
		this.landline = landline;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "person"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "contact_id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	@Column(name = "landline")
	public String getLandline() {
		return landline;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Person getPerson() {
		return person;
	}
}