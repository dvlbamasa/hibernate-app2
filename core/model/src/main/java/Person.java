import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "person")
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

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Embedded
	public Name getName() {
		return name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Address getAddress() {
		return address;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "birthday")
	public Date getBirthday() {
		return birthday;
	}
	
	public void setGwa(float gwa) {
		this.gwa = gwa;
	}

	@Column(name = "gwa")
	public float getGwa() {
		return gwa;
	}

	
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	@Column(name = "date_hired")
	public Date getDateHired() {
		return dateHired;
	}

	public void setCurrentlyEmployed(boolean currentlyEmployed) {
		this.currentlyEmployed = currentlyEmployed;
	}

	@Column(name = "currently_employed")
	public boolean getCurrentlyEmployed() {
		return currentlyEmployed;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "person_role", joinColumns = { 
			@JoinColumn(name = "person_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", 
					nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}	
}