package lootic.game.interfaces;

import lootic.game.interfaces.DecisionRules.DecisionRule;

public interface Intelligent {
	public void addDecisionRule(DecisionRule decisionRule);
	public void makeDecision();
}
