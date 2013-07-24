package lootic.game.interfaces;

import lootic.game.controllers.Actions;

public interface Controllable {
	
	public void inputPressed(Actions action);
	public void inputReleased(Actions action);
}
