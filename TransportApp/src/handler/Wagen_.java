package handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import stamm.Taxi;
import util.ETable;

public class Wagen_ extends JPanel implements ActionListener {
	// Class Declarations
	JTextField jtfText1;
	String disp = "";
	TextHandler handler = null;
	private JPanel panel = new JPanel();
	Taxi taxi = new Taxi();
	List<Taxi> taxiList = new ArrayList<Taxi>();
	ETable table = new ETable();
	DefaultTableModel model;
	Object[][] taxiArr = new Object[10][4];
	JScrollPane pane;
	String[] titles = { "id", "Taxi Name", "Manufacturer", "Next Service" };
	TableCellRenderer renderer;
	Component c;

	// Constructor
	public Wagen_() {
		super();
		panel.setLayout(new BorderLayout());
		jtfText1 = new JTextField("Taxi Management", 10);
		panel.add(jtfText1, BorderLayout.NORTH);
		handler = new TextHandler();
		jtfText1.addActionListener(handler);
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
		pane = new JScrollPane(table);
		model.fireTableDataChanged();
		panel.add(pane, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	// Inner Class TextHandler
	private class TextHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jtfText1) {
				disp = "text1 : " + e.getActionCommand();
			}
			JOptionPane.showMessageDialog(null, disp);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Wagen_ w = new Wagen_();
		w.jtfText1.setBackground(Color.WHITE);
		w.panel.setSize(345, 456);
		JFrame frm = new JFrame();
		frm.setSize(567, 555);
		Container c = frm.getContentPane();
		c.add(w.panel);
		frm.setContentPane(c);
		frm.setVisible(true);

	}

}// End of class TextFieldTest
