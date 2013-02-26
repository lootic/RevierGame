package lootic.game.models;

import lootic.game.interfaces.Positioned;
import lootic.game.interfaces.Sized;

public class Region implements Sized, Positioned {
	private Positioned owner;
	private int x;
	private int y;
	private int width;
	private int height;

	public Region(int x, int y, int width, int height) {
		this.width = width / 2;
		this.height = height / 2;
		this.x = x + this.width;
		this.y = y + this.height;
	}

	public Region(int x, int y, int width, int height, Positioned owner) {
		this(x, y, width, height);
		this.owner = owner;
	}

	private int getCenterX() {
		if (owner == null) {
			return x;
		} else {
			return owner.getX() + x;
		}
	}

	private int getCenterY() {
		if (owner == null) {
			return y;
		} else {
			return owner.getY() + y;
		}
	}

	@Override
	public int getHeight() {
		return height * 2;
	}

	public int getPrevX() {
		if (owner != null) {
			return owner.getPrevX() + x - width;
		} else {
			return x - width;
		}
	}

	public int getPrevY() {
		if (owner != null) {
			return owner.getPrevY() + y - height;
		} else {
			return y - height;
		}
	}

	@Override
	public int getWidth() {
		return width * 2;
	}

	@Override
	public int getX() {
		if (owner == null) {
			return x - width;
		} else {
			return owner.getX() + x - width;
		}
	}

	@Override
	public int getY() {
		if (owner == null) {
			return y - height;
		} else {
			return owner.getY() + y - height;
		}
	}

	public boolean intersects(Region otherRegion) {
		int vectorX = otherRegion.getCenterX() - this.getCenterX();
		int vectorY = otherRegion.getCenterY() - this.getCenterY();

		return (this.width + otherRegion.width > Math.abs(vectorX) && this.height
				+ otherRegion.height > Math.abs(vectorY));
	}

	public boolean isAbove(Region otherRegion) {
		return this.getPrevY() + this.getHeight() - 1 < otherRegion.getPrevY();
	}

	public boolean isBelow(Region otherRegion) {
		return  this.getPrevY() > otherRegion.getPrevY() + otherRegion.getHeight() -1;
	}

	public boolean isLeftOf(Region otherRegion) {
		return this.getPrevX() + getWidth() - 1 < otherRegion.getPrevX();
	}

	public boolean isRightOf(Region otherRegion) {
		return this.getPrevX() > otherRegion.getPrevX()
				+ otherRegion.getWidth() - 1;
	}

	public void setOwner(Positioned owner) {
		this.owner = owner;
	}
}
