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
				ArrayList<int[]> zoneTemp = new ArrayList<int[]>();
				
				int direction = 2; //rand.nextInt(1, 3); //1-vertical, 2 horizontal
				int row=0;							
				int col=0;
				
				switch(direction){				
					case 1:						
						row = rand.nextInt(1, size-deck);							
						col = rand.nextInt(1, size);											
						break;						
					case 2:						
						row = rand.nextInt(1, size);							
						col = rand.nextInt(1, size-deck);							
						break;						
				}				
				
				for (int o = 0; o<deck; o++) {					
					switch(direction) {
						case 1:	
							
							row++;							
							System.out.println("COORD: "+row+" "+col);
							
							if (o==0) { //sets dmz on the top								
								for (int z=-1; z<2; z++) {									
									if (row-1<1 || col+z>size-1 || col+z<1) 
										continue;									
									else {										
										int[] topZone = {row-1,col+z};
										zoneTemp.add(topZone);
									}									
								}								
							}
							
							if (o<deck) {		//sets dmz along the ship						
								int[] zone1 = {row, col-1};
								int[] zone2 = {row, col+1};								
								if(zone1[1]<1) {
									zoneTemp.add(zone2);  
								}
								else if (zone2[1]>size-1) {									
									zoneTemp.add(zone1);
								}
								else {				
									zoneTemp.add(zone1);									
									zoneTemp.add(zone2);										
								}
							}
							
							if (o==deck-1) { //sets dmz on the bottom								
								for (int z=-1; z<2; z++) {									
									if (row+1>size-1 || col+z>size-1 || col+z<1) 
										continue;									
									else {
										int[] bottomZone = {row+1, col+z};
										zoneTemp.add(bottomZone);
									}									
								}								
							}							
							break;
							
						case 2:	
							
							col++;							
							
							if (o==0) { //sets dmz on the top								
								for (int z=-1; z<2; z++) {									
									if (col-1<1 || row+z>size-1 || row+z<1) 
										continue;									
									else {										
										int[] topZone = {row+z,col-1};
										zoneTemp.add(topZone);
									}									
								}								
							}
							
							if (o<deck) {		//sets dmz along the ship						
								int[] zone1 = {row-1, col};
								int[] zone2 = {row+1, col};								
								if(zone1[0]<1) {
									zoneTemp.add(zone2);
								}
								else if (zone2[0]>size-1) {
									zoneTemp.add(zone1);
								}
								else {							
									zoneTemp.add(zone1);
									zoneTemp.add(zone2);
								}
							}
							
							if (o==deck-1) { //sets dmz on the bottom								
								for (int z=-1; z<2; z++) {									
									if (col+1>size-1 || row+z>size-1 || row+1<1) 
										continue;									
									else {
										int[] bottomZone = {row+z, col+1};
										zoneTemp.add(bottomZone);
									}									
								}								
							}							
							break;						
					}					
					coordTemp.add(new int[] {row, col});					
				}
				
				
				
				boolean collision = false;
				for (int j=0; j<coordTemp.size(); j++) {						
					for (int q=0; q<coordTotal.size(); q++) {							
						if (Arrays.equals(coordTemp.get(j), coordTotal.get(q))) {
							collision = true;
							break;
						}	
					}
				}
				
				if(collision)
					continue;				
				else {					
					for (int j=0; j<coordTemp.size(); j++) {					
						coordTotal.add(coordTemp.get(j));
						this.setValueAt("■", coordTemp.get(j)[0], coordTemp.get(j)[1]);						
					}	
					
//					for (int i=0; i<coordTemp.size(); i++) {
//						System.out.println(coordTemp.get(i)[0]+"-"+coordTemp.get(i)[1]);
//					}
					
					Ship ship = new Ship(deck, coordTemp, zoneTemp, direction, size);
					shipsCounter++;					
				}
				
				
				for (int l=0; l<zoneTemp.size(); l++) {					
					zoneTotal.add(zoneTemp.get(l));					
				}
				for (int i=0; i<zoneTotal.size(); i++) {
					this.setValueAt("x", zoneTotal.get(i)[0], zoneTotal.get(i)[1]);	
					System.out.println(zoneTotal.get(i)[0]+"-"+zoneTotal.get(i)[1]);
				}
				
				
			}
						
		
		
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
		zoneTotal = new ArrayList<int[]>();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
