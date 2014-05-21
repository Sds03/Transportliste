package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stamm.Parameter;

public class DAOFile {
static HibernateUtil hu = new HibernateUtil();
	public static List<Parameter> OpenFile(String importPatientPath) {
		// TODO Auto-generated method stub
			
		Session session = hu.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query queryResult = session.createSQLQuery("select * from parameter");;
			List<Parameter> allElements = queryResult.list();
			tx.commit();
			session.close();
			
			return allElements;
		
	}
}

