import java.util.Set;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "role")
public class Role {

	private int id;
	private String name;
	private Set<Person> persons;

	public Role() {}
	
	public Role(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "person")
	public Set<Person> getPersons() {
		return persons;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} 
		if (!this.getClass().equals(obj.getClass())) {
			return false;	
		} 
		Role obj2 = (Role)obj;
		if((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) {
			 return true;
		}
		return false;
  	}
   
   	public int hashCode() {
		int tmp = 0;
		tmp = ( id + name ).hashCode();
		return tmp;
   	}
}