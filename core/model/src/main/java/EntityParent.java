import javax.persistence.MappedSuperclass;
import javax.persistence.*;

@MappedSuperclass
public abstract class EntityParent {
	
	private long id;

	@Id @GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}