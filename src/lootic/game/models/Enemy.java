package lootic.game.models;

import java.util.ArrayList;

import lootic.game.interfaces.Behaviours.Behaviour;
import lootic.game.interfaces.Intelligent;

public class Enemy extends Creature implements Intelligent{

	private ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();
	private Perception perception;
	
	@Override
	public void addBehaviour(Behaviour behaviour) {		
		behaviours.add(behaviour);
	}

	@Override
	public void makeDecision() {
		int will, highestWill = -1;
		Behaviour selectedBehaviour = null;
		for(Behaviour behaviour : behaviours){
			will = behaviour.will(perception);
			if(will > highestWill) {
				selectedBehaviour = behaviour;
				highestWill = will;
			}
		}
		selectedBehaviour.action(this);
	}

	@Override
	public void setPerception(Perception perception) {
		this.perception = perception;
	}
}
