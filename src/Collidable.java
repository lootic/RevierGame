public interface Collidable extends Positioned {
	public void addCollisionBox(Region<? extends Collidable> r);
	public Iterable<Region<? extends Collidable>> getCollisionBoxes();
}
