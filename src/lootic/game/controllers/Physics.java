package lootic.game.controllers;

import java.util.HashSet;

import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.CollisionRule;
import lootic.game.interfaces.Looping;
import lootic.game.interfaces.Movable;
import lootic.game.interfaces.SimpleRule;
import lootic.game.models.Region;

public class Physics implements Looping {
	@SuppressWarnings("rawtypes")
	private HashSet<SimpleRule> physicsRules = new HashSet<SimpleRule>();
	private boolean isPaused;

	public Physics() {
		registerPhysicsRule(SOLID);
	}

	public void registerPhysicsRule(
			CollisionRule<? extends Collidable, ? extends Collidable> rule) {
		physicsRules.add(rule);
	}

	public void registerPhysicsRule(SimpleRule<?> rule) {
		physicsRules.add(rule);
	}

	public void unregisterPhysicsRule(SimpleRule<?> rule) {
		physicsRules.remove(rule);
	}

	public void unregisterPhysicsRule(
			CollisionRule<? extends Collidable, ? extends Collidable> rule) {
		physicsRules.remove(rule);
	}

	public static final CollisionRule<Collidable, Collidable> DAMAGE = new CollisionRule<Collidable, Collidable>() {

		@Override
		protected void onCollision(Collidable affector, Collidable affected,
				Region affectorRegion, Region affectedRegion) {
		}
	};

	public static final CollisionRule<Collidable, Movable> SOLID = new CollisionRule<Collidable, Movable>() {

		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			if (movablesRegion.isNorthOf(collidablesRegion)) {
				movable.setVerticalSpeed(0f);
				movable.moveY(collidablesRegion.distance(movablesRegion));
			} else if (movablesRegion.isWestOf(collidablesRegion)) {
				movable.setHorizontalSpeed(0f);
				movable.moveX(movablesRegion.distance(collidablesRegion));
			} else if (movablesRegion.isEastOf(collidablesRegion)) {
				movable.setHorizontalSpeed(0f);
				movable.moveX(-movablesRegion.distance(collidablesRegion));
			} else if (movablesRegion.isSouthOf(collidablesRegion)) {
				movable.setVerticalSpeed(0f);
				movable.moveY(movable.getHorizontalSpeed());
			}
		}
	};

	public static final SimpleRule<Movable> GLOBAL_GRAVITY = new SimpleRule<Movable>() {

		private float gravity = 1f;

		@Override
		public void onTick() {
			for (Movable m : affecteds) {
				m.setVerticalSpeed(m.getVerticalSpeed() + gravity);
			}
		}

		public void setGravity(float amount) {
			this.gravity = amount;
		}
	};

	public static final SimpleRule<Movable> MOVING = new SimpleRule<Movable>() {

		@Override
		public void onTick() {
			for (Movable m : affecteds) {
				m.setHorizontalSpeed(m.getHorizontalSpeed()
						+ m.getHorizontalAcceleration());
				m.setVerticalSpeed(m.getVerticalSpeed()
						+ m.getVerticalAcceleration());

				m.moveX(m.getHorizontalSpeed());
				m.moveY(m.getVerticalSpeed());
			}
		}
	};

	@Override
	public void onTick() {
		if (!isPaused) {
			for (SimpleRule<?> rule : physicsRules) {
				rule.onTick();
			}
		}
	}

	@Override
	public boolean isPaused() {
		return isPaused;
	}

	@Override
	public void setPaused(boolean paused) {
		isPaused = paused;
	}
}
