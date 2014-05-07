package handler;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DAODriver;
import stamm.Driver;

public class DriverHandler extends JPanel implements ActionListener, MouseListener {

	JScrollPane pane = new JScrollPane();
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable();
	private JPanel panel = new JPanel(new BorderLayout());
	JTextField tfNewDriver = new JTextField("new driver",25);
	JButton btnSave = new JButton("Save");
	List<Driver> dlist = new ArrayList();
	Driver dtemp = new Driver();
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSave) {
				dtemp.setLastname(tfNewDriver.getText());
				dlist.add(dtemp);
			}
		DAODriver.saveData(dlist);
		
		this.panel.revalidate();
		this.panel.repaint();

	}

 
	//ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
	//buttonColumn.setMnemonic(KeyEvent.VK_D);


	/**
	 * Description: Input Template for Insurance companies
	 * 
	 * 
	 */
	public DriverHandler() {
		
		Object[] headers = {"id","Name","Button"};
		for (int i = 0; i < DAODriver.getDriverist().size(); i++) {
			Driver tmp = new Driver();
			tmp.setId(DAODriver.getDriverist().get(i).getId());
			tmp.setLastname(DAODriver.getDriverist().get(i).getLastname());
			dlist.add(tmp);
		}
		Object[][] o = new Object[dlist.size()][3];
		for (Driver d : dlist) {
			o[dlist.indexOf(d)][0] = d.getId();
			o[dlist.indexOf(d)][1] = d.getLastname();
			//o[dlist.indexOf(d)][2] = "Button";
			
		}
		JPanel pnlSave = new JPanel(new GridLayout(1,2));
		model.setDataVector(o, headers);
		table.setModel( model);
//		table.getColumn("Button").setCellRenderer(new ColumnButton());
		model.fireTableDataChanged();
		pane.getViewport().add(table);
		btnSave.addActionListener(this);
		pnlSave.add(btnSave);
		pnlSave.add(tfNewDriver);
		this.panel.add(pnlSave,BorderLayout.SOUTH);
		this.panel.add(pane, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
