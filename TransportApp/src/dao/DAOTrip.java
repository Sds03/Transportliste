package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Array;

import stamm.Patient;
import stamm.Tour;
import stamm.Trip;

public class DAOTrip {
	static HibernateUtil hu = new HibernateUtil();

	public Trip getTripSQL(int id) {
		// TODO Auto-generated method stub

		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select * from PERSOAPP.PERSO where PERSON_ID =" + id)
				.addEntity(Trip.class);
		List result = query.list();

		tx.commit();
		session.close();

		return (Trip) result.get(0);
	}

	public static Trip getTripById(Integer id) {
		// TODO Auto-generated method stub
		Trip sd = new Trip(null);

		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		sd = (Trip) session.get(Trip.class, id);
		tx.commit();
		session.close();

		return sd;
	}

	public static List<Trip> getTripByMonth(int month) {
		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery(
						"select * from FAHRDIENST.APR JOIN Patient On(TRIP.PATNR=PATIENT.PATNR)")
				.addEntity(Trip.class);
		List result = query.list();

		tx.commit();
		session.close();

		return result;
	}

	public static Object[][] getTripsbyDay(Date date) {

		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = session
				// .createSQLQuery("select OPDatum, PATIENT.ID, PATIENT.PATNR, LName, FName, Street, City, PATIENT.Telefon from FAHRDIENST.TRIP JOIN  FAHRDIENST.Patient On TRIP.PATNR=PATIENT.PATNR ")
				.createSQLQuery(
						"select OPDatum, PATIENT.ID, PATIENT.PATNR, LName, FName, Street, City, PATIENT.Zipcode,PATIENT.Insurance, PATIENT.Telefon from FAHRDIENST.TRIP JOIN  FAHRDIENST.Patient On TRIP.PATNR=PATIENT.PATNR WHERE opDatum='"+ date + "'")
				.addEntity(Patient.class);
		List<Patient> result = (List<Patient>) query.list();
		Object[][] resultArray = new Object[result.size()][8];
		for (int i = 0; i < result.size(); i++) {
			resultArray[i][0] = sf.format(date);
			resultArray[i][2] = result.get(i).getPatNum();
			resultArray[i][3] = result.get(i).getLastName();
			resultArray[i][4] = result.get(i).getFirstName();
			resultArray[i][5] = result.get(i).getStrasse();
			resultArray[i][6] = result.get(i).getOrt();
			resultArray[i][7] = result.get(i).getTelefon();
		}
		tx.commit();
		session.close();
		return resultArray;
	}

	public static List<Trip> getTripsbyTourId(int tournr) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = session
				//.createSQLQuery("select OPDatum, PATIENT.ID, PATIENT.PATNR, LName, FName, Street, City, PATIENT.Telefon from FAHRDIENST.TRIP JOIN  FAHRDIENST.Patient On TRIP.PATNR=PATIENT.PATNR ")
				.createSQLQuery(
						"select * from fahrdienst.trip WHERE tour='"+tournr+"'")
						.addEntity(Trip.class);
		List<Trip> result = (List<Trip>) query.list();
		return result;
		
		
		
	}

	public static void addTriptoTour(Patient patient, Tour tour) {
		// 
		Trip trip = new Trip(patient);		
		trip.setTourNr(tour.getTourNr());
		
		Session session = hu.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(trip);
		tx.commit();
		session.close();
		
	}
}
