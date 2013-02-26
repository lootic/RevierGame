package lootic.game.interfaces;

import lootic.game.models.JumpState;
import lootic.game.models.Player;
import lootic.game.models.Region;

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
				movable.moveY(collidablesRegion.getY()
						+ collidablesRegion.getHeight() - movablesRegion.getY());
			}
		}
	};

	public static final StaticCollisionRule JUMP_PLAYER = new StaticCollisionRule() {
		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			Player player = (Player) movable;
			if (movablesRegion.isAbove(collidablesRegion)) {
				player.setJumpState(JumpState.CAN_JUMP);
			} else if (movablesRegion.isLeftOf(collidablesRegion)) {
				player.setJumpState(JumpState.CAN_LEFT_WALLJUMP);
			} else if (movablesRegion.isRightOf(collidablesRegion)) {
				player.setJumpState(JumpState.CAN_RIGHT_WALLJUMP);
			} else if(movablesRegion.isBelow(collidablesRegion)) {
			} else {
				player.setJumpState(JumpState.CAN_JUMP);
			}
		}
	};

	public static final StaticCollisionRule FALLSPEED_RESET = new StaticCollisionRule() {

		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			if (movablesRegion.isAbove(collidablesRegion)) {
				movable.setFallSpeed(0);
			}
		}
	};

	public static final StaticCollisionRule WALL_COLLISION_PLAYER = new StaticCollisionRule() {

		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			if (movablesRegion.isLeftOf(collidablesRegion)
					|| movablesRegion.isRightOf(collidablesRegion)) {
				movable.setFallSpeed(1000);
				//movable.setMovementSpeed(0);
			}
		}
	};

	public static final StaticCollisionRule FRICTION_ICE = new StaticCollisionRule() {

		private static final int friction = 100;

		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			applyFriction(collidable, movable, collidablesRegion,
					movablesRegion, friction);
		}
	};

	public static final StaticCollisionRule FRICTION_NORMAL = new StaticCollisionRule() {

		private static final int friction = 300;

		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			applyFriction(collidable, movable, collidablesRegion,
					movablesRegion, friction);
		}
	};
	
	public static final StaticCollisionRule WATER = new StaticCollisionRule() {
		
		@Override
		public void onCollision(Collidable collidable, Movable movable,
				Region collidablesRegion, Region movablesRegion) {
			
			movable.setFallSpeed((movable.getFallSpeed()- movable.getFallSpeed()/8));
			
			if(movable.getFallSpeed()> 1500){
				movable.setFallSpeed(1500);
			}
		}
	};

	private static void applyFriction(Collidable collidable, Movable movable,
			Region collidablesRegion, Region movablesRegion, int friction) {
		if (movable.getMovementSpeed() < 0) {
				movable.setMovementSpeed(movable.getMovementSpeed() + friction);
				if (movable.getMovementSpeed() > 0) {
					movable.setMovementSpeed(0);
				}
			} else {

				movable.setMovementSpeed(movable.getMovementSpeed() - friction);
				if (movable.getMovementSpeed() < 0) {
					movable.setMovementSpeed(0);
				}
			}
		
	}
}