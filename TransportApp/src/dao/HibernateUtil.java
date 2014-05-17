package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	static Session session = null;
		
	public SessionFactory getSessionFactory() {
		
			try {
				 SessionFactory mysf = new

				Configuration().configure().buildSessionFactory();
				
				 return mysf;
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}
		
	}
}
