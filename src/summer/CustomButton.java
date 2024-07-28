package summer;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomButton extends JButton {

	public CustomButton(String name, int width, int height, CustomPanel container, boolean active) { //button constructor	
		//sets parameters
		this.setText(name);
		this.setSize(width, height);
		container.add(this);
		this.setEnabled(active);
	}
	
}
