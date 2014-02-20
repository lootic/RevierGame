package lootic.game.models;
import java.awt.Image;

import lootic.game.interfaces.Drawable;
import lootic.game.interfaces.Positioned;


public class Decoration implements Positioned, Drawable{
	protected float x;
	protected float y;
	protected Image sprite; 

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public int getX() {
		return (int)x;
	}

	@Override
	public int getY() {
		return (int)y;
	}
}
