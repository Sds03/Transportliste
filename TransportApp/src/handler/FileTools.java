package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DAOFile;

import stamm.Parameter;
import stamm.Patient;
import util.GuiUtilities;

public class FileTools {
	Parameter parameter;
	String importPath;
	File importFile;
	List<Parameter> par;
	

	@SuppressWarnings("deprecation")
	public Patient openPatientFile() throws IOException {
		// par = DAOFile.OpenFile(GuiUtilities.IMPORT_PATIENT_PATH);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		BufferedReader reader;
		Patient patient = new Patient();

		try {
			reader = new BufferedReader(new FileReader(
					GuiUtilities.IMPORT_PATIENT_PATH));
			if (reader != null) {
				String line = null;
				int count = 1;
				while ((line = reader.readLine()) != null) {
					// process each line in some way
					switch (count++) {
					case 1:
						patient.setLastName(line);
						break;
					case 2:
						patient.setFirstName(line);
						break;
					case 3:
						patient.setStrasse(line);
						break;
					case 4:
						patient.setPlz(line);
						break;
					case 5:
						patient.setOrt(line);
						break;
					case 7:
						patient.setPatNum(line);
						break;
					case 8:{
						Date convertedDate = null;
						try {convertedDate = sf.parse(line);}
						catch(Exception e)	{}
						finally {
						}
					}
					} //switch

				} //while
				
			} else
				throw new IOException();

		} catch (IOException e) {

		} finally {}
		
		return patient;
		

	}

}
