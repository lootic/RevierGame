package lootic.game.interfaces;

import lootic.game.interfaces.Behaviours.Behaviour;
import lootic.game.models.Perception;

public interface Intelligent {
	public void addBehaviour(Behaviour decisionRule);
	public void makeDecision();
	public void setPerception(Perception perception);
}
