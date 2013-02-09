import java.util.ArrayList;

public class Physics {
	private float gravity = 9.82f;
	private boolean isPaused = true;

	/**
	 * Colliding regions that collide with everything including among
	 * themselves.
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
		for (Region platformRegion : staticColliders) {
			for (Region creatureRegion : dynamicColliders) {
				if (creatureRegion.intersects(platformRegion)) {
				}
			}
		}
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void registerCreature(Creature creature) {
		for (Region region : creature.getCollisionBoxes()) {
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
		for (Region region : platform.getCollisionBoxes()) {
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
		this.isPaused = paused;
	}

	public void update() {
		if (!isPaused()) {
			applyMovements();
			applyGravity();
			checkTerrainCollisions();
		}
	}
}
