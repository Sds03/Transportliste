package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stamm.Taxi;

public class DAOTaxi {
	static HibernateUtil hu = new HibernateUtil();

	public static <E> List<E> getAllDataElements(String myClass ) {
		// TODO Auto-generated method stub
		myClass ="from " + myClass;
		
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query queryResult = session.createQuery(myClass);;
		List<E> allElements = queryResult.list();
		tx.commit();
		session.close();

		return allElements;
	}

	public static void SaveTaxi(Taxi taxi) {
		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(taxi);
		tx.commit();
		session.close();
	}

	public static int getTaxiFromName(Taxi taxi) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static List<Taxi> getTaxiFromNr(int i) {
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query queryResult = session.createQuery("from Taxi Where id='"+i+"'");
		List<Taxi> result = (List<Taxi>) queryResult.list();
		tx.commit();
		session.close();

		return result;
	}

}
