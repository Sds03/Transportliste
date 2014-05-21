package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stamm.Driver;
import stamm.Taxi;
import stamm.Tour;
import stamm.Trip;
import util.GuiUtilities;

public class DAOTour {
	static HibernateUtil hu = new HibernateUtil();

	public static List<Tour> getTour(Date date) {
		
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = session.createSQLQuery(
				"select * from fahrdienst.tour where tourDate ='" + sf.format(date) + "'")
				.addEntity(Tour.class);
		
		List result = query.list();

		tx.commit();
		session.close();

		return (List<Tour>) result;
	}

	public static void addTour(int taxi, int chosenDriver, Date date, int nrTours, String area) {
		
		// set Tournr from taxinr + Date strings + additional digit in case of more than one tour
		Calendar calDate= Calendar.getInstance();
		calDate.setTime(date);
		String sdate = String.valueOf(calDate.get(Calendar.DATE)) + String.valueOf(calDate.get(Calendar.MONTH)) +String.valueOf(calDate.get(Calendar.YEAR) + String.valueOf(nrTours));
		
		
		Tour tour = new Tour();
		tour.setDriver(chosenDriver);
		tour.setTourDate(date);
		tour.setTaxiNr(taxi);
		tour.setTourArea(area);
		tour.setNrGuests(GuiUtilities.MAX_GUEST_NR);
		tour.setTourNr(Integer.parseInt(String.valueOf(taxi) + sdate));
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(tour);
		tx.commit();
		session.close();
	}

	public static Tour getTourbyTourNr(Integer i) {
		
		// 
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select * from FAHRDIENST.TOUR where tourNr =" + i )
				.addEntity(Tour.class);
		
		List result = query.list();

		tx.commit();
		session.close();

		return  (Tour) result.get(0);
	}
}
