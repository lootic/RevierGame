package lootic.game.models;

import java.util.ArrayList;



public class Damage extends Terrain {
	private int amount;
	private Body source;
	private int timeToLive;
	
	public Damage(int amount, Body source) {
		
	}
	
	public int getAmount() {
		return amount;
	}

	public Body getSource() {
		return source;
	}
	
	public void addHitbox(Region region) {
		
	}
	
	public ArrayList<Region> getHitboxes(){
		return null;
	}

	public int getTimeToLive() {
		return timeToLive;
	}
}
