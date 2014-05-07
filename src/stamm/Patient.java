package stamm;

import java.util.Date;

public class Patient  {

	private int id;
	private String lastName;
	private String firstName;
	private Date gebDat;
	private String patNum;
	private String strasse;
	private String ort;
	private String plz;
	private int km;
	private int insurance;
	private String telefon;
	
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public int getInsurance() {
		return insurance;
	}
	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getGebDat() {
		return gebDat;
	}
	public void setGebDat(Date gebDat) {
		this.gebDat = gebDat;
	}
	public String getPatNum() {
		return patNum;
	}
	public void setPatNum(String patNum) {
		this.patNum = patNum;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	
}
