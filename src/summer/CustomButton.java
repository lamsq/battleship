package summer;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomButton extends JButton implements ActionListener{

	public CustomButton(String name, int width, int height, CustomPanel container, boolean active) {
		
		this.setText(name);
		this.setSize(width, height);
		container.add(this);
		this.setEnabled(active);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			JButton pressed = (JButton) e.getSource();
			
			if (pressed.getText().equals("Start")) {
				
//				Graphic.computerField.setShips(4);
//				Graphic.computerField.setShips(3);
				Graphic.computerField.setShips(2);
//				Graphic.computerField.setShips(1);
				
				this.setEnabled(false);
				Graphic.stopButton.setEnabled(true);
				
				System.out.println("START");
			}
			
			else if (pressed.getText().equals("Stop")) {
				
				Graphic.computerField.resetField();
				Graphic.userField.resetField();
				
				
				this.setEnabled(false);
				Graphic.startButton.setEnabled(true);
				
				System.out.println("STOP");
			}
			
			else if (pressed.getText().equals("About")) {
				
			}
		}
	}
	
	
	
	
	
	
	
}
