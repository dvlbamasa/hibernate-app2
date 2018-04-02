import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends EntityParent{

	private int streetNo;
	private String barangay;
	private String municipality;
	private int zipCode;

	public Address() {}

	public Address(int streetNo, String barangay, String municipality, int zipCode) {
		this.streetNo = streetNo;
		this.barangay = barangay;
		this.municipality = municipality;
		this.zipCode = zipCode;
	}

	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}

	@Column(name = "street_no", nullable = false)
	public int getStreetNo() {
		return streetNo;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	@Column(name = "barangay", nullable = false)
	public String getBarangay() {
		return barangay;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	@Column(name = "municipality", nullable = false)
	public String getMunicipality() {
		return municipality;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "zip_code", nullable = false)
	public int getZipCode() {
		return zipCode;
	}
}