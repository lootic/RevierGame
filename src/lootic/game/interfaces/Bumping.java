package lootic.game.interfaces;

import lootic.game.interfaces.CollisionRules.DynamicCollisionRule;
import lootic.game.models.Region;

public interface Bumping extends Movable{

	public void onCollision(Collidable collidable, Region dynamicColliderRegion, Region staticColliderRegion);

	public void addCollisionRule(DynamicCollisionRule collisionRule);

	public void setOnGround(boolean b);
	
	public boolean isOnGround();
	
	public void setBumpingLeft(boolean b);
	
	public void setBumpingRight(boolean b);
	
	public boolean isBumpingLeft();
	
	public boolean isBumpingRight();
}
