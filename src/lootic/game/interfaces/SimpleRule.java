package lootic.game.interfaces;

import java.util.ArrayList;

public abstract class SimpleRule<Affected> implements Looping{
	protected ArrayList<Affected> affecteds = new ArrayList<Affected>();
	protected boolean isPaused;

	@Override
	public abstract void onTick();

	@Override
	public boolean isPaused() {
		return false;
	}

	@Override
	public void setPaused(boolean paused) {
		this.isPaused = paused;
	}
	
	public void addAffected(Affected affected) {
		this.affecteds.add(affected);
	}

	public void removeAffected(Affected affected) {
		this.affecteds.remove(affected);
	}
}
