package handler;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DAOInsurance;

import stamm.Insurance;

public class InsuranceHandler extends JPanel implements ActionListener {

	private JScrollPane pane = new JScrollPane();
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable();
	private JPanel panel = new JPanel(new BorderLayout());
	private JTextField tfNewInsurance = new JTextField("new insurance",25);
	private JButton btnSave = new JButton("Save");
	private List<Insurance> ilist = new ArrayList();
	private Insurance itemp = new Insurance();
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSave) {
				itemp.setName(tfNewInsurance.getText());
				ilist.add(itemp);
			}
		DAOInsurance.saveData(ilist);
		model.fireTableDataChanged();
		this.panel.revalidate();
		this.panel.repaint();
	}

	/**
	 * Description: Input Template for Insurance companies
	 * 
	 * 
	 */
	public InsuranceHandler() {
		model.addColumn("id");
		model.addColumn("insurance");
		for (int i = 0; i < DAOInsurance.getInsuranceList().size(); i++) {
			Insurance tmp = new Insurance();
			tmp.setId(DAOInsurance.getInsuranceList().get(i).getId());
			tmp.setName(DAOInsurance.getInsuranceList().get(i).getName());
			ilist.add(tmp);
		}
		for (Insurance i : ilist) {
			Object[] o = new Object[2];
			o[0] = i.getId();
			o[1] = i.getName();
			model.addRow(o);
		}
		JPanel pnlSave = new JPanel(new GridLayout(1,2));
		table.setModel(model);
		pane.getViewport().add(table);
		btnSave.addActionListener(this);
		pnlSave.add(btnSave);
		pnlSave.add(tfNewInsurance);
		this.panel.add(pnlSave,BorderLayout.SOUTH);
		this.panel.add(pane, BorderLayout.CENTER);
	}

}
