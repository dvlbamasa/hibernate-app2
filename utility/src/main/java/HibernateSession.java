import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
	
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public HibernateSession () {}

	public static Session getSession() {
		Session session = sessionFactory.openSession();
		return session;
	}
	
}