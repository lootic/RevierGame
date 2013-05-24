package lootic.game.models;

import lootic.game.interfaces.DecisionRules.DecisionRule;
import lootic.game.interfaces.Intelligent;

public class Enemy extends Creature implements Intelligent{

	@Override
	public void addDecisionRule(DecisionRule decisionRule) {		
	}

	@Override
	public void makeDecision() {
	}

}
