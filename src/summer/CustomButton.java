package summer;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomButton extends JButton {

	public CustomButton(String name, int width, int height, CustomPanel container, boolean active) {
		
		this.setText(name);
		this.setSize(width, height);
		container.add(this);
		this.setEnabled(active);
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//		if (e.getSource() instanceof JButton) {
//			JButton pressed = (JButton) e.getSource();
//			
//			if (pressed.getText().equals("About")) {
//				
//			}
//		}
//	}
	
	
	
	
	
	
	
}
