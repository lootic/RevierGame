package lootic.game;
import java.awt.Image;

import lootic.game.interfaces.Drawable;
import lootic.game.interfaces.Positioned;


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
	public int getPrevY() {
		return y;
	}

	@Override
	public int getPrevX() {
		return x;
	}

}
