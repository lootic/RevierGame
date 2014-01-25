package lootic.game.models;

import lootic.game.interfaces.Positioned;
import lootic.game.interfaces.Sized;

public class Camera implements Positioned {

	private int x;
	private int y;
	private Positioned focusedObject;
	private Sized observedArea;

	private int minX = Integer.MIN_VALUE;
	private int minY = Integer.MIN_VALUE;
	private int maxX = Integer.MAX_VALUE;
	private int maxY = Integer.MAX_VALUE;

	public void setBounds(int x, int y, int width, int height) {
		this.minX = x;
		this.maxX = x + width;
		this.minY = y;
		this.maxY = y + height;
	}

	public void setFocusedObject(Positioned focusedObject) {
		this.focusedObject = focusedObject;
	}

	public void setObservedArea(Sized observedArea) {
		this.observedArea = observedArea;
	}

	@Override
	public int getX() {
		if (focusedObject != null) {
			if (observedArea != null) {
				if (focusedObject.getX() - observedArea.getWidth() / 2 < minX) {
					return minX;
				} else if (focusedObject.getX() + observedArea.getWidth() / 2 > maxX) {
					return maxX - observedArea.getWidth();
				} else {
					return focusedObject.getX() - observedArea.getWidth() / 2;
				}
			} else {
				return focusedObject.getX();
			}
		}
		return x;
	}

	@Override
	public int getY() {
		if (focusedObject != null) {
			if (observedArea != null) {
				if (focusedObject.getY() - observedArea.getHeight() / 2 < minY) {
					return minY;
				} else if (focusedObject.getY() + observedArea.getHeight() / 2 > maxY) {
					return maxY - observedArea.getHeight();
				} else {
					return focusedObject.getY() - observedArea.getHeight() / 2;
				}
			} else {
					return focusedObject.getY();
				}
			}
		return y;
	}

	@Override
	public int getPrevY() {
		if (focusedObject != null) {
			if (observedArea != null) {
				return focusedObject.getY() - observedArea.getHeight() / 2;
			} else {
				return focusedObject.getY();
			}
		}
		return x;
	}

	@Override
	public int getPrevX() {
		if (focusedObject != null) {
			if (observedArea != null) {
				return focusedObject.getX() - observedArea.getWidth() / 2;
			} else {
				return focusedObject.getX();
			}
		}
		return y;
	}

}
