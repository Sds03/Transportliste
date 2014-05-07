package stamm;

import java.util.Date;

public class Taxi {

	private int id;
	private String name;
	private String hersteller;
	private Date nextService;
	private boolean aktiv;
	
	public boolean isAktiv() {
		return aktiv;
	}
	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHersteller() {
		return hersteller;
	}
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}
	public Date getNextService() {
		return nextService;
	}
	public void setNextService(Date nextService) {
		this.nextService = nextService;
	}
	
}
