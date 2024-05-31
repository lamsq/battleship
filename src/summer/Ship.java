package summer;

import java.util.*;

public class Ship {

	private int deck;
	private int[] head;
	private int[] tail;
	private ArrayList<int[]> coord;
	private ArrayList<int[]> zone;
	private String direction;
	private String position;
	
	public Ship(int deck, ArrayList<int[]> coord, ArrayList<int[]> zone, int direction, int size) {
		
		this.deck = deck;
		this.coord = coord;		
		this.zone = zone;
		
		if(direction ==1)
			this.direction = "vert";
		else 
			this.direction = "hor";
		
		head = coord.get(0);
		tail = coord.get(coord.size()-1);
		
		
		
		
	}
	
	
	public void setCoord (ArrayList<int[]> coord) {
		
	}
	
	public void setZone (ArrayList<int[]> zone) {
		
	}
	
	public void setDeck (int deck) {
		
	}
	
	
}
