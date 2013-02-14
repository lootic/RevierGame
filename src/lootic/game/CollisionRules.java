package lootic.game;

import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Movable;

public class CollisionRules {
	public interface StaticCollisionRule {
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion);
	};

	public interface DynamicCollisionRule {
		public void onCollision(Movable movableA, Movable movableB,
				Region collidablesRegion, Region movablesRegion);
	};

	public static final StaticCollisionRule SOLID = new StaticCollisionRule() {

		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {

			if (movablesRegion.isAbove(collidablesRegion)) {
				movable.moveY(collidablesRegion.getY() - movablesRegion.getY()
						- movablesRegion.getHeight());
			} else if (movablesRegion.isLeftOf(collidablesRegion)) {
				movable.moveX(collidablesRegion.getX() - movablesRegion.getX()
						- movablesRegion.getWidth());
			} else if (movablesRegion.isRightOf(collidablesRegion)) {
				movable.moveX(collidablesRegion.getX()
						+ collidablesRegion.getWidth() - movablesRegion.getX());
			} else if (movablesRegion.isBelow(collidablesRegion)) {
				movable.moveY(movablesRegion.getY() - collidablesRegion.getY()
						- collidablesRegion.getHeight());
			}
		}
	};

	public static final StaticCollisionRule FRICTION_ICE = new StaticCollisionRule() {
		
		private static final int friction = 30;

		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			if (movablesRegion.isAbove(collidablesRegion)) {
				if (movable.getMovementSpeed() < 0) {
					movable.setMovementSpeed(movable.getMovementSpeed()
							+ friction);
					if (movable.getMovementSpeed() > 0) {
						movable.setMovementSpeed(0);
					}
				} else {

					movable.setMovementSpeed(movable.getMovementSpeed()
							- friction);
					if (movable.getMovementSpeed() < 0) {
						movable.setMovementSpeed(0);
					}
				}
			}
		}
	};
}