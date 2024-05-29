package summer;

import java.util.*;

public class Ship {

	private int deck;
	private ArrayList<Integer[]> coord;
	private String name;
	ArrayList<Integer[]> zone;
	
	public Ship(int deck, ArrayList<Integer[]> coord, ArrayList<Integer[]> zone, String name ) {
		
		this.deck = deck;
		this.coord = coord;
		this.name = name;
		this.zone = zone;
	}
	
	
	public void setName (String name) {
		this.name = name;
	}
	
	
	
}
