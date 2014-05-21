package stamm;

import java.util.Date;

public class Tour {

	private int id;
	private int tourNr;
	private int taxiNr;
	public int getTaxiNr() {
		return taxiNr;
	}
	public void setTaxiNr(int taxiNr) {
		this.taxiNr = taxiNr;
	}
	private Date tourDate;
	private int nrGuests;
	private boolean tourConfirmed;
	private int driver;
	private String tourArea;
	
	public String getTourArea() {
		return tourArea;
	}
	public void setTourArea(String tourArea) {
		this.tourArea = tourArea;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTourNr() {
		return tourNr;
	}
	public void setTourNr(int tourNr) {
		this.tourNr = tourNr;
	}
	public Date getTourDate() {
		return tourDate;
	}
	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}
	public int getNrGuests() {
		return nrGuests;
	}
	public void setNrGuests(int nrGuests) {
		this.nrGuests = nrGuests;
	}
	public boolean isTourConfirmed() {
		return tourConfirmed;
	}
	public void setTourConfirmed(boolean tourConfirmed) {
		this.tourConfirmed = tourConfirmed;
	}
	public int getDriver() {
		return driver;
	}
	public void setDriver(int driver) {
		this.driver = driver;
	}
	
	
}
