public interface Collidable extends Positioned {
	public void addCollisionBox(Region region);
	public Iterable<Region> getCollisionBoxes();
	public void onCollision(Movable movable);
}
