package lootic.game.interfaces;

public interface Looping {
	public void onTick();
	public boolean isPaused();
	public void setPaused(boolean paused);
}
