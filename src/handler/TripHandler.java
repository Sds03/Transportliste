package handler;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import stamm.Driver;
import stamm.Insurance;
import stamm.Parameter;
import stamm.Patient;
import stamm.Taxi;
import stamm.Tour;
import stamm.Trip;
import templates.TTable;
import util.ButtonColumn;
import util.GuiUtilities;
import dao.DAODriver;
import dao.DAOInsurance;
import dao.DAOPatient;
import dao.DAOTaxi;
import dao.DAOTour;
import dao.DAOTrip;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 * Description: TripHandler is the view handler for the trip planning of the
 * vehicle pool. It is the control switchboard for adding tours, trips and
 * administering them. Classes used: Patient, Trip, Tour, Taxi, Driver
 * 
 * @author Scott Stephens
 * @version 1.0
 */
public class TripHandler extends JPanel implements ActionListener, KeyListener,
		PropertyChangeListener, ListSelectionListener {
	//
	// invoked fields that are needed to be manipulated within the class
	// Data types
	private Patient patient = new Patient();
	private Trip trip = new Trip(null);
	private Tour tour = new Tour();
	private Taxi taxi = new Taxi();
	private Date date = new Date();
	private Driver driver = new Driver();

	// panels
	public JPanel panel = new JPanel(new BorderLayout());
	private JPanel pnlPatients = new JPanel();
	private JPanel pnlTour = new JPanel();
	private JPanel dataMatrix = new JPanel();

	// Text fields
	private JTextField tfPatientNr;
	private JTextField tfPatientFName;
	private JTextField tfPatientLName;
	private JTextField tfPatientStreet;
	private JTextField tfPatientCity;
	private JTextField tfPatientZIP;
	private JTextField tfPatientFon;
	private JTextField tfStartCity;
	private JTextField tfEndCity;
	private JTextField tfPatientSearch = new JTextField();

	// buttons
	private JButton btnSaveGuest = new JButton("Save");
	private JButton btnLoadGuest = new JButton("Load");
	private JButton btnPatToTrip = new JButton("Add to Trip");
	private JButton btnPatSearch = new JButton();
	private JButton btnPatRollRight = new JButton(">>");
	private JButton btnPatRollLeft = new JButton("<<");
	private JButton btnAddTour = new JButton("Add Tour");

	// labels
	private JLabel lblTitle = new JLabel("                Trip  Management");
	private JLabel lblTourTitle = new JLabel("              Tours");
	private JLabel lblPatinQueue = new JLabel("0/0");

	// Dropdown boxes
	private JComboBox cbInsurance;

	// data lists for components
	private List<Patient> patList = new ArrayList();
	private List tripList = new ArrayList();
	private List<Insurance> iList = new ArrayList<Insurance>();
	private List<Taxi> taxiList = new ArrayList<Taxi>();
	private List<Tour> tourList = new ArrayList<Tour>();
	private List<Driver> driverList = new ArrayList<Driver>();
	private Object[] oDriver = new Object[20];
	private Object[][] tourtabledata;
	private Object[][] triptabledata;
	private Object[] taxiNames;

	// calendar and supporting elements
	private JCalendar jcalendar = new JCalendar();
	private JDateChooser jcal = new JDateChooser();
	private JPopupMenu popUpGebDat = new JPopupMenu();

	// scroll panes for tables
	private JScrollPane paneTrip;
	private JScrollPane paneTour;

	// data models
	private DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel patmodel = new DefaultTableModel();

	private String[] tourtitles = { "Taxi Nr.", "Driver", "Pickup Area",
			"Nr Guests", "" };
	private String[] triptitles = { "Name", "Street", "City", "", "Time" };
	private DefaultTableModel tourModel;
	private DefaultTableModel tripModel;
	// tables
	private JTable tripTable = new JTable(triptabledata, triptitles);
	private TTable tourTable = new TTable(tourtabledata, tourtitles);

	// Popup Pane
	JOptionPane optPane = new JOptionPane();

	// tables
	private JTable table;
	private int posPatQueue = 1;

	// constructor TripHandler
	public TripHandler() {
		super();

		// fill iList (Insurances)
		iList = DAOInsurance.getInsuranceList();

		// fill patientsTable, Insurance table, Taxi ComboBox
		loadInsuranceList();
		loadTrips();
		loadTaxis();
		loadTours();
		loadDrivers();

		// gets patient file if available from the interface
		FileTools fileio = new FileTools();
		try {
			patient = fileio.openPatientFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tourTable.getSelectionModel().addListSelectionListener(this);
		showGUI();
	}

	// listeners
	// key listener used for setting focus on patient search button
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == '\n') {
			btnPatSearch.requestFocusInWindow();
			btnPatSearch.doClick();
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// action listener
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnSaveGuest) {
			savePatientData();
		} else if (e.getSource() == this.btnPatToTrip) {
			JOptionPane msg = new JOptionPane();

			if ((tourList.size() != 0) || (patient.getId() > 0)
					|| (tourList.size() != 0))
				addTrip();
			else
				msg.showMessageDialog(this,
						"a patient must be loaded / Tour must be selected first ...Please try again");

		} else if (e.getSource() == this.btnLoadGuest) {
			try {
				this.patient = new FileTools().openPatientFile();
				loadPatient(this.patient);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				// here code for an error message
			}
		} else if (e.getSource() == this.btnPatSearch) {
			posPatQueue = 1;
			String s = tfPatientSearch.getText();
			patList = DAOPatient.getSearchPatients(s);
			if (patList.size() > 0)
				loadPatient(patList.get(posPatQueue - 1));
			dataMatrix.revalidate();
		} else if (e.getSource() == btnAddTour) {
			showDialogBoxDriver();
		} else if (e.getSource() == btnPatRollRight) {
			if (posPatQueue < patList.size())
				posPatQueue++;
			loadPatient(patList.get(posPatQueue - 1));
			dataMatrix.revalidate();
		} else if (e.getSource() == btnPatRollLeft) {
			if (posPatQueue > 1)
				posPatQueue--;
			loadPatient(patList.get(posPatQueue - 1));
			dataMatrix.revalidate();
		}
	}

	private void savePatientData() {
		if (patient.getId() > 0) {
			patient.setFirstName(tfPatientFName.getText());
			patient.setLastName(tfPatientLName.getText());
			patient.setStrasse(tfPatientStreet.getText());
			patient.setOrt(tfPatientCity.getText());
			patient.setPlz(tfPatientZIP.getText());
			patient.setInsurance(cbInsurance.getSelectedIndex() + 1);
			patient.setTelefon(tfPatientFon.getText());

			if (savePatientIsPlausible()) {
				DAOPatient.saveData(this.patient);
				clearPatientFields();
			}
		}
	}

	// listener for choosing tour and show the patients (trips)
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//
		  try {
			this.tour = tourList.get(tourTable.getSelectedRow());
			tripList = DAOTrip.getTripsbyTourId(tour.getTourNr());
			if (tripList.size() > 0) {
				loadTrips();
				tripModel.fireTableDataChanged();
				pnlTour.revalidate();
				pnlTour.repaint();
			}
		  }
		  catch (Exception ex) {}
		}

	// listener for birthday text field
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == jcal) {
			patient.setGebDat(jcal.getDate());
		} else if (e.getSource() == cbInsurance) {
			this.patient.setInsurance(cbInsurance.getSelectedIndex());
		} else if (e.getSource() == jcalendar) {
			date = jcalendar.getDate();
			if (loadTours())
				loadTrips();
			panel.revalidate();
			panel.repaint();
		}
	}

	public void propertyChange(PropertyChangeEvent e) {

		if (e.getSource() == jcalendar) {

			date = jcalendar.getDate();
			loadTours();
			tripModel.fireTableDataChanged();
			tourModel.fireTableDataChanged();
		}
	}

	// function for clearing patient text fields
	private void clearPatientFields() {
		// TODO Auto-generated method stub
		tfPatientLName.setText("");
		tfPatientFName.setText("");
		tfPatientStreet.setText("");
		tfPatientCity.setText("");
		tfPatientZIP.setText("");
		tfPatientNr.setText("");
	}

	// function for checking if the patient data is plausible
	// noch nicht fertig
	private boolean savePatientIsPlausible() {

		//
		if ((tfPatientLName.getText() != "")
				&& (tfPatientFName.getText() != ""))
			return true;
		else
			return false;
	}

	public void loadTaxis(){
		
	taxiList = DAOTaxi.getAllDataElements("Taxi");	
	for (int i=0; i<taxiList.size();i++) taxiNames[i] = taxiList.get(i).getName();
	}
	
	public boolean loadTours() {
		boolean result = false;
		tourList = DAOTour.getTour(date);
		tourtabledata = null;
		if (tourList.size() > 0) {
			tourtabledata = new Object[tourList.size()][5];
			for (int i = 0; i < tourList.size(); i++) {
				tourtabledata[i][0] = DAOTaxi.getTaxiFromNr(tourList.get(i).getTaxiNr()).get(0).getName();
				tourtabledata[i][1] = DAODriver.getDriver(
						tourList.get(i).getDriver()).getLastname();
				tourtabledata[i][2] = tourList.get(i).getTourArea();
				tourtabledata[i][3] = tourList.get(i).getNrGuests();
			}
			result = true;
		}
		this.tourModel = new DefaultTableModel(tourtabledata, tourtitles);
		tourTable.setModel(tourModel);
		tourModel.fireTableDataChanged();
		return result;
	}

	private void loadPatient(Patient patient) {
		// TODO Auto-generated method stub
		this.patient = patient;
		tfPatientLName.setText(patient.getLastName());
		tfPatientFName.setText(patient.getFirstName());
		tfPatientStreet.setText(patient.getStrasse());
		tfPatientCity.setText(patient.getOrt());
		tfPatientZIP.setText(patient.getPlz());
		tfPatientNr.setText(String.valueOf(patient.getPatNum()));
		tfPatientFon.setText(String.valueOf(patient.getTelefon()));
		lblPatinQueue.setText(String.valueOf(posPatQueue) + "/"
				+ String.valueOf(patList.size()));
		cbInsurance.setSelectedIndex(patient.getInsurance());
	}

	private void loadInsuranceList() {
		List<String> cbInsuranceList = new ArrayList<String>();

		for (Insurance i : iList) {
			cbInsuranceList.add(i.getName());
		}
		cbInsurance = new JComboBox(cbInsuranceList.toArray());
	}

	private void loadDrivers() {

		driverList = DAODriver.getDriverist();
		for (int i = 0; i < driverList.size(); i++)
			oDriver[i] = driverList.get(i).getLastname();
	}

	private List<Patient> loadAllPatients() {
		return DAOPatient.getAllPatients();
	}

	public List<Trip> getTrip(int id) {

		return DAOTrip.getTripByMonth(id);
	}

	public void addTrip() {

		if ((tour.getNrGuests() > tripList.size())
				&& (!patient.getLastName().isEmpty()) && (tour.getTourNr() > 0)
				&& (patient.getId() > 0))
			DAOTrip.addTriptoTour(patient, tour);
		loadTrips();

	}

	public void addTour(Driver driver, String area, Object taxi) {
		List<Tour> tourlst = DAOTour.getTour(date);
		DAOTour.addTour(((Taxi) taxi).getId(), driver.getId(), date, tourlst.size(),
				area);
		loadTours();
	}

	// if a date that a patient is supposed be assigned a trip and tour that
	// haven't been planned yet, this dialog box enables the user
	// to select a driver and assign the driver to chosen taxi.
	private void showDialogBoxDriver() {
		final JDialog dialog = new JDialog();
		final JComboBox cbArea = new JComboBox(GuiUtilities.AREAS);
		final JComboBox cbDriver = new JComboBox(oDriver);
		final JComboBox cbTaxi = new JComboBox(taxiNames);
		Action hide = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// here insert plausibility check and insert tour function
				addTour(driverList.get(cbDriver.getSelectedIndex()),
						(String) cbArea.getSelectedItem(),cbTaxi.getSelectedItem());
				dialog.setVisible(false);
			}
		};

		Action abort = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dialog.setVisible(false);

			}
		};

		dialog.setBounds(100, 100, 300, 200);
		dialog.getContentPane().setLayout(null);
		cbDriver.setBounds(10, 10, 140, 24);
		cbArea.setBounds(10, 38, 140, 24);
		JButton btnClose = new JButton("Add");
		JButton btnAbort = new JButton("Cancel");
		btnClose.setBounds(40, 135, 70, 24);
		btnClose.addActionListener(hide);
		btnAbort.setBounds(140, 135, 70, 24);
		btnAbort.addActionListener(abort);
		dialog.getContentPane().add(btnClose);
		dialog.getContentPane().add(btnAbort);
		dialog.getContentPane().add(cbDriver);
		dialog.getContentPane().add(cbArea);
		dialog.setVisible(true);
	}

	// responsible for filling the trip table

	private void loadTrips() {

		// get tours for specific date and taxi
		List<Trip> trip = DAOTrip.getTripsbyTourId(tour.getTourNr());
		// triptabledata = null;
		if (trip.size() > 0) {
			triptabledata = new Object[trip.size()][5];
			for (int i = 0; i < trip.size(); i++) {
				triptabledata[i][4] = trip.get(i).getPickUpTIme();
				triptabledata[i][0] = trip.get(i).getP().getLastName();
				triptabledata[i][1] = trip.get(i).getP().getStrasse();
				triptabledata[i][2] = trip.get(i).getP().getOrt();
			}

			this.tripModel = new DefaultTableModel(triptabledata, triptitles);
			tripTable.setModel(tripModel);
			tripModel.fireTableDataChanged();
		}
	}

	// main GUI definitions
	public void showGUI() {

		// add listeners / Actions
		btnSaveGuest.addActionListener(this);
		btnLoadGuest.addActionListener(this);
		btnPatToTrip.addActionListener(this);
		btnPatSearch.addActionListener(this);
		btnAddTour.addActionListener(this);
		btnPatRollLeft.addActionListener(this);
		jcalendar.addPropertyChangeListener(this);
		tfPatientSearch.addKeyListener(this);

		// setup Panel dimensions and initialize
		// panel is a wrapper. the others will be added to it.
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int screenWidth = dimension.width;
		int screenHeight = dimension.height;
		panel.setSize(new Dimension(dimension));
		panel.setLayout(new BorderLayout());

		// set up for trip table east panel
		pnlTour.setPreferredSize(new Dimension(600, 240));
		pnlTour.setBackground(Color.white);
		tripModel = new DefaultTableModel(triptabledata, triptitles);
		tripTable = new JTable(tripModel);
		paneTrip = new JScrollPane(tripTable);
		pnlTour.setPreferredSize(new Dimension(650, 300));
		pnlTour.setLayout(null);
		paneTrip.setOpaque(true);
		paneTrip.setBounds(10, 8, 650, 250);
		pnlTour.add(paneTrip);

		// define panel content
		// initialize view UI Components
		cbInsurance.setBounds(336, 65, 70, 18);

		dataMatrix.setBorder(new CompoundBorder(null, new CompoundBorder(
				new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(
						new Color(0, 0, 0), 1, true))));
		dataMatrix.setPreferredSize(new Dimension(1600, 292));
		dataMatrix.setBackground(Color.LIGHT_GRAY);
		panel.setAlignmentX(LEFT_ALIGNMENT);

		tfPatientNr = new JTextField("");
		tfPatientNr.setBounds(85, 37, 57, 18);
		tfPatientNr.setEditable(false);
		tfPatientNr.setPreferredSize(new Dimension(57, 19));
		tfPatientLName = new JTextField("");
		tfPatientLName.setBounds(85, 58, 170, 20);
		tfPatientFName = new JTextField("");
		tfPatientFName.setBounds(85, 83, 180, 20);
		tfPatientStreet = new JTextField("");
		tfPatientStreet.setBounds(85, 108, 180, 20);
		tfPatientCity = new JTextField("");
		tfPatientCity.setBounds(85, 133, 180, 20);
		tfPatientZIP = new JTextField("");
		tfPatientZIP.setBounds(85, 158, 87, 20);
		tfPatientFon = new JTextField("");
		tfPatientFon.setBounds(85, 208, 180, 20);

		// control buttons
		btnSaveGuest.setBounds(115, 258, 77, 23);
		btnLoadGuest.setBounds(202, 258, 97, 23);
		btnPatToTrip.setSize(85, 23);

		btnPatToTrip.setLocation(20, 257);
		btnPatSearch.setBounds(245, 14, 25, 14);
		btnPatRollRight.setSize(50, 22);
		btnPatRollRight.setLocation(235, 232);
		btnPatRollRight.addActionListener(this);
		btnPatRollLeft.setSize(50, 22);
		btnPatRollLeft.setLocation(85, 232);

		// add icons to buttons
		btnPatSearch.setIcon(new ImageIcon(
				"f:/workspace/transportliste/img/search-icon.png"));

		btnAddTour.setBounds(304, 258, 97, 23);
		jcalendar.setBounds(436, 28, 200, 200);

		// set panel = FlowLayout
		dataMatrix.setLayout(null);

		// set specific location and dimensions for components
		// 1
		JLabel label = new JLabel("PatientNr");
		label.setBounds(20, 43, 85, 14);
		dataMatrix.add(label);
		dataMatrix.add(tfPatientNr);
		JLabel label_1 = new JLabel("");
		label_1.setBounds(242, 83, 0, 0);
		dataMatrix.add(label_1);
		// 2
		JLabel label_2 = new JLabel("Name");
		label_2.setBounds(20, 68, 85, 14);
		dataMatrix.add(label_2);
		dataMatrix.add(tfPatientLName);
		JLabel label_3 = new JLabel("");
		label_3.setBounds(20, 83, 0, 0);
		dataMatrix.add(label_3);
		// 3
		JLabel label_4 = new JLabel("Birthday");
		label_4.setBounds(20, 189, 85, 14);
		dataMatrix.add(label_4);
		jcal.setComponentPopupMenu(popUpGebDat);
		jcal.setBounds(85, 182, 110, 18);
		dataMatrix.add(jcal);
		// 4
		JLabel label_5 = new JLabel(" First Name");
		label_5.setBounds(20, 93, 85, 14);
		dataMatrix.add(label_5);
		dataMatrix.add(tfPatientFName);
		JLabel label_6 = new JLabel("");
		label_6.setBounds(810, 83, 0, 0);
		dataMatrix.add(label_6);
		// 5
		JLabel label_7 = new JLabel("Street");
		label_7.setBounds(20, 118, 85, 14);
		dataMatrix.add(label_7);
		dataMatrix.add(tfPatientStreet);
		JLabel label_8 = new JLabel("");
		label_8.setBounds(935, 83, 0, 0);
		dataMatrix.add(label_8);
		// 6
		JLabel label_9 = new JLabel("City");
		label_9.setBounds(20, 139, 85, 14);
		dataMatrix.add(label_9);
		dataMatrix.add(tfPatientCity);
		JLabel label_10 = new JLabel("");
		label_10.setBounds(1038, 83, 0, 0);
		dataMatrix.add(label_10);
		// 7
		JLabel label_11 = new JLabel("Zipcode");
		label_11.setBounds(20, 164, 85, 14);
		dataMatrix.add(label_11);
		dataMatrix.add(tfPatientZIP);
		JLabel label_12 = new JLabel("");
		label_12.setBounds(1177, 83, 0, 0);
		dataMatrix.add(label_12);
		// 8
		JLabel label_13 = new JLabel("Telephone Nr");
		label_13.setBounds(10, 214, 85, 14);
		dataMatrix.add(label_13);
		dataMatrix.add(tfPatientFon);
		JLabel label_14 = new JLabel("");
		label_14.setBounds(1348, 83, 0, 0);
		dataMatrix.add(label_14);
		tfPatientSearch.setBounds(85, 14, 150, 18);

		dataMatrix.add(tfPatientSearch);

		// add Tourtable
		tourModel = new DefaultTableModel(tourtabledata, tourtitles);
		// tourTable = new JTable(tourModel);
		JScrollPane pane = new JScrollPane(tourTable);
		pane.setBounds(645, 24, 300, 200);
		dataMatrix.add(pane);

		// add more content to panel NORTH
		dataMatrix.add(btnSaveGuest);
		dataMatrix.add(btnLoadGuest);
		dataMatrix.add(btnPatToTrip);
		dataMatrix.add(btnAddTour);
		dataMatrix.add(btnPatRollRight);
		dataMatrix.add(btnPatRollLeft);
		lblPatinQueue.setSize(80, 22);
		lblPatinQueue.setLocation(180, 232);
		dataMatrix.add(lblPatinQueue);
		dataMatrix.add(cbInsurance);
		dataMatrix.add(jcalendar, BorderLayout.NORTH);
		dataMatrix.add(btnPatSearch);
		panel.add(dataMatrix, BorderLayout.NORTH);
		panel.add(pnlTour, BorderLayout.WEST);
	}

}
