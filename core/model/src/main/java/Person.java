import java.util.Date;
import java.util.Set;

public class Person {

	private int id;
	private Name name;
	private Address address;
	private Date birthday;
	private float gwa;
	private Date dateHired;
	private boolean currentlyEmployed;
	private ContactInformation contactInformation;
	private Set<Role> roles;

	public Person() {}
	
	public Person(Name name, Address address, Date birthday, float gwa, 
					Date dateHired, boolean currentlyEmployed) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.gwa = gwa;
		this.dateHired = dateHired;
		this.currentlyEmployed = currentlyEmployed;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setGwa(float gwa) {
		this.gwa = gwa;
	}

	public float getGwa() {
		return gwa;
	}

	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	public Date getDateHired() {
		return dateHired;
	}

	public void setCurrentlyEmployed(boolean currentlyEmployed) {
		this.currentlyEmployed = currentlyEmployed;
	}

	public boolean getCurrentlyEmployed() {
		return currentlyEmployed;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Role> getRoles() {
		return roles;
	}	
}