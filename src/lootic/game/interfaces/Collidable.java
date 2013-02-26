package lootic.game.interfaces;

import lootic.game.interfaces.CollisionRules.StaticCollisionRule;
import lootic.game.models.Region;

public interface Collidable extends Positioned {
	public void addCollisionBox(Region region);

	public Iterable<Region> getCollisionBoxes();

	public void onCollision(Movable movable, Region myRegion, Region otherRegion);

	public void addCollisionRule(StaticCollisionRule collisionRule);
}
