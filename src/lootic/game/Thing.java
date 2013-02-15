package lootic.game;

import java.util.ArrayList;

import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Movable;
import lootic.game.interfaces.Weighing;
import lootic.game.interfaces.CollisionRules.DynamicCollisionRule;
import lootic.game.interfaces.CollisionRules.StaticCollisionRule;

public class Thing extends Terrain implements Weighing, Movable {

	private ArrayList<DynamicCollisionRule> dynamicCollisionRules = new ArrayList<DynamicCollisionRule>();
	protected float verticalForce;
	protected int maxFallSpeed = 10000;
	protected int fallSpeed;
	protected int weight = 50;
	protected int maxMovementSpeed = 5000;
	protected int movementSpeed;
	protected int movementSpeedAcceleration = 500;
	protected int prevX;
	protected int prevY;
	protected int decX;
	protected int decY;

	@Override
	public int getPrevX() {
		return prevX;
	}

	@Override
	public int getPrevY() {
		return prevY;
	}

	@Override
	public int getFallSpeed() {
		return fallSpeed;
	}

	@Override
	public int getMovementSpeed() {
		return movementSpeed;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void increaseFallSpeed(int increase) {
		if (fallSpeed == maxFallSpeed) {
			return;
		} else if (fallSpeed + increase > maxFallSpeed) {
			fallSpeed = maxFallSpeed;
		} else {
			fallSpeed += increase;
		}
	}

	@Override
	public void moveX(int amount) {
		System.out.println(amount);
		x += amount;
	}

	@Override
	public void moveY(int amount) {
		y += amount;
	}
	
	@Override
	public void setFallSpeed(int speed) {
		this.fallSpeed = speed;
	}

	@Override
	public void setMovementSpeed(int speed) {
		this.movementSpeed = speed;
	}

	@Override
	public void updatePosition() {
		movementSpeed += verticalForce * movementSpeedAcceleration;
		if (Math.abs(movementSpeed) > maxMovementSpeed)
			movementSpeed = (int) (verticalForce * maxMovementSpeed);
		decX += movementSpeed;
		decY += fallSpeed;
		if (Math.abs(decX) >= 1000 || Math.abs(decY) >= 1000) {
			prevX = x;
			prevY = y;
			x += decX / 1000;
			y += decY / 1000;
			decX %= 1000;
			decY %= 1000;
		}
	}

	@Override
	public void onCollision(Movable movable, Region myRegion, Region otherRegion) {
		for(DynamicCollisionRule collisionRule : dynamicCollisionRules) {
			collisionRule.onCollision(this, movable, myRegion, otherRegion);
		}
	}

	@Override
	public void addCollisionRule(DynamicCollisionRule collisionRule) {
		dynamicCollisionRules.add(collisionRule);
	}

	@Override
	public void onCollision(Collidable collidable, Region myRegion,
			Region otherRegion) {
		for(StaticCollisionRule collisionRule : staticCollisionRules) {
			collisionRule.onCollision(collidable, this, myRegion, otherRegion);
		}
		
	}
}
