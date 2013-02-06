
public interface Destructable {
	public void damage(int amount);
	public Iterable<Region> getHurtBoxes();
	public void addHurtBox(Region hurtBox);
}
