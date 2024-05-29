package summer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Field extends JTable implements MouseListener{
	
	private Color defaultColor = Color.cyan;
	private Random rand = new Random();
	private ArrayList<Integer[]> coordTotal;
	private ArrayList<Integer[]> zoneTotal;
	
	public Field (DefaultTableModel model, int size, CustomPanel container) {
		
		super(model);
				
		//this.size = size;
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
		
		int ships  = 5-deck;
		
			for(int i =0; i<ships-1; i++) {
				
				boolean shipCompleted = false;
				
				while (!shipCompleted) {
				
					int direction = rand.nextInt(1, 3); //1-vertical, 2 horizontal
					
					int startRow;
					int startCol;
					
					switch(direction) {
					
						case 1: 
							
							startRow = rand.nextInt(2, 12-deck);							
							startCol = rand.nextInt(2, 12);							
							this.setValueAt("■", startRow, startCol);					
							
							break;
							
						case 2:
							
							startRow = rand.nextInt(2, 12);							
							startCol = rand.nextInt(2, 12-deck);							
							this.setValueAt("■", startRow, startCol);
							
							break;
							
					}
					
					for (int o = 0; o<deck; o++) {
						
						
					
					}
				}
				
			}
			
		
		//this.repaint();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
