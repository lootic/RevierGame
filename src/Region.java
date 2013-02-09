public class Region implements Sized, Positioned {
	private Positioned owner;
	private int x;
	private int y;
	private int width;
	private int height;

	/*
	 * public static final CollisionAction<Movable, Positioned> MOVE_BACK = new
	 * CollisionAction<Movable, Positioned>() {
	 * 
	 * @Override public void collisionRoof(Region<Movable> thisRegion,
	 * Region<Positioned> otherRegion) {
	 * thisRegion.owner.moveY(otherRegion.getY() + otherRegion.getHeight() -
	 * thisRegion.getY()); }
	 * 
	 * @Override public void collisionRight(Region<Movable> thisRegion,
	 * Region<Positioned> otherRegion) {
	 * thisRegion.owner.moveX(otherRegion.getX() + otherRegion.getWidth() -
	 * thisRegion.getX()); }
	 * 
	 * @Override public void collisionLeft(Region<Movable> thisRegion,
	 * Region<Positioned> otherRegion) {
	 * thisRegion.owner.moveX(otherRegion.getX() - thisRegion.getX() -
	 * thisRegion.getWidth()); }
	 * 
	 * @Override public void collisionGround(Region<Movable> thisRegion,
	 * Region<Positioned> otherRegion) {
	 * thisRegion.owner.moveY(otherRegion.getY() - thisRegion.getY() -
	 * thisRegion.getHeight()); } };
	 */

	public Region(int x, int y, int width, int height) {
		this.width = width / 2;
		this.height = height / 2;
		this.x = x + this.width;
		this.y = y + this.height;
	}

	public Region(int x, int y, int width, int height, Positioned owner) {
		this(x, y, width, height);
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
		return this.getPrevY() + height < otherRegion.getPrevY()
				- otherRegion.height + 1;
	}

	public boolean isBelow(Region otherRegion) {
		return this.getPrevY() > otherRegion.getPrevY() + otherRegion.height
				+ 1;
	}

	public boolean isLeftOf(Region otherRegion) {
		return this.getPrevX() + width - 1 < otherRegion.getPrevX()
				- otherRegion.width;
	}

	public boolean isRightOf(Region otherRegion) {
		return this.getPrevX() > otherRegion.getPrevX() + otherRegion.width + 1;
	}

	public int getPrevX() {
		if (owner != null) {
			return owner.getPrevX() + this.x;
		} else {
			return x;
		}
	}

	public int getPrevY() {
		if (owner != null) {
			return owner.getPrevY() + this.y;
		} else {
			return y;
		}
	}

	public void setOwner(Positioned owner) {
		this.owner = owner;
	}
}
