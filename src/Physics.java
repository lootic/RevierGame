import java.util.ArrayList;

public class Physics {
	private float gravity = 9.82f;
	private boolean paused = true;

	private ArrayList<Creature> creatures = new ArrayList<Creature>();
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private ArrayList<Weighing> weighings = new ArrayList<Weighing>(); // yuck,
																		// bad
																		// name
	private ArrayList<Movable> movables = new ArrayList<Movable>();

	public void registerWeighing(Weighing w) {
		weighings.add(w);
	}

	public void registerCreature(Creature c) {
		creatures.add(c);
	}

	public void registerPlatform(Platform p) {
		platforms.add(p);
	}

	public void registerMovable(Movable m) {
		movables.add(m);
	}

	public void update() {
		if (!isPaused()) {
			applyMovements();
			applyGravity();
			checkTerrainCollisions();
		}
	}

	private void checkTerrainCollisions() {
		for (Platform platform : platforms) {
			for (Creature creature : creatures) {
				for (Region platformRegion : platform.getCollisionBoxes()) {
					for (Region creatureRegion : creature.getCollisionBoxes()) {
						if (creatureRegion.intersects(platformRegion)) {
							if (creatureRegion.isAbove(platformRegion)) {
								creature.moveY(platformRegion.getY()
										- creatureRegion.getY()
										- creatureRegion.getHeight());
								creature.groundCollision();
								creature.applyFriction(platform.getFriction());
							} else if (creatureRegion.isLeftOf(platformRegion)) {
								creature.moveX(platformRegion.getX()
										- creatureRegion.getX()
										- creatureRegion.getWidth());
								creature.wallCollision();
							}
						}
					}
				}
			}
		}
	}

	private void applyMovements() {
		for (Movable m : movables) {
			m.updatePosition();
		}
	}

	private void applyGravity() {
		for (Weighing w : weighings) {
			w.increaseFallSpeed((int) (gravity * w.getWeight()));
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
