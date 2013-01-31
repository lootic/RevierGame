import java.awt.Image;

public interface Drawable {
	public Image getSprite();
	public int getX();
	public int getY();
	void setSprite(Image image);
}
