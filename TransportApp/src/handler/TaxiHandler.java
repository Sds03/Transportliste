package handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import dao.DAOTaxi;

import stamm.Taxi;
import util.ETable;

public class TaxiHandler extends JPanel implements ActionListener {

	private JTextField tftaxiName = new JTextField();
	private JCheckBox cbtaxi¡ctive = new JCheckBox();
	private JButton btntaxiAdd = new JButton("Add Taxi");
	public JPopupMenu popUpGebDat = new JPopupMenu();
	private JTextField tftaxiMaker = new JTextField();
	private JPanel pnlMainTaxi = new JPanel();
	private JDateChooser jcal = new JDateChooser();
	private JPanel panel = new JPanel(new BorderLayout());
	private Taxi myTaxi = new Taxi();
	// Class Declarations
	JTextField jtfText1;
	String disp = "";
	TextHandler handler = null;

	List<Taxi> taxiList = new ArrayList<Taxi>();
	ETable table = new ETable();
	DefaultTableModel model;
	Object[][] taxiArr = new Object[10][4];
	JScrollPane pane;
	String[] titles = { "id", "Taxi Name", "Manufacturer", "Next Service" };
	TableCellRenderer renderer;
	Component c;

	// Constructor
	public TaxiHandler() {
		super();

		handler = new TextHandler();
		loadTableData();

		pane = new JScrollPane(table);
		StartGUI();

	}

	public void loadTableData() {

		taxiList = TaxiHandler.getAllTaxis();
		for (int i = 0; i < taxiList.size(); i++) {
			taxiArr[i][0] = taxiList.get(i).getId();
			taxiArr[i][1] = taxiList.get(i).getName();
			taxiArr[i][2] = taxiList.get(i).getHersteller();
			taxiArr[i][3] = taxiList.get(i).getNextService();
		}

		model = new DefaultTableModel(taxiArr, titles);
		table.setModel(model);

		for (int i = 0; i < table.getRowCount(); i++)
			for (int j = 0; j < 4; j++) {
				renderer = table.getCellRenderer(i, j);
				c = table.prepareRenderer(renderer, i, j);
			}
		model.fireTableDataChanged();

	}

	public void StartGUI() {
		jtfText1 = new JTextField("Taxi Management", 10);
		panel.add(jtfText1, BorderLayout.NORTH);
		pnlMainTaxi.setLayout(null);
		pnlMainTaxi.setPreferredSize(new Dimension(390, 567));
		pnlMainTaxi.setBackground(Color.LIGHT_GRAY);
		panel.setAlignmentX(LEFT_ALIGNMENT);
		JLabel lbltaxiName = new JLabel("TaxiName");
		lbltaxiName.setBounds(5, 5, 160, 24);
		tftaxiName.setBounds(205, 5, 180, 24);
		JLabel lbltaxiMaker = new JLabel("Taxi Manufacturer");
		lbltaxiMaker.setBounds(5, 28, 160, 24);
		tftaxiMaker.setBounds(205, 32, 180, 24);
		JLabel lbltaxiNextService = new JLabel("Next Service");
		lbltaxiNextService.setBounds(5, 56, 160, 24);
		btntaxiAdd.setBounds(205, 260, 124, 24);
		JLabel lbltaxiActive = new JLabel("Active/not in Service");
		lbltaxiActive.setBounds(5, 88, 120, 24);
		cbtaxi¡ctive.setBounds(205, 88, 100, 24);
		jcal.setComponentPopupMenu(popUpGebDat);
		jcal.setBounds(205, 56, 150, 24);
		panel.add(jcal, BorderLayout.WEST);
		
		// add components to panel
		pnlMainTaxi.add(tftaxiName);
		pnlMainTaxi.add(jcal);
		pnlMainTaxi.add(tftaxiMaker);
		pnlMainTaxi.add(btntaxiAdd);
		pnlMainTaxi.add(cbtaxi¡ctive);
		pnlMainTaxi.add(lbltaxiName);
		pnlMainTaxi.add(lbltaxiMaker);
		pnlMainTaxi.add(lbltaxiNextService);
		pnlMainTaxi.add(lbltaxiActive);
		panel.add(pnlMainTaxi, BorderLayout.WEST);
		panel.add(pane, BorderLayout.CENTER);

		// add listeners
		btntaxiAdd.addActionListener(this);
		jtfText1.addActionListener(handler);
	}

	public static List<Taxi> getAllTaxis() {

		return DAOTaxi.getAllDataElements("Taxi");

	}

	private class TextHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jtfText1) {
				disp = "text1 : " + e.getActionCommand();
			}
			JOptionPane.showMessageDialog(null, disp);
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public String getDisp() {
		return disp;
	}

	public void setDisp(String disp) {
		this.disp = disp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btntaxiAdd)
			panel.remove(pane);
		this.myTaxi.setName(tftaxiName.getText());
		this.myTaxi.setHersteller(tftaxiMaker.getText());
		this.myTaxi.setNextService(jcal.getDate());
		DAOTaxi.SaveTaxi(myTaxi);
		loadTableData();
		panel.add(pane);
		panel.revalidate();
		panel.repaint();

	}
}
