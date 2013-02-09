import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private boolean isDrawingRegions;

	public Canvas() {
	}

	public void registerDrawable(Drawable d) {
		drawables.add(d);
	}

	public void update() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// make canvas empty
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		// redraw everything
		for (Drawable d : drawables) {
			g2d.drawImage(d.getSprite(), d.getX(), d.getY(), null);
			if (isDrawingRegions() && d instanceof Collidable) {
				Collidable c = (Collidable) d;
				for (Region r : c.getCollisionBoxes()) {
					g2d.setColor(Color.YELLOW);
					g2d.drawRect((int) r.getX(), (int) r.getY(), r.getWidth(),
							r.getHeight());
				}
			}
//			if (d instanceof Destructable) {
//				Destructable dest = (Destructable) d;
//				for (Region r : dest.getHurtBoxes()) {
//					g2d.setColor(Color.RED);
//					g2d.drawRect((int) r.getX(), (int) r.getY(), r.getWidth(),
//							r.getHeight());
//				}
//			}
		}

	}

	public boolean isDrawingRegions() {
		return isDrawingRegions;
	}

	/**
	 * Debugging tool, sets that all the boxes that determines collision should
	 * be drawn.
	 * 
	 * @param isDrawingRegions
	 */
	public void setDrawingRegions(boolean isDrawingRegions) {
		this.isDrawingRegions = isDrawingRegions;
	}
}
