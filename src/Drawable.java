import java.awt.Image;

public interface Drawable extends Positioned {
	public Image getSprite();
	void setSprite(Image image);
}
