import java.awt.Image;


public class Decoration implements Positioned, Drawable{
	protected int x;
	protected int y;
	protected Image sprite; 

	@Override
	public Image getSprite() {
		//TODO
		return sprite;
	}

	@Override
	public void setSprite(Image sprite) {
		//TODO
		this.sprite = sprite;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getPrevY() { //why?
		return y;
	}

	@Override
	public int getPrevX() { //why?
		return x;
	}

}
