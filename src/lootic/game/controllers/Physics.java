package lootic.game.controllers;

import java.util.ArrayList;

import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Looping;
import lootic.game.interfaces.Bumping;
import lootic.game.interfaces.Weighing;
import lootic.game.models.Region;
import lootic.game.models.Thing;

public class Physics implements Looping{
	private float gravity = 9.82f;
	private boolean isPaused = true;

	/**
	 * Colliding regions that collide with everything including among
	 * themselves.
	 */

	/**
	 * Colliding regions that doesn't collide with each other but with the movables
	 */
	private ArrayList<Collidable> collidables = new ArrayList<Collidable>();
	private ArrayList<Weighing> weighings = new ArrayList<Weighing>();
	
	/**
	 * colliding regions that collide with everything
	 */
	private ArrayList<Bumping> movables = new ArrayList<Bumping>();

	private void applyGravity() {
		for (Weighing w : weighings) {
			w.increaseFallSpeed((int) (gravity * w.getWeight()));
		}
	}
	
	/**Jump doesn't work properly with low values on gravity.
	 * @param gravity
	 */
	public void setGravity(float gravity){
		this.gravity = gravity;
	}

	private void applyMovements() {
		for (Bumping m : movables) {
			m.updatePosition();
		}
	}

	private void checkTerrainCollisions() {
		if(isPaused) {
			return;
		}
		for (Bumping dynamicCollider : movables) {
			for (Collidable staticCollider : collidables) {
				for (Region dynamicColliderRegion : dynamicCollider
						.getCollisionBoxes()) {
					for (Region staticColliderRegion : staticCollider
							.getCollisionBoxes()) {
						//threading from here
						if (staticColliderRegion
								.intersects(dynamicColliderRegion)) {
							staticCollider.onCollision(dynamicCollider, staticColliderRegion, dynamicColliderRegion);
							dynamicCollider.onCollision(staticCollider, dynamicColliderRegion, staticColliderRegion);
						}
						//to here?
					}
				}
			}
		}
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void registerThing(Thing thing) {
		registerMovable(thing);
		registerWeighing(thing);
	}

	public void registerMovable(Bumping m) {
		movables.add(m);
	}
	
	public void registerCollidable(Collidable collidable) {
		collidables.add(collidable);
	}

	public void registerWeighing(Weighing w) {
		weighings.add(w);
	}

	public void setPaused(boolean paused) {
		this.isPaused = paused;
	}

	public void nextIteration() {
		if (!isPaused()) {
			applyGravity();
			applyMovements();
			checkTerrainCollisions();
		}
	}

}