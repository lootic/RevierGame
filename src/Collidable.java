public interface Collidable<T extends Collidable<?>> extends Positioned {
	public void addCollisionBox(Region<T> region);
	public Iterable<Region<T>> getCollisionBoxes();
}
