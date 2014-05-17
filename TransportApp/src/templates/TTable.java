package templates;

import javax.swing.JTable;
import javax.swing.border.Border;

public class TTable extends javax.swing.JTable {

public TTable(Object[][] o1, Object[] o2){
	super(o1,o2);
	this.setRowHeight(22);
	this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
}
public TTable(){ 
	super();
	
}
}
