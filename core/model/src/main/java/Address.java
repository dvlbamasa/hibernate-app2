import java.util.Set;

public class Address {

	private int id;
	private int streetNo;
	private String barangay;
	private String municipality;
	private int zipCode;
	private Set<Person> persons;

	public Address() {}

	public Address(int streetNo, String barangay, String municipality, int zipCode) {
		this.streetNo = streetNo;
		this.barangay = barangay;
		this.municipality = municipality;
		this.zipCode = zipCode;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}

	public int getStreetNo() {
		return streetNo;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getBarangay() {
		return barangay;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public Set<Person> getPersons() {
		return persons;
	}
}