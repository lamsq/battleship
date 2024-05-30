package summer;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Field extends JTable implements MouseListener{
	
	private Color defaultColor = Color.cyan;
	private Random rand = new Random();
	private ArrayList<int[]> coordTotal = new ArrayList<int[]>();
	private ArrayList<int[]> zoneTotal = new ArrayList<int[]>();
	private int size;
	
	public Field (DefaultTableModel model, int size, CustomPanel container) {
		
		super(model);
		
//		coordTotal = new ArrayList<int[]>();
//		zoneTotal = new ArrayList<int[]>();
		this.size = size;
				
		this.setRowHeight(18);
		for (int i = 0; i<size; i++) {
			this.getColumnModel().getColumn(i).setPreferredWidth(18);
		}
		
		for (int i = 0; i<size; i++) {
			if (i==0) {
				this.setValueAt("\\", i, i);
			}
			else {
				this.setValueAt(Character.toString((char)(64+i)), 0, i);
			}
		}
		
		for (int i = 1; i<size; i++) {
			this.setValueAt(Integer.toString(i), i, 0);
		}
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer grayCenterRender = new DefaultTableCellRenderer();
        grayCenterRender.setBackground(Color.lightGray);
        grayCenterRender.setHorizontalAlignment(JLabel.CENTER);
        
        this.setBackground(defaultColor);
        
		for(int i=0 ; i<size ; i++){
			this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			 
			if (i==0) {
				this.getColumnModel().getColumn(0).setCellRenderer(grayCenterRender);
			}
	    }
		
		container.add(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("test: mouse ckicked in the field");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		this.setBackground(Color.PINK);	
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		this.setBackground(defaultColor);
		
		// TODO Auto-generated method stub
		
	}
	
	
	public void setShips(int deck) {
		
		
		
		int ships = 5-deck; //number of ships depends on the number of decks
		int shipsCounter = 0;
			
		
			while(shipsCounter<ships) {
				
				ArrayList<int[]> coordTemp = new ArrayList<int[]>();
				
				int direction = rand.nextInt(1, 3); //1-vertical, 2 horizontal
				
				int startRow=0;
				int startCol=0;
				
				switch(direction) {				
					case 1:						
						startRow = rand.nextInt(1, size-deck);							
						startCol = rand.nextInt(1, size);							
						this.setValueAt("■", startRow, startCol);							
						break;
						
					case 2:						
						startRow = rand.nextInt(1, size);							
						startCol = rand.nextInt(1, size-deck);							
						this.setValueAt("■", startRow, startCol);							
						break;						
				}
				
				coordTemp.add(new int[] {startRow, startCol});
				
				
				for (int o = 0; o<deck-1; o++) {
					
					int row=0;
					int col=0;
					
					switch(direction) {
						case 1:							
							row = startRow+o+1;
							col = startCol;
							this.setValueAt("■", row, col);							
							break;
							
						case 2:		
							row = startRow;
							col = startCol+o+1;							
							this.setValueAt("■", row, col);							
							break;						
					}
					
					coordTemp.add(new int[] {row, col});
					
					
					
					
				}	
				
				boolean collision = false;
				for (int j=0; j<coordTemp.size(); j++) {						
					for (int q=0; q<coordTotal.size(); q++) {							
						if (coordTemp.get(j)==coordTotal.get(q)) {
							collision = true;
							break;
						}	
					}
				}
				
				if(collision) {
					continue;
				}
				else {
					for (int j=0; j<coordTemp.size(); j++) {					
						coordTotal.add(coordTemp.get(j));
					}
					
					shipsCounter++;
				}
				
				
				
			}
			
			
		//this.repaint();
		
	}
	
	
	public void resetField() {
		
		for (int o = 0; o<size; o++) {			
			for (int u = 0; u<size; u++) { 				
				this.setValueAt(" ", o, u);
			}					
		}	
		
		for (int i = 0; i<size; i++) {
			if (i==0) {
				this.setValueAt("\\", i, i);
			}
			else {
				this.setValueAt(Character.toString((char)(64+i)), 0, i);
			}
		}
		
		for (int i = 1; i<size; i++) {
			this.setValueAt(Integer.toString(i), i, 0);
		}
		
		coordTotal = new ArrayList<int[]>();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
