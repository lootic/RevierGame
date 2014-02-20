package lootic.game.controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Drawable;
import lootic.game.interfaces.Looping;
import lootic.game.interfaces.Sized;
import lootic.game.models.Camera;
import lootic.game.models.Region;

public class Canvas extends JPanel implements Sized, Looping {
	private static final long serialVersionUID = 1L;
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private boolean isDebugMode;
	private boolean isPaused;
	private Camera camera;

	public Canvas() {
	}

	public void registerDrawable(Drawable d) {
		drawables.add(d);
	}

	public void onTick() {
		if (!isPaused()) {
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// make canvas empty
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		// redraw everything
		for (Drawable d : drawables) {
			g2d.drawImage(d.getSprite(), d.getX() - camera.getX(), d.getY()
					- camera.getY(), this);

			// since the following is for debugging, we allow ourselves to use
			// instanceof even though it costs cpu-cycles, at least its better
			// than exceptions. :)
			if (isDebugMode() && d instanceof Collidable) {
				Collidable c = (Collidable) d;
				for (Region r : c.getCollisionBoxes()) {
					g2d.setColor(Color.YELLOW);
					g2d.drawRect((int) r.getX() - camera.getX(), (int) r.getY()
							- camera.getY(), r.getWidth(), r.getHeight());
				}
			}
			if (isDebugMode() //&& d instanceof Damaging
					) {
//				Damaging dam = (Damaging) d;
//
//				if (dam.getHitBoxes() != null) {
//					for (Region r : dam.getHitBoxes()) {
//						g2d.setColor(Color.RED);
//						g2d.drawRect((int) r.getX() - camera.getX(),
//								(int) r.getY() - camera.getY(), r.getWidth(),
//								r.getHeight());
//					}
//				}
			}
		}

	}

	public boolean isDebugMode() {
		return isDebugMode;
	}

	/**
	 * Debugging tool, sets that all the boxes that determines collision should
	 * be drawn.
	 * 
	 * @param isDebugMode
	 */
	public void setDebug(boolean isDebugMode) {
		this.isDebugMode = isDebugMode;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean paused) {
		isPaused = paused;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
		camera.setObservedArea(this);
	}
}
