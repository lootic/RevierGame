package lootic.game.interfaces;

import lootic.game.models.Perception;

public class Behaviours {
	public interface Behaviour {
		public int will(Perception perception);
		public void action(Intelligent intelligent);
	}
}
