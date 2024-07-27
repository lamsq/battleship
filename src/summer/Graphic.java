package summer;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.System.*;
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
		computerField = new Field(modelComp, size, compFieldPanel, true);
		CustomPanel userFieldLabelPanel = new CustomPanel(mainPanel);
		
		
		CustomLabel userFieldLabel = new CustomLabel(userFieldLabelPanel, "Player:");		
		CustomPanel userFieldPanel = new CustomPanel(mainPanel);
		DefaultTableModel modelUser = new DefaultTableModel(size, size);
		userField = new Field(modelUser, size, userFieldPanel, true);
		
		
		//action listeners for buttons
		//startButton.addActionListener(startButton);
		
		startButton.addActionListener(new ActionListener() {

			   public void actionPerformed(ActionEvent e) {
			    
				    Graphic.userField.setActive(false);
					
					Graphic.userField.setShips(4);
					Graphic.userField.setShips(3);
					Graphic.userField.setShips(2);
					Graphic.userField.setShips(1);
					
					Graphic.computerField.setActive(true);
					
					Graphic.computerField.setShips(4);
					Graphic.computerField.setShips(3);
					Graphic.computerField.setShips(2);
					Graphic.computerField.setShips(1);
					
					Graphic.userField.setFocusable(false);
					Graphic.userField.setRowSelectionAllowed(false);
					
					startButton.setEnabled(false);
					Graphic.stopButton.setEnabled(true);
					
					
					
					System.out.println("START");
				   
			   }
		});
		
		
		stopButton.addActionListener(new ActionListener() {
			
			 public void actionPerformed(ActionEvent e) {

					Graphic.computerField.resetField();
					Graphic.userField.resetField();
					
					stopButton.setEnabled(false);
					Graphic.startButton.setEnabled(true);
				
					System.out.println("STOP");
			 }
		});
		
		//aboutButton.addActionListener(aboutButton);
		
		
		
		//computerField.addMouseListener(computerField);
		//userField.addMouseListener(userField);
		
		userField.setActive(false);
		
		computerField.setActive(false);
		
		
		
		
		mFrame.pack();
		mFrame.setVisible(true);
		
		
		
		
		
		
		computerField.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				System.out.println("test: mouse ckicked in the field");				
				
				if (userTurn && !finished) { //computerField.getSelectedRow()>0 && computerField.getSelectedColumn()>0
					if (clickCounter==1) {				
						confirmRow=computerField.getSelectedRow();
						confirmCol=computerField.getSelectedColumn();				
						if(confirmRow==selectRow && confirmCol==selectCol) {			
							clickCounter++;		
							System.out.println("counter increased");
						}				
						else {
							clickCounter=0;
							System.out.println("counter decreased");
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
						
						if(computerField.getShipsCoord().size()==0) {  //end of the game       userField.getShipsCoord().size()==0 ||
							finished = true;
							System.out.println("GAME OVER \nYOU WON");
						}
					}
					else {
						
						
						
						int[] atcCoord = userField.getAttackCoordinates();
						
						while(userField.attacked(atcCoord)) {
							
							atcCoord = userField.getAttackCoordinates();
							
							if(userField.getShipsCoord().size()==0) {  //end of the game   
								finished = true;
								System.out.println("GAME OVER \nPC WON");
							}							
							System.out.println("USER ATTACKED");							
						}
						
						userTurn = true;
												
					}					
					clickCounter = 0;						
				}
				if(finished) {					
					System.out.println("GAME OVER");
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
				
				if(computerField.isActive()) {
					computerField.setBackground(Color.cyan);
				}
				else {
					computerField.setBackground(Color.GRAY);
				}
			}		
		});
		
		

		
		
		
	}
	
	
	
}
