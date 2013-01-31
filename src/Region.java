public class Region {
	private Entity owner;
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

	public Region(int x, int y, int width, int height, Entity owner) {
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

	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public CollisionType intersects(Region otherRegion) {
		int vectorX = otherRegion.getCenterX() - this.getCenterX();
		int vectorY = otherRegion.getCenterY() - this.getCenterY();

		if (this.width + otherRegion.width > Math.abs(vectorX)
				&& this.height + otherRegion.height > Math.abs(vectorY)) {

			System.out.println("this.prevY=" + (prevY() + height)
					+ ", otherRegion.prevY=" + (otherRegion.prevY() - height));
			if ((this.prevY() + height) < otherRegion.prevY()
					- otherRegion.height+1)
				return CollisionType.TOP;
			else if (this.prevX() + width - 1 < otherRegion.prevX()
					- otherRegion.width)
				return CollisionType.LEFT;
			else if (this.prevX() + width > otherRegion.prevX()
					- otherRegion.width+1)
				return CollisionType.RIGHT;
			else
				return CollisionType.BOTTOM;

		}
		return CollisionType.NONE;
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

	public boolean hasSameOwner(Region r) {
		return this.owner == r.owner;
	}

	public void fireCollisionEvent(CollisionType ct) {
		owner.collisionEvent(ct);
	}
}
