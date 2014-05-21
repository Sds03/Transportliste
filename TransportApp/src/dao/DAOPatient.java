package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stamm.Insurance;
import stamm.Patient;

public class DAOPatient {
	static HibernateUtil hu = new HibernateUtil();

	public static List<Patient> getAllPatients() {

		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery("select * from FAHRDIENST.PATIENT").addEntity(
						Patient.class);
		List result = query.list();
		tx.commit();
		session.close();
		return result;
	}

	public static Patient getPatientById(int id) {

		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery("select * from FAHRDIENST.PATIENT WHERE id="+id).addEntity(
						Patient.class);
		List result = query.list();
		tx.commit();
		session.close();
		return (Patient) result;
	}

	public static void saveData(Patient p) {
		// TODO Auto-generated method stub

		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(p);
		tx.commit();
		session.close();
	}

	public static List<Patient> getSearchPatients(String s) {

		// TODO Auto-generated method stub
		List result=null;
		if (s.length() > 2) {
			Session session = hu.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createSQLQuery(
					"select * from FAHRDIENST.PATIENT Where LName LIKE " + "'%" + s + "%'")
					.addEntity(Patient.class);
			result = query.list();
			tx.commit();
			session.close();
		}
		return result;
	}

}
