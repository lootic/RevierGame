public interface CollisionAction<OwnerTypeA extends Positioned, OwnerTypeB extends Positioned> {
	public void collisionGround(Region<OwnerTypeA> thisRegion,
			Region<OwnerTypeB> otherRegion);

	public void collisionRoof(Region<OwnerTypeA> thisRegion,
			Region<OwnerTypeB> otherRegion);

	public void collisionLeft(Region<OwnerTypeA> thisRegion,
			Region<OwnerTypeB> otherRegion);

	public void collisionRight(Region<OwnerTypeA> thisRegion,
			Region<OwnerTypeB> otherRegion);
}
