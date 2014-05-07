package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import util.GuiUtilities;

public class InputPanel extends JPanel {
private JPanel mainPanel = new JPanel(new GridLayout(1,2));

public InputPanel(){
	this.setLayout(null);
	mainPanel.setPreferredSize(new Dimension(GuiUtilities.PANEL_WIDTH, GuiUtilities.PANEL_HEIGHT));
	mainPanel.setBackground(Color.lightGray);
	this.add(mainPanel);
}
}
