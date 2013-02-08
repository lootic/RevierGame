public interface CollisionAction<A extends Positioned, B extends Positioned> {
	public void onCollision(Region<A> thisRegion, Region<B> otherRegion);
}
