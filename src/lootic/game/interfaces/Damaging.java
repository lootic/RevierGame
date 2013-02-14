package lootic.game.interfaces;

import lootic.game.Region;

public interface Damaging {
	public Iterable<Region> getHitBoxes();
	public void addHitBox(Region hurtBox);
	public void onHit(Hurtable hurter);
}
