
public interface Hurtable {
	public Iterable<Region> getHurtBoxes();
	public void addHurtBox(Region hurtBox);
	public void onDamage(Hurting hurter);
}
