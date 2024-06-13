package summer;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Field extends JTable {
	
	private Color defaultColor = Color.cyan;
	private Random rand = new Random();
	private ArrayList<int[]> coordTotal = new ArrayList<int[]>();
	private ArrayList<int[]> zoneTotal = new ArrayList<int[]>();
	private int size;
	private static ArrayList<int[]> pcAttacksTotal = new ArrayList<int[]>();
	private boolean active;
	
	
	public Field (DefaultTableModel model, int size, CustomPanel container, boolean active) {
		
		super(model);
		this.size = size;
		this.active = active;
				
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


	
	public boolean cellAttacked(int row, int col) {
		
		addPcAttacks(new int[] {row, col});	
		
		System.out.println(row+" "+ col);
		
		if (this.getModel().getValueAt(row, col)!=null && this.getModel().getValueAt(row, col).equals("X")) {
			return false;
		}
		
		for (int i=0; i<this.coordTotal.size(); i++) {
			
			if (this.coordTotal.get(i)[0]==row && this.coordTotal.get(i)[1]==col ) {
				this.setValueAt("X", row, col);
				this.coordTotal.remove(i);
				
				return true;
			}			
		}
		this.setValueAt("o", row, col);
		return false;
	}
	
	
	
	
	public void setShips(int deck) {		
		
		int ships = 5-deck; //number of ships depends on the number of decks
		int shipsCounter = 0;			
		
			while(shipsCounter<ships) {				
				
				ArrayList<int[]> coordTemp = new ArrayList<int[]>();
				ArrayList<int[]> zoneTemp = new ArrayList<int[]>();
				
				int direction = rand.nextInt(1, 3); //1-vertical, 2 horizontal
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
								if(zone1[1]<1) 
									zoneTemp.add(zone2); 								
								else if (zone2[1]>size-1) 								
									zoneTemp.add(zone1);								
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
								if(zone1[0]<1) 
									zoneTemp.add(zone2);								
								else if (zone2[0]>size-1) 
									zoneTemp.add(zone1);								
								else {							
									zoneTemp.add(zone1);
									zoneTemp.add(zone2);
								}
							}
							
							if (o==deck-1) { //sets dmz on the bottom								
								for (int z=-1; z<2; z++) {									
									if (col+1>size-1 || row+z>size-1 || row+z<1) 
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
							j=coordTemp.size()-1;
							break;
						}	
					}
					for (int q=0; q<zoneTotal.size(); q++) {							
						if (Arrays.equals(coordTemp.get(j), zoneTotal.get(q))) {
							collision = true;
							j=coordTemp.size()-1;
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
					
					
					for (int j=0; j<zoneTemp.size(); j++) {					
						zoneTotal.add(zoneTemp.get(j));							
					}
										
					Ship ship = new Ship(deck, coordTemp, zoneTemp, direction, size);
					shipsCounter++;					
				}
				
			}
			
	}
	
	
	public ArrayList<int[]> getShipsCoordinates(){		
		return this.coordTotal;
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
	
	
	@Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
	
	public boolean isActive() {
		return this.active;
	}
	
	public void setActive(boolean active) {		
		this.setEnabled(active);
		this.setFocusable(active);
		this.setRowSelectionAllowed(active);		
		if(active==true) {
			this.setBackground(defaultColor);
		}
		else {
			this.setBackground(Color.GRAY);
		}		
		this.active = active;
	}
	
	
	public boolean attacked(int row, int col, boolean direction) {
		
		if(direction) {
			
			int directionInt = rand.nextInt(1, 3); //1 - vert, 2 - hor
			
			if (directionInt==1) {
			
								
				if(row==1) {						
					if(this.cellAttacked(row+1, col)) {			
						System.out.println("USER IS HIT");
						return true;
					}
					else {
						System.out.println("PC MISSED");
						return false;
					}						
				}
				else if (row==size) {						
					if(this.cellAttacked(row-1, col)) {			
						System.out.println("USER IS HIT");
						return true;
					}
					else {
						System.out.println("PC MISSED");
						return false;
					}						
				}
				else {						
					int upDown = rand.nextInt(1, 3); //1-up, 2-down						
					if (upDown==1) {						
													
							if(this.cellAttacked(row-1, col)) {			
								System.out.println("USER IS HIT");
								return true;
							}
							else {
								System.out.println("PC MISSED");
								return false;
							}
					}
					else {								
							if(this.cellAttacked(row+1, col)) {			
								System.out.println("USER IS HIT");
								return true;
							}
							else {
								System.out.println("PC MISSED");
								return false;
							}							
					}
				}
		}					
			else {					
				if(col==1) {						
					if(this.cellAttacked(row, col+1)) {			
						System.out.println("USER IS HIT");
						return true;
					}
					else {
						System.out.println("PC MISSED");
						return false;
					}						
				}
				else if (col==size) {						
					if(this.cellAttacked(row, col-1)) {			
						System.out.println("USER IS HIT");
						return true;
					}
					else {
						System.out.println("PC MISSED");
						return false;
					}						
				}
				else {						
					int leftRight = rand.nextInt(1, 3); //1-left, 2-right						
					if (leftRight==1) {															
							if(this.cellAttacked(row, col-1)) {			
								System.out.println("USER IS HIT");
								return true;
							}
							else {
								System.out.println("PC MISSED");
								return false;
							}								
					}
					else {
							if(this.cellAttacked(row, col+1)) {			
								System.out.println("USER IS HIT");
								return true;
							}
							else {
								System.out.println("PC MISSED");
								return false;
							}							
					}
				}
			}
		}
		
		else {			
			if(this.cellAttacked(row, col)) {			
				System.out.println("USER IS HIT");
				return true;
			}
			else {
				System.out.println("PC MISSED");
				return false;
			}			
		}					
	}
	
	
	
	
	public static ArrayList<int[]> getPcAttacks(){
		
		return pcAttacksTotal;
	}
	
	public static void addPcAttacks(int[] attack) {
		
		pcAttacksTotal.add(attack);
	}
	
		
	}


