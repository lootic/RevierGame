package lootic.game;

import lootic.game.interfaces.Damaging;
import lootic.game.interfaces.Hurtable;

public class Creature extends Thing implements Hurtable {
	protected short up;
	protected short down;
	protected short left;
	protected short right;

//	public void applyFriction(int amount) {
//		if (movementSpeed < 0) {
//			movementSpeed += amount;
//			if (movementSpeed > 0) {
//				movementSpeed = 0;
//			}
//		} else {
//			movementSpeed -= amount;
//			if (movementSpeed < 0) {
//				movementSpeed = 0;
//			}
//		}
//	}

	@Override
	public Iterable<Region> getHurtBoxes() {
		return null;
	}


	@Override
	public void addHurtBox(Region hurtBox) {		
	}

	@Override
	public void onDamage(Damaging hurter) {		
	}
}
