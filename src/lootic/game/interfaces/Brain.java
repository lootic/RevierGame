package lootic.game.interfaces;


/**
 * Controls a part of the game, eithere via AI or via input or combination of
 * both.
 * @author lootic
 *
 */
public interface Brain<Controlled> {
	
	public void setControlled(Controlled controlled);
	
	public void makeDecision();
}
