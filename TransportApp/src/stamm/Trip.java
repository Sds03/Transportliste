package stamm;

import java.util.Date;

public class Trip {
	private int id;
	private Date pickUpTIme;
	private int authorization;
	private boolean deductible;
	private float deductAmount;
	private Patient p;
	private int doctorNr;
	private float distance;
	private String startCity;
	private String endCity;
	private int tripType;
	private int insuranceCo;
	private int tourNr;
	private Date opdate;
	
	public Trip(){
		
	}
	
	public Trip(Patient pat){
		this.p = pat;
	}
	
	public Date getOpdate() {
		return opdate;
	}
	public void setOpdate(Date opdate) {
		this.opdate = opdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getPickUpTIme() {
		return pickUpTIme;
	}

	public void setPickUpTIme(Date pickUpTIme) {
		this.pickUpTIme = pickUpTIme;
	}

	public int getAuthorization() {
		return authorization;
	}
	public void setAuthorization(int authorization) {
		this.authorization = authorization;
	}
	public boolean isDeductible() {
		return deductible;
	}
	public void setDeductible(boolean deductible) {
		this.deductible = deductible;
	}
	public float getDeductAmount() {
		return deductAmount;
	}
	public void setDeductAmount(float deductAmount) {
		this.deductAmount = deductAmount;
	}
	
	public Patient getP() {
		return p;
	}

	public void setP(Patient p) {
		this.p = p;
	}

	public int getDoctorNr() {
		return doctorNr;
	}
	public void setDoctorNr(int doctorNr) {
		this.doctorNr = doctorNr;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public int getTripType() {
		return tripType;
	}
	public void setTripType(int tripType) {
		this.tripType = tripType;
	}
	public int getInsuranceCo() {
		return insuranceCo;
	}
	public void setInsuranceCo(int insuranceCo) {
		this.insuranceCo = insuranceCo;
	}
	public int getTourNr() {
		return tourNr;
	}
	public void setTourNr(int tourNr) {
		this.tourNr = tourNr;
	}

}
