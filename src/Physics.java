import java.util.ArrayList;

public class Physics {
	private float gravity = 9.82f;
	private boolean paused = true;

	/**
	 * Colliding regions that collide with everything including among themselves.
	 */
	private ArrayList<Region> dynamicColliders = new ArrayList<Region>();
	
	/**
	 * Colliding regions that doesnt collide with each other but 
	 */
	private ArrayList<Region> staticColliders = new ArrayList<Region>();
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
		for (Region<Platform> platformRegion : staticColliders) {
			for (Region<Creature> creatureRegion : dynamicColliders) {
				if (creatureRegion.intersects(platformRegion)) {
					if (creatureRegion.isAbove(platformRegion)) {
						creatureRegion.getCollisionAction().collisionGround(
								creatureRegion, platformRegion);
						platformRegion.getCollisionAction().collisionRoof(
								platformRegion, creatureRegion);
					} else if (creatureRegion.isLeftOf(platformRegion)) {
						creatureRegion.getCollisionAction().collisionLeft(
								creatureRegion, platformRegion);
						platformRegion.getCollisionAction().collisionRight(
								platformRegion, creatureRegion);
					} else if (creatureRegion.isRightOf(platformRegion)) {
						creatureRegion.getCollisionAction().collisionRight(
								creatureRegion, platformRegion);
						platformRegion.getCollisionAction().collisionLeft(
								platformRegion, creatureRegion);
					} else if (creatureRegion.isBelow(platformRegion)) {
						creatureRegion.getCollisionAction().collisionRoof(
								creatureRegion, platformRegion);
						platformRegion.getCollisionAction().collisionGround(
								platformRegion, creatureRegion);
					}
				}
			}
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void registerCreature(Creature creature) {
		for(Region region : creature.getHurtBoxes()) {
			//lol
		}
		
		for(Region region : creature.getCollisionBoxes()) {
			registerDynamicCollider(region);
		}
		
		registerMovable(creature);
		registerWeighing(creature);
	}

	public void registerDynamicCollider(Region region) {
		dynamicColliders.add(region);
	}

	public void registerMovable(Movable m) {
		movables.add(m);
	}

	public void registerPlatform(Platform platform) {
		for(Region region : platform.getCollisionBoxes()) {
			registerStaticCollider(region);
		}
	}

	public void registerStaticCollider(Region region) {
		staticColliders.add(region);
	}

	public void registerWeighing(Weighing w) {
		weighings.add(w);
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public void update() {
		if (!isPaused()) {
			applyMovements();
			applyGravity();
			checkTerrainCollisions();
		}
	}
}
