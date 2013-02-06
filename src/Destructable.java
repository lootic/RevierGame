
public interface Destructable {
	public Iterable<Region> getHurtBoxes();
	public void addHurtBox(Region hurtBox);
	void destroy();
}
