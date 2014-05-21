package stamm;


public class Parameter {
String patientImportPath;
public static final String[] TRIPHEADER = {"OPDatum","TOUR","PATIENTNR","LASTNAME","FIRSTNAME","STREET","CITY","TELEFONNR"};
public String getPatientImportPath() {
	return patientImportPath;
}

public void setPatientImportPath(String patientImportPath) {
	this.patientImportPath = patientImportPath;
}


}
