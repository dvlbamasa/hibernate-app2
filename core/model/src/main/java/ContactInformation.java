public class ContactInformation {
	
	private int id;
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

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getLandline() {
		return landline;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}
}