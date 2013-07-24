package lootic.game.interfaces;

public class DecisionRules {
	public interface DecisionRule {
		public int will(/*senses*/);
		public void action();
	}
}
