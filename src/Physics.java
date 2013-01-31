import java.util.ArrayList;

public class Physics {
	private float gravity = 9.82f;
	private boolean paused = true;

	private ArrayList<Region> regions = new ArrayList<Region>();
	private ArrayList<Weighing> weighings = new ArrayList<Weighing>(); // yuck, bad name
	private ArrayList<Movable> movables = new ArrayList<Movable>();

	public void registerWeighing(Weighing w) {
		weighings.add(w);
	}

	public void registerCollidable(Region r) {
		regions.add(r);
	}

	public void registerMovable(Movable m) {
		movables.add(m);
	}

	public void update() {
		if (!isPaused()) {
			applyMovements();
			applyGravity();
			checkCollisions();
		}
	}

	private void checkCollisions() {
		for (Region r1 : regions) {
			for (Region r2 : regions) {
				if (r1.hasSameOwner(r2)) {
					continue;
				} else {
					r1.fireCollisionEvent(r1.intersects(r2));
					r2.fireCollisionEvent(r2.intersects(r1));
				}
			}
		}
		System.out.println();
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
