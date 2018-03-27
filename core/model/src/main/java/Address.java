import java.util.Set;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "address")
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
public class Address extends EntityParent{

	private long id;
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

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "address_id", nullable = false, unique = true)
	@Override
	public long getId() {
		return id;
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