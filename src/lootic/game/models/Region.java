package lootic.game.models;

import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Positioned;
import lootic.game.interfaces.Sized;

public class Region implements Sized, Positioned {

	protected Collidable owner;
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public Region(int x, int y, int width, int height) {
		this.width = width / 2;
		this.height = height / 2;
		this.x = x + this.width;
		this.y = y + this.height;
	}

	public Region(int x, int y, int width, int height, Collidable owner) {
		this(x, y, width, height);
		this.owner = owner;
	}

	public int getCenterX() {
		if (owner == null) {
			return x;
		} else {
			return owner.getX() + x;
		}
	}

	public int getCenterY() {
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

	public boolean isInside(Region otherRegion) {
		return false;
	}

	public boolean isNorthOf(Region other) {
		return this.getY() < other.getY();
	}

	public boolean isSouthOf(Region other) {
		return this.getCenterY() + this.getHeight() > other.getCenterY()
				+ other.getHeight();
	}

	public boolean isWestOf(Region other) {
		return this.getX() < other.getX();
	}

	public boolean isEastOf(Region other) {
		return this.getCenterX() + this.getWidth() > other.getCenterX()
				+ other.getWidth();
	}

	public void setOwner(Collidable owner) {
		this.owner = owner;
	}

	public boolean intersects(Region otherRegion) {
		return intersectsSquare(otherRegion);
	}

	private boolean intersectsSquare(Region otherRegion) {
		int vectorX = otherRegion.getCenterX() - this.getCenterX();
		int vectorY = otherRegion.getCenterY() - this.getCenterY();

		return (this.width + otherRegion.width > Math.abs(vectorX) && this.height
				+ otherRegion.height > Math.abs(vectorY));
	}

	/**
	 * Calculates the distance to the closest walls between this region and the
	 * region we want to compare with. If they are intersecting a negative value
	 * is returned;
	 * 
	 * @param otherRegion
	 *            the region to compare with.
	 * @return the distance in pixels between the walls.
	 */
	public int distance(Region otherRegion) {
		int xDistance = Math.abs(this.getCenterX() - otherRegion.getCenterX())
				- (this.width + otherRegion.width);
		int yDistance = Math.abs(this.getCenterY() - otherRegion.getCenterY())
				- (this.height + otherRegion.height);

		return xDistance > yDistance ? xDistance : yDistance;
	}
}
