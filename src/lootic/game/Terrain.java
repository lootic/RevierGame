package lootic.game;

import java.util.ArrayList;

import lootic.game.CollisionRules.StaticCollisionRule;
import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Movable;

public class Terrain extends Decoration implements Collidable {
	private ArrayList<Region> collisionBoxes = new ArrayList<Region>();
	protected ArrayList<StaticCollisionRule> staticCollisionRules = new ArrayList<StaticCollisionRule>();

	@Override
	public void addCollisionBox(Region r) {
		r.setOwner(this);
		collisionBoxes.add(r);
	}

	@Override
	public Iterable<Region> getCollisionBoxes() {
		return collisionBoxes;
	}

	@Override
	public void onCollision(Movable movable, Region myRegion, Region otherRegion) {
		for (StaticCollisionRule collisionRule : staticCollisionRules) {
			collisionRule.onCollision(this, movable, myRegion, otherRegion);
		}
	}

	@Override
	public void addCollisionRule(StaticCollisionRule collisionRule) {
		staticCollisionRules.add(collisionRule);
	}
}
