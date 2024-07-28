package summer;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.System.*;
import java.time.Year;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

public class Graphic {
	
	//static CustomFrame mFrame;
	static Field userField;
	static Field computerField;
	static CustomButton startButton;
	static CustomButton stopButton;
	static CustomButton aboutButton;
	static boolean userTurn = true;
	private static int clickCounter=0;
	private static int selectRow;
	private static int selectCol;
	private static int confirmRow;
	private static int confirmCol;
	static Random rand = new Random();
	static int size;
	static boolean finished = false;
	
	public static void main (String[] args) {
		
		CustomFrame mFrame = new CustomFrame("Battleship");
		
		CustomPanel mainPanel = new CustomPanel(mFrame, 20, 20, 20, 20);
		CustomPanel buttonPanel = new CustomPanel(mainPanel);
		
		startButton = new CustomButton("Start", 80, 50, buttonPanel, true);
		stopButton = new CustomButton("Stop", 80, 50, buttonPanel, false);
		aboutButton = new CustomButton("About", 80, 50, buttonPanel, true);
		
		CustomPanel compFieldLabelPanel = new CustomPanel(mainPanel);
		CustomLabel compFieldLabel = new CustomLabel(compFieldLabelPanel, "CPU:");
		
		size = 10;
		size = size+1;
		
		CustomPanel compFieldPanel = new CustomPanel(mainPanel);
		DefaultTableModel modelComp = new DefaultTableModel(size, size);
		computerField = new Field(modelComp, size, compFieldPanel, true, false);
		CustomPanel userFieldLabelPanel = new CustomPanel(mainPanel);
		
		CustomLabel userFieldLabel = new CustomLabel(userFieldLabelPanel, "Player:");		
		CustomPanel userFieldPanel = new CustomPanel(mainPanel);
		DefaultTableModel modelUser = new DefaultTableModel(size, size);
		userField = new Field(modelUser, size, userFieldPanel, true, true);
		
		startButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {			    
				    Graphic.userField.setActive(false);					
				    for (int i=1; i<5; i++) {
				    	Graphic.userField.setShips(i);
				    }					
					Graphic.computerField.setActive(true);					
					for (int i=1; i<5; i++) {
				    	Graphic.computerField.setShips(i);
				    }					
					Graphic.userField.setFocusable(false);
					Graphic.userField.setRowSelectionAllowed(false);					
					startButton.setEnabled(false);
					Graphic.stopButton.setEnabled(true);
			   }
		});
		
		stopButton.addActionListener(new ActionListener() {			
			 public void actionPerformed(ActionEvent e) {
					Graphic.computerField.resetField();
					Graphic.userField.resetField();					
					stopButton.setEnabled(false);
					Graphic.startButton.setEnabled(true);					
			 }
		});
		
		aboutButton.addActionListener(new ActionListener() {	
			 public void actionPerformed(ActionEvent e) {				 
				 CustomFrame aboutFrame = new CustomFrame("About");		
				 aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
				 CustomPanel mainAboutPanel = new CustomPanel(aboutFrame, 20, 20, 20, 20);
				 CustomPanel labelAboutPanel = new CustomPanel(mainAboutPanel);
				 CustomPanel buttonAboutPanel = new CustomPanel(mainAboutPanel);
				 CustomLabel aboutLabel = new CustomLabel(labelAboutPanel, "<html><center><font size=\"+1\">Project page:"
				 		+ "<br>github.com/lamsq/battleship</font></center>"
				 		+ "<br><br><center>2024-"+Year.now().getValue()+" ©</center></html>");
				 CustomButton okButton = new CustomButton("Ok", 80, 50, buttonAboutPanel, true); 	
				 okButton.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e)	{
						 aboutFrame.dispose();
						 }
					});					
				 aboutFrame.pack();
				 aboutFrame.setLocationRelativeTo(null);
				 aboutFrame.setVisible(true);
			 }
		});
		
		userField.setActive(false);
		computerField.setActive(false);
		
		mFrame.pack();
		mFrame.setLocationRelativeTo(null);
		mFrame.setVisible(true);
		
		computerField.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				if (userTurn && !finished) { 
					if (clickCounter==1) {				
						confirmRow=computerField.getSelectedRow();
						confirmCol=computerField.getSelectedColumn();				
						if(confirmRow==selectRow && confirmCol==selectCol) {			
							clickCounter++;		
						}				
						else {
							clickCounter=0;
						}				
					}
					else if (clickCounter ==0) {
						selectRow = computerField.getSelectedRow();
						selectCol = computerField.getSelectedColumn();
						clickCounter++;				
					}
				}					
				if (clickCounter == 2 && !finished) {					
					if(computerField.cellAttacked(confirmRow, confirmCol)) {
						userTurn = true;						
						if(computerField.getShipsCoord().size()==0)   //end of the game    
							finished = true;
					}
					else {						
						int[] atcCoord = userField.getAttackCoordinates();						
						while(userField.attacked(atcCoord)) {							
							atcCoord = userField.getAttackCoordinates();							
							if(userField.getShipsCoord().size()==0)   //end of the game   
								finished = true;																			
						}						
						userTurn = true;												
					}					
					clickCounter = 0;						
				}
				if(finished) {					
					if(userField.getShipsCoord().size()==0)
						computerField.gameOver();						
					else
						userField.gameOver();					
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {				
				computerField.setBackground(Color.PINK);
			}

			@Override
			public void mouseExited(MouseEvent e) {				
				if(computerField.isActive()) 
					computerField.setBackground(Color.cyan);				
				else 
					computerField.setBackground(Color.GRAY);				
			}		
		});
		
	}
	
	
	
}
