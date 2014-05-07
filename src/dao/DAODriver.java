package dao;

import java.awt.Component;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stamm.Driver;
import stamm.Insurance;

public class DAODriver {
	static HibernateUtil hu = new HibernateUtil();

	public static List<Driver> getDriverist() {
		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery("select * from FAHRDIENST.DRIVER")
				.addEntity(Driver.class);
		List result = query.list();

		tx.commit();
		session.close();

		return result;
	}

	public static void saveData(List<Driver> dlist) {
		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		for (int i = 0; i < dlist.size(); i++)
			session.saveOrUpdate(dlist.get(i));
		tx.commit();
		session.close();
	}

	public static Driver getDriver(int driver) {
		
		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery("select * from FAHRDIENST.DRIVER WHERE id=" + driver)
				.addEntity(Driver.class);
		List result = query.list();

		tx.commit();
		session.close();
		
		
		return (Driver) result.get(0);
	}

}
