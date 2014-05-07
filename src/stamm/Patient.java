package stamm;

public class Patient extends Person {

private int patNum;
private int insurance;
private boolean insRequired;
public int getPatNum() {
	return patNum;
}
public void setPatNum(int patNum) {
	this.patNum = patNum;
}
public int getInsurance() {
	return insurance;
}
public void setInsurance(int insurance) {
	this.insurance = insurance;
}
public boolean isInsRequired() {
	return insRequired;
}
public void setInsRequired(boolean insRequired) {
	this.insRequired = insRequired;
}


}
