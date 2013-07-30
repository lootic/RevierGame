package lootic.game.models;

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
	protected boolean isOnGround;
	protected boolean prevWasSetOnGround;
	protected boolean isBumpingLeft;
	protected boolean isBumpingRight;

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

		// make x and y additions based on movement constants for this thing
		movementSpeed += verticalForce * movementSpeedAcceleration;
		if (movementSpeed > maxMovementSpeed) {
			movementSpeed = (int) (maxMovementSpeed);
		} else if (movementSpeed < -maxMovementSpeed) {
			movementSpeed = (int) -(maxMovementSpeed);
		}
		decX += movementSpeed;
		decY += fallSpeed;

		// resolve actual movements in x and y if they are visible and not just
		// fractions of a pixel
		if (Math.abs(decX) >= 1000 || Math.abs(decY) >= 1000) {
			prevX = x;
			prevY = y;
			x += decX / 1000;
			y += decY / 1000;
			decX %= 1000;
			decY %= 1000;
		}

		checkIfAirborn();
		isBumpingLeft=false;
		isBumpingRight=false;
	}

	private void checkIfAirborn() {
		if (!prevWasSetOnGround) {
			isOnGround = false;
		}

		prevWasSetOnGround = false;
	}

	@Override
	public void onCollision(Movable movable, Region myRegion, Region otherRegion) {
		for (DynamicCollisionRule collisionRule : dynamicCollisionRules) {
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
		for (StaticCollisionRule collisionRule : staticCollisionRules) {
			collisionRule.onCollision(collidable, this, otherRegion, myRegion);
		}

	}

	@Override
	public void setOnGround(boolean b) {
		prevWasSetOnGround = b;
		isOnGround = b;
	}

	@Override
	public boolean isOnGround() {
		return isOnGround;
	}

	@Override
	public void moveDecX(int amount) {
		this.decX += amount;
	}

	@Override
	public void moveDecY(int amount) {
		this.decY += amount;	
	}

	@Override
	public void setBumpingLeft(boolean b) {
		this.isBumpingLeft = b;
	}

	@Override
	public void setBumpingRight(boolean b) {
		this.isBumpingRight = b;
	}

	@Override
	public boolean isBumpingLeft() {
		return isBumpingLeft;
	}

	@Override
	public boolean isBumpingRight() {
		return isBumpingRight;
	}
}
