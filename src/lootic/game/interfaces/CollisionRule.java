package lootic.game.interfaces;

import java.util.ArrayList;

import lootic.game.models.Region;

public abstract class CollisionRule<Affector extends Collidable, Affected extends Collidable>
		extends SimpleRule<Affected> {

	protected ArrayList<Affector> affectors = new ArrayList<Affector>();

	public void onTick() {
		for (Affector affector : affectors) {
			for (Affected affected : affecteds) {
				if (affector.equals(affected))
					continue;
				for (Region affectorRegion : affector.getCollisionBoxes()) {
					for (Region affectedRegion : affected.getCollisionBoxes()) {
						if (affectorRegion.intersects(affectedRegion)) {
							onCollision(affector, affected, affectorRegion,
									affectedRegion);
						}
					}
				}
			}
		}
	}

	protected abstract void onCollision(Affector affector, Affected affected,
			Region affectorRegion, Region affectedRegion);

	public void addAffector(Affector affector) {
		this.affectors.add(affector);
	}

	public void removeAffector(Affector affected) {
		this.affectors.remove(affected);
	}
}