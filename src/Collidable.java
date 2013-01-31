public interface Collidable {
	public CollisionType collides(Collidable c);
	public void addCollisionBox(Region r);
	public Region[] getCollisionBoxes();
	public void onCollision(Collidable c2);
}
