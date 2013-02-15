package lootic.game.interfaces;

import lootic.game.Region;
import lootic.game.interfaces.CollisionRules.DynamicCollisionRule;

public interface Movable extends Positioned, Collidable {
	public void updatePosition();

	public void moveX(int amount);

	public void moveY(int amount);

	public void setMovementSpeed(int speed);

	public void setFallSpeed(int speed);

	public int getMovementSpeed();

	public int getFallSpeed();

	public void onCollision(Collidable collidable, Region myRegion, Region otherRegion);

	public void addCollisionRule(DynamicCollisionRule collisionRule);
}