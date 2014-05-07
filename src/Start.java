import handler.DriverHandler;
import handler.InsuranceHandler;
import handler.TaxiHandler;
import handler.TripHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import caltools.BCalendar;
import caltools.MyCalendar;

/**
 * s Start Class is the class that steers the program as main console and calls
 * all views
 * 
 * Classes: Wagen,Calendar, Trip, Insurance and Driver
 * 
 **/
public class Start extends JPanel implements ActionListener, ChangeListener {

	private JFrame frame = new JFrame("Taxi Transport");
	private Container contentPane = frame.getContentPane();
	
	private JMenu menu = new JMenu("Drivers ");
	private JMenu menudtable = new JMenu("Data Tables");
	
	private JMenuItem mItem = new JMenuItem("Driver Timesheet");
	private JMenuItem mItemdt = new JMenuItem("Insurance");
	private JMenuItem mItemdrv = new JMenuItem("Driver");
	
	private JMenuBar innerBarIcons = new JMenuBar();
	private JMenuBar innerBar = new JMenuBar();
	private JMenuBar outerBar = new JMenuBar();
	
	private JButton btnWagen = new JButton("");
	private JButton btnSingleMonth = new JButton("");
	private JButton btnMultiMonth = new JButton("");
	private JButton btnTrip = new JButton("");
	
	private int myTaxi; 
	
	// instantiate used Classes as instance to work with
	private MyCalendar cal = new MyCalendar();
	private TaxiHandler wagen = new TaxiHandler();
	private TripHandler trip = new TripHandler();
	private InsuranceHandler iHandler = new InsuranceHandler();
	private DriverHandler drvHandler = new DriverHandler();

	/**
	 * Description: responsible for the mapping of the menubar button to the
	 * called view.
	 * 
	 * 
	 */

	public void actionPerformed(ActionEvent e) {

		// ...Get information from the menu Drivers

		if (e.getSource() == this.mItem) {
			this.contentPane.removeAll();
			this.cal.showCalendar(BCalendar.CalendarType.MULTI_TIMESHEET,
					BCalendar.CalendarMode.WEEK);
		}
		if (e.getSource() == this.mItemdt) {
			this.contentPane.removeAll();
			this.contentPane.add(iHandler.getPanel());
		}
		if (e.getSource() == this.mItemdrv) {
			this.contentPane.removeAll();
			this.contentPane.add(drvHandler.getPanel());
		} else if (e.getSource() == this.btnWagen) {
			this.contentPane.removeAll();
			this.contentPane.add(wagen.getPanel());
		} else if (e.getSource() == this.btnMultiMonth) {
			this.contentPane.removeAll();
			this.contentPane.add(cal.panel);
			//cal.setDrivers(drivers);
			cal.setMode(BCalendar.CalendarMode.MONTH);
			this.cal.showCalendar(BCalendar.CalendarType.MULTI_TIMESHEET,
					BCalendar.CalendarMode.MONTH);
		} else if (e.getSource() == btnSingleMonth) {
			this.contentPane.removeAll();
			this.contentPane.add(cal.panel);
			cal.setMode(BCalendar.CalendarMode.MONTH);
			cal.showCalendar(BCalendar.CalendarType.SINGLE_TIMESHEET,
					BCalendar.CalendarMode.MONTH);
		} else if (e.getSource() == btnTrip) {
			this.contentPane.removeAll();
			this.trip.showGUI();
			this.contentPane.add(trip.wrapper);
		}

		this.contentPane.repaint();
		this.cal.panel.repaint();
		this.iHandler.repaint();
		this.wagen.getPanel().repaint();
		this.frame.validate();
		this.frame.repaint();
	}
	
/**
 * Description: sets up the main GUI - Interface
 * 
 */
	public void GUI() {
				
		// connect Panel

		// set Frame automatically to Window Max
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int screenWidth = dimension.width;
		int screenHeight = dimension.height;
		this.frame.setSize(new Dimension(dimension));

		// set other parameters
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = this.frame.getContentPane();
		this.contentPane.setLayout(new FlowLayout());

		// initialize outerBar
		this.outerBar.setLayout(new GridLayout(0, 1));

		// MenuBar #1
		this.menu.add(this.mItem);
		this.menudtable.add(this.mItemdt);
		this.menudtable.add(this.mItemdrv);
		this.innerBar.add(this.menu);
		this.innerBar.add(this.menudtable);
		this.outerBar.add(this.innerBar);

		// MenuBar #2 with Icons
		this.btnWagen.setIcon(new ImageIcon(
				"f:/workspace/transportliste/img/driver.png"));
		this.btnMultiMonth.setIcon(new ImageIcon(
				"f:/workspace/transportliste/img/calendar.png"));
		this.btnSingleMonth.setIcon(new ImageIcon(
				"f:/workspace/transportliste/img/cal_day.png"));
		this.btnTrip.setIcon(new ImageIcon(
				"f:/workspace/transportliste/img/trip.png"));
		this.innerBarIcons.add(this.btnWagen);
		this.innerBarIcons.add(this.btnSingleMonth);
		this.innerBarIcons.add(this.btnTrip);

		this.innerBarIcons.add(this.btnMultiMonth);

		// Add Listeners
		this.mItem.addActionListener(this);
		this.mItemdt.addActionListener(this);
		this.mItemdrv.addActionListener(this);
		this.btnWagen.addActionListener(this);
		this.btnTrip.addActionListener(this);
		this.innerBarIcons.add(this.btnWagen);
		this.btnSingleMonth.addActionListener(this);
		this.innerBarIcons.add(this.btnSingleMonth);
		this.btnMultiMonth.addActionListener(this);
		this.innerBarIcons.add(this.btnMultiMonth);
		
						
		// other standard stuff
		this.outerBar.add(this.innerBarIcons);
		this.frame.setJMenuBar(this.outerBar);
		this.contentPane.add(this.trip.wrapper);
		this.frame.setContentPane(contentPane);
		contentPane.setBackground(Color.yellow);
		this.frame.setVisible(true);
		this.cal.setInitialized(true);
		// this.cal.setDrivers(drivers);
	}

	public Start() {
		super();
		GUI();
		// 
		wagen.setLayout(new BorderLayout());
		trip.setLayout(new BorderLayout());
		iHandler.setLayout(new BorderLayout());
	}

	public static void main(String[] args) {
		
		
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
// start the native interface
		 NativeInterface.open();
		

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Start(); // Create and show the GUI.
			}
		});
		 NativeInterface.runEventPump();   
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	}

