package lootic.game.models;

import java.util.ArrayList;

import lootic.game.interfaces.Percieveble;

public class Perception {
	private ArrayList<Percieveble> percievebles = new ArrayList<Percieveble>();

	public ArrayList<Percieveble> getPercievebles() {
		return percievebles;
	}

	public void setPercievebles(ArrayList<Percieveble> percievebles) {
		this.percievebles = percievebles;
	}
}
