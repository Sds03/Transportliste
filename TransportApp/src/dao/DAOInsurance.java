package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stamm.Insurance;
import stamm.Trip;

public class DAOInsurance {
	static HibernateUtil hu = new HibernateUtil();

	public static List<Insurance> getInsuranceList() {
		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select * from fahrdienst.insurance")
				.addEntity(Insurance.class);
		List result = query.list();

		tx.commit();
		session.close();

		return result;
	}

	public static void saveData(List<Insurance> ilist) {
		// TODO Auto-generated method stub

		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		for (int i = 0; i < ilist.size(); i++)
			session.saveOrUpdate(ilist.get(i));
		tx.commit();
		session.close();
	}
}
