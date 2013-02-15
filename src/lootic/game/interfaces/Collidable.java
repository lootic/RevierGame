package lootic.game.interfaces;

import lootic.game.Region;
import lootic.game.interfaces.CollisionRules.StaticCollisionRule;

public interface Collidable extends Positioned {
	public void addCollisionBox(Region region);

	public Iterable<Region> getCollisionBoxes();

	public void onCollision(Movable movable, Region myRegion, Region otherRegion);

	public void addCollisionRule(StaticCollisionRule collisionRule);
}
