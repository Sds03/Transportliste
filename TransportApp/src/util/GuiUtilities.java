package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



public class GuiUtilities {

	/**
	 * @param args
	 */
	static final Color ALTERNATE_ROW_COLOR =  new Color(240,240,240);
	public static final String IMPORT_PATIENT_PATH = "C:\\medtrans\\patientimport.gdt";
	public static final int PANEL_WIDTH = 1024;
	public static final int PANEL_HEIGHT = 768;
	public static final int PANEL_COLOR_INPUT = 248;
	public static final String[] AREAS ={"Brunsbüttel","Flensburg", "Lübeck","Kiel","Westcoast"};
	public static final int MAX_GUEST_NR = 5;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	class ButtonRenderer extends JButton implements TableCellRenderer, ActionListener {
		private JButton renderButton;
		private JButton editButton;
		private Border originalBorder;
		private Border focusBorder;
		private JTable table;
		private Action action;
		
		public ButtonRenderer(JTable table) {
			setOpaque(true);
			renderButton = new JButton();
			editButton = new JButton();
			editButton.setFocusPainted( false );
			editButton.addActionListener( this );
			originalBorder = editButton.getBorder();
			setBorder( new LineBorder(Color.BLUE) );
			this.table = table;
			
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
		
			return this;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			int row = table.convertRowIndexToModel( table.getEditingRow() );
			//fireEditingStopped();
			
			//  Invoke the Action

			ActionEvent event = new ActionEvent(
				table,
				ActionEvent.ACTION_PERFORMED,
				"" + row);
			action.actionPerformed(event);
		}
	}
	
}
