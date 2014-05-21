package caltools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javassist.tools.framedump;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MyCalendar extends JPanel implements BCalendar, ActionListener {

	// Class vars
	public CalendarType cType = BCalendar.CalendarType.MULTI_TIMESHEET;
	public CalendarMode cMode = BCalendar.CalendarMode.WEEK;
	int[] MONTHS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	java.util.Calendar dt = Calendar.getInstance();
	int kw = dt.get(java.util.Calendar.WEEK_OF_YEAR);
	int currentMonth = Calendar.getInstance().MONTH;
	int[][] rows = new int[1][31];
	int[][] singleDayCal = new int[6][7];
	String[] monthsText = { "January", "February", "März", "April", "Mai",
			"Juni", "Juli", "August", "September", "October", "November",
			"December" };
	String[] titles = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday" };

	DefaultTableModel model = new DefaultTableModel(0, 0);
	private boolean initialized = false;
	JMenuItem mItem = new JMenuItem("Driver Timesheet");
	JMenuItem mItemCalSingle = new JMenuItem("");
	JFrame frame = new JFrame("Taxi Transport");
	Container contentPane = frame.getContentPane();
	JMenu menu = new JMenu("Drivers ");
	JButton btn1 = new JButton();

	JMenuBar outerBar = new JMenuBar();
	JMenuBar innerBarIcons = new JMenuBar();
	JMenuBar innerBar = new JMenuBar();
	JMenu menuIcon1 = new JMenu("");
	JMenu menuIcon2 = new JMenu("");
	JMenu menucalday = new JMenu("");
	String[] drivers = { "Peter", "Wolfgang", "Anneliies", "Margret",
			"Hans-Rudolf", "Theo" };
	String selectedMenu = new String("");

	// instaniate MyCalendar as instance to work with
	//
	// MyCalendar cal = new MyCalendar();

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	// window components
	JScrollPane pane = new JScrollPane();
	JPanel panelTop = new JPanel(new GridLayout(1, 3));
	JPanel panelTopMonth = new JPanel(new BorderLayout());
	JTable table = new JTable(0, 0);
	JButton btnBack = new JButton("BACK");
	JLabel monthLbl = new JLabel("                    " + monthsText[dt.MONTH]
			+ "  " + dt.get(Calendar.YEAR));
	JButton btnForward = new JButton("FORWARD");
	public JPanel panel = new JPanel(new BorderLayout());

	// class constructor
	public MyCalendar() {

		// the main calendar in panel CENTER#
		this.panel.add(new JScrollPane(table), BorderLayout.CENTER);

		// Buttons for changing date
		panelTop.add(btnBack);
		panelTop.add(panelTopMonth);
		panelTopMonth.add(monthLbl, BorderLayout.CENTER);
		panelTop.add(btnForward);
		btnBack.addActionListener(this);
		btnForward.addActionListener(this);
	}

	// getters and setters
	public String[] getDrivers() {
		return drivers;
	}

	public void setDrivers(String[] drivers) {
		this.drivers = drivers;
	}

	@Override
	public void setType(CalendarType ctype) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMode(CalendarMode cmode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeCalendar() {
		// TODO Auto-generated method stub
		
	}

	private void fillSingleMonthModel() {
		Calendar clonedt = (Calendar) dt.clone();
		clonedt.set(Calendar.DATE, 1);
		Object[] singleDayRow = { 0, 0, 0, 0, 0, 0, 0 };
		int firstDayofMonth = clonedt.get(Calendar.DAY_OF_WEEK);
		this.rows = currentMonthDays();
		boolean start = false;
		int k = 0, j = 0;
		int row = 0;
		for (int i = 0; i < titles.length; i++) {
			model.addColumn(titles[i] + "  " + this.rows[0][i]);
		}
		for (int i = 1; i < 42; i++) {
			if (!start && i == firstDayofMonth)
				start = true;

			if ((start) && (k < this.MONTHS[dt.get(Calendar.MONTH)]))
				this.singleDayCal[row][j] = ++k;
			else
				this.singleDayCal[row][j] = k;
			if (i % 7 == 0) {
				row++;
			}
			j = ++j % 7;
		} // for

		for (int cnt = 0; cnt < 6; cnt++) {
			for (int cnt2 = 0; cnt2 < 7; cnt2++) {
				singleDayRow[cnt2] = singleDayCal[cnt][cnt2];
			}
			this.model.addRow(singleDayRow);
		}
	}

	// show calendar in the various modes
	public void showCalendar(CalendarType cType, CalendarMode cMode) {

		// Initialisierung
		this.pane.getViewport().removeAll();
		this.panel.removeAll();
		this.cType = cType;
		this.cMode = cMode;

		// always start with new values for browsing the months
		model.setColumnCount(0);
		model.setRowCount(0);

		// show all drivers in a weekly time frame
		if ((drivers.length > 0)
				&& (cType != BCalendar.CalendarType.SINGLE_TIMESHEET))
			model.addColumn("drivername", this.drivers);
		   
		if ((cMode == BCalendar.CalendarMode.WEEK)
				&& (cType == BCalendar.CalendarType.MULTI_TIMESHEET)) {

			// add driver names
			this.rows = currentWeekdays();
			if (this.cMode == CalendarMode.WEEK)
				for (int i = 0; i < titles.length; i++) {
					//model.addColumn( titles[i] + "  " + this.rows[0][i]);
					model.addColumn(titles[i]);
				}
				
		} else if ((cMode == BCalendar.CalendarMode.MONTH)
				&& (cType == BCalendar.CalendarType.MULTI_TIMESHEET)) {
			this.rows = currentMonthDays();
			for (int i = 0; i < MONTHS[dt.get(Calendar.MONTH)]; i++) {
				model.addColumn(this.rows[0][i]);
			}
		} else if ((cMode == BCalendar.CalendarMode.MONTH)
				&& (cType == BCalendar.CalendarType.SINGLE_TIMESHEET)) {
			fillSingleMonthModel();
		}

		// initialize JTable
		table.setModel(model);
		if (cType == CalendarType.MULTI_TIMESHEET)
			table.setRowHeight(29);
		else
			table.setRowHeight(67);
		int size = model.getColumnCount();
		model.fireTableDataChanged();
		this.pane.getViewport().add(table);
		this.panel.add(pane);
		panel.add(table, BorderLayout.CENTER);
		panel.add(panelTop, BorderLayout.NORTH);
		panel.revalidate();
		this.pane.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		// fires bei BtnClick BACK
		if (ae.getSource() == btnBack) {

			if (this.cMode == CalendarMode.WEEK) {
				dt.add(Calendar.DATE, -7); // here the code for minusDate()
			} else if (this.cMode == CalendarMode.MONTH) {
				if (dt.get(Calendar.MONTH) != 0)
					dt.set(Calendar.MONTH, dt.get(Calendar.MONTH) - 1);
				else {
					dt.set(Calendar.MONTH, 11);
					dt.set(Calendar.YEAR, dt.get(Calendar.YEAR) - 1);
				}
			}
		}

		// BtnClick Forward
		else if (this.cMode == CalendarMode.WEEK) {
			dt.add(Calendar.DATE, 7); // here the code for plusDate()
		} else if (this.cMode == CalendarMode.MONTH) {
			if (dt.get(Calendar.MONTH) != 11)
				dt.set(Calendar.MONTH, dt.get(Calendar.MONTH) + 1);
			else {
				dt.set(Calendar.MONTH, 0);
				dt.set(Calendar.YEAR, dt.get(Calendar.YEAR) + 1);
			}
		}
		refreshCalendar();
	}

	public void refreshCalendar() {

		// clear Model
		// delete old info from model
		if (cType == CalendarType.MULTI_TIMESHEET)
			model.setColumnCount(1);
		else
			model.setColumnCount(0);
		if (this.cMode == BCalendar.CalendarMode.WEEK) {
			this.rows = currentWeekdays();
			for (int i = 0; i < titles.length; i++) {
				model.addColumn(titles[i] + "  " + this.rows[0][i]);
			}
			// Mode == Month
		} else {
			if (this.cType == CalendarType.MULTI_TIMESHEET) {
				// here the code for Type== Month
				this.rows = currentMonthDays();
				for (int i = 0; i < 31; i++) {
					model.addColumn(this.rows[0][i]);
				}
			} // Type == Singlesheet
			else {
				rows = currentMonthDays();
				model.setNumRows(0);
				fillSingleMonthModel();
				table.setModel(model);
			}
		}
		monthLbl.setText("                    "
				+ monthsText[dt.get(Calendar.MONTH)] + "  "
				+ dt.get(Calendar.YEAR));
		model.fireTableDataChanged();

		this.pane.repaint();
	}

	// header dates
	public static Vector createHeaderDates(int[][] rows, int size) {
		Vector vector = new Vector(size);
		for (int i = 0; i < size - 1; i++)
			vector.add(String.valueOf(rows[0][i]));

		return vector;
	}

	// data vector
	public static Vector createDataVector(String prefix, int size) {
		Vector vector = new Vector(size);
		for (int i = 0; i < size; i++)
			vector.add(prefix + " : " + size + " : " + i);

		return vector;
	}

	// finds the numeric days of the month i.e. the week that is being viewed
	// returns an 2DArray
	private int[][] currentWeekdays() {
		int workArr[][] = { { 0, 1, 2, 3, 4, 5, 6, 7 } };
		Calendar tmp = Calendar.getInstance();
		if (dt.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			int dow = dt.get(Calendar.DAY_OF_WEEK);
			dt.add(Calendar.DAY_OF_MONTH, 1 - dow);
		}
		tmp = (Calendar) dt.clone();
		for (int i = 0; i < 7; i++) {
			if ((tmp.get(tmp.DAY_OF_MONTH) <= MONTHS[tmp.get(Calendar.MONTH)])
					&& tmp.get(tmp.DAY_OF_MONTH) > 0) {
				workArr[0][i] = tmp.get(Calendar.DAY_OF_MONTH);
				{
				}
				tmp.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return workArr;
	}

	private int[][] currentMonthDays() {
		int workArr[][] = new int[1][31];
		for (int i = 1; i < MONTHS[dt.get(Calendar.MONTH)]; i++)
			workArr[0][i] = i;
		return workArr;
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return initialized;
	}

	@Override
	public boolean isLeapYear(Date d) {
		// TODO Auto-generated method stub
		return false;
	}

}
