import javax.persistence.MappedSuperclass;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class EntityParent {
	
	private long id;

	@GenericGenerator(name = "increment_generator", strategy = "increment")
	@Id @GeneratedValue(generator = "increment_generator")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}