public interface Collidable extends Positioned {
	public void addCollisionBox(Region r);
	public Iterable<Region> getCollisionBoxes();
}
