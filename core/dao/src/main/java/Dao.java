import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import static org.hibernate.Criteria.DISTINCT_ROOT_ENTITY;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import java.util.List;
import java.io.Serializable;
import java.util.stream.Collectors;

public class Dao {
	
	public Dao() {}
	
	
	public static <T> void create(T object) {
	   	Session session = HibernateSession.getSession();
	   	Transaction transaction = session.beginTransaction();
	   	try {
		   	session.save(object);
			transaction.commit();
		} catch (HibernateException e) {
        	if (transaction!=null) {
        		transaction.rollback();	
        	}
        	e.printStackTrace(); 
     	} finally {
        	session.close(); 
      	}
  	}

  	public static Object get(long id, String object) {
  		Session session = HibernateSession.getSession();
  		Object resultObject = null;
  		try {
  			Criteria criteria = session.createCriteria(object);
  			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
  			criteria.add(Restrictions.eq("id", id));
			List results = criteria.list();
			if (results.size() == 1) {
				resultObject = results.get(0);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return resultObject;
	}
  	

	public static <T> void update(T object) {
  		Session session = HibernateSession.getSession();
  		Transaction transaction = session.beginTransaction();
	  	try{
	  		session.update(object);
	  		transaction.commit();
	  	} catch (HibernateException e) {
        	if (transaction!=null) {
        		transaction.rollback();	
        	}
        	e.printStackTrace(); 
     	} finally {
        	session.close(); 
      	}
  	}

  	public static <T> void delete(T object) {
  		Session session = HibernateSession.getSession();
  		Transaction transaction = session.beginTransaction();
	  	try{
	  		session.update(object);
	  		session.delete(object);
	  		transaction.commit();
	  	} catch (HibernateException e) {
        	if (transaction!=null) {
        		transaction.rollback();	
        	}
        	e.printStackTrace(); 
     	} finally {
        	session.close(); 
      	}
  	}


  	public static boolean isDBEmpty() {
  		Session session = HibernateSession.getSession();
  		List results = null;
  		try {
			Criteria criteria = session.createCriteria("Person");
			results = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return (results.size() > 0 ? false : true);
  	}

  	public static List getList(String object) {
  		Session session = HibernateSession.getSession();
  		List results = null;
  		try {
  			Criteria criteria = session.createCriteria(object);
  			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			results = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return results;
  	}

  	public static List getOrderedList(String object, String order) {
  		Session session = HibernateSession.getSession();
  		List results = null;
  		try {
  			Criteria criteria = session.createCriteria(object);
  			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
  			criteria.addOrder(Order.asc(order));
			results = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return results;
  	}
}