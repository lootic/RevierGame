package lootic.game.controllers;

import java.util.ArrayList;

import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Looping;
import lootic.game.interfaces.Movable;
import lootic.game.interfaces.Weighing;
import lootic.game.models.Region;
import lootic.game.models.Creature;

public class Physics implements Looping{
	private float gravity = 9.82f;
	private boolean isPaused = true;

	/**
	 * Colliding regions that collide with everything including among
	 * themselves.
	 */

	/**
	 * Colliding regions that doesnt collide with each other but
	 */
	private ArrayList<Collidable> collidables = new ArrayList<Collidable>();
	private ArrayList<Weighing> weighings = new ArrayList<Weighing>();
	private ArrayList<Movable> movables = new ArrayList<Movable>();

	private void applyGravity() {
		for (Weighing w : weighings) {
			w.increaseFallSpeed((int) (gravity * w.getWeight()));
		}
	}

	private void applyMovements() {
		for (Movable m : movables) {
			m.updatePosition();
		}
	}

	private void checkTerrainCollisions() {
		for (Movable dynamicCollider : movables) {
			for (Collidable staticCollider : collidables) {
				for (Region dynamicColliderRegion : dynamicCollider
						.getCollisionBoxes()) {
					for (Region staticColliderRegion : staticCollider
							.getCollisionBoxes()) {
						if (staticColliderRegion
								.intersects(dynamicColliderRegion)) {
							staticCollider.onCollision(dynamicCollider, staticColliderRegion, dynamicColliderRegion);
							dynamicCollider.onCollision(staticCollider, dynamicColliderRegion, staticColliderRegion);
						}
					}
				}
			}
		}
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void registerCreature(Creature creature) {
		registerMovable(creature);
		registerWeighing(creature);
	}

	public void registerMovable(Movable m) {
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
			applyMovements();
			applyGravity();
			checkTerrainCollisions();
		}
	}

}