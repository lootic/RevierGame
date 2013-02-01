public class Region {
	private Creature owner;
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

	public boolean isAbove(Region otherRegion) {
		return this.prevY() + height < otherRegion.prevY()
				- otherRegion.height + 1;
	}

	public boolean isLeftOf(Region otherRegion) {
		return this.prevX() + width - 1 < otherRegion.prevX()
				- otherRegion.width;
	}

	public boolean isRightOf(Region otherRegion) {
		return this.prevX() > otherRegion.prevX() + otherRegion.width
				+ 1;
	}
	
	public boolean isBelow(Region otherRegion) {
		return this.prevY() > otherRegion.prevY() + otherRegion.height
				+ 1;
	}

	public Region(int x, int y, int width, int height, Creature owner) {
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

	public int getX() {
		if (owner == null) {
			return x - width;
		} else {
			return owner.getX() + x - width;
		}
	}

	public int getY() {
		if (owner == null) {
			return y - height;
		} else {
			return owner.getY() + y - height;
		}
	}

	public void setOwner(Creature owner) {
		this.owner = owner;
	}

	public boolean intersects(Region otherRegion) {
		int vectorX = otherRegion.getCenterX() - this.getCenterX();
		int vectorY = otherRegion.getCenterY() - this.getCenterY();

		return (this.width + otherRegion.width > Math.abs(vectorX)
				&& this.height + otherRegion.height > Math.abs(vectorY));
	}

	private int prevX() {

		if (owner != null) {
			return owner.getPrevX() + this.x;
		} else {
			return x;
		}
	}

	private int prevY() {
		if (owner != null) {
			return owner.getPrevY() + this.y;
		} else {
			return y;
		}
	}

	public int getWidth() {
		return width * 2;
	}

	public int getHeight() {
		return height * 2;
	}
}
