package lootic.game.interfaces;

import lootic.game.Region;

public interface Hurtable {
	public Iterable<Region> getHurtBoxes();
	public void addHurtBox(Region hurtBox);
	public void onDamage(Damaging hurter);
}
