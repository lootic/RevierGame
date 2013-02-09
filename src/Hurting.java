
public interface Hurting {
	public Iterable<Region> getHitBoxes();
	public void addHitBox(Region hurtBox);
	public void onHit(Hurtable hurter);
}
