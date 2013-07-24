package lootic.game.interfaces;

import lootic.game.interfaces.CollisionRules.DynamicCollisionRule;
import lootic.game.models.Region;

public interface Movable extends Positioned, Collidable {
	public void updatePosition();

	public void moveX(int amount);
	
	public void moveDecX(int amount);

	public void moveY(int amount);
	
	public void moveDecY(int amount);

	public void setMovementSpeed(int speed);

	public void setFallSpeed(int speed);

	public int getMovementSpeed();

	public int getFallSpeed();

	public void onCollision(Collidable collidable, Region dynamicColliderRegion, Region staticColliderRegion);

	public void addCollisionRule(DynamicCollisionRule collisionRule);

	public void setOnGround(boolean b);
	
	public boolean isOnGround();
	
	public void setBumpingLeft(boolean b);
	
	public void setBumpingRight(boolean b);
	
	public boolean isBumpingLeft();
	
	public boolean isBumpingRight();
}