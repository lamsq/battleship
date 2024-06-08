package summer;

import java.util.*;
import java.lang.System.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import java.awt.*;

public class Graphic {
	
	//static CustomFrame mFrame;
	static Field userField;
	static Field computerField;
	static CustomButton startButton;
	static CustomButton stopButton;
	static CustomButton aboutButton;
	
	public static void main (String[] args) {
		
		
		CustomFrame mFrame = new CustomFrame("Battleship");
		
		CustomPanel mainPanel = new CustomPanel(mFrame, 20, 20, 20, 20);
		CustomPanel buttonPanel = new CustomPanel(mainPanel);
		
		startButton = new CustomButton("Start", 80, 50, buttonPanel, true);
		stopButton = new CustomButton("Stop", 80, 50, buttonPanel, false);
		aboutButton = new CustomButton("About", 80, 50, buttonPanel, true);
		
		CustomPanel compFieldLabelPanel = new CustomPanel(mainPanel);
		CustomLabel compFieldLabel = new CustomLabel(compFieldLabelPanel, "CPU:");
		
		
		int size = 10;
		size = size+1;
		int move = 0;
		
		
		CustomPanel compFieldPanel = new CustomPanel(mainPanel);
		DefaultTableModel modelComp = new DefaultTableModel(size, size);
		computerField = new Field(modelComp, size, compFieldPanel, true);
		CustomPanel userFieldLabelPanel = new CustomPanel(mainPanel);
		
		
		CustomLabel userFieldLabel = new CustomLabel(userFieldLabelPanel, "Player:");		
		CustomPanel userFieldPanel = new CustomPanel(mainPanel);
		DefaultTableModel modelUser = new DefaultTableModel(size, size);
		userField = new Field(modelUser, size, userFieldPanel, true);
		
		
		//action listeners for buttons
		startButton.addActionListener(startButton);
		stopButton.addActionListener(stopButton);
		aboutButton.addActionListener(aboutButton);
		
		
		
		computerField.addMouseListener(computerField);
		userField.addMouseListener(userField);
		
		while (computerField.getShipsCoordinates().size()!=0 || userField.getShipsCoordinates().size()!=0 ) {
			
			if (move%2==0) {
				
				
				userField.setActive(false);
				
				
				computerField.setActive(true);
				
				
			}
			else {
				
				userField.setActive(true);
				
				
				computerField.setActive(false);
				
				
			}
			move++;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		mFrame.pack();
		mFrame.setVisible(true);
		
		
		
	}
	
	
	
	
	
	
        
	
	
	
	
	
	
	
	/**
	public class CellRender extends DefaultTableCellRenderer  {
		
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
	    	
	    	Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 

	    	cell.setBackground(Color.red); 

	    	return cell;
	    	
	    } 
	}
	**/
	
}
