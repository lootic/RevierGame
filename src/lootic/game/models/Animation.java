package lootic.game.models;

import javax.swing.ImageIcon;

public class Animation {
	
	private ImageIcon[] animation;

	public int getAnimationLength() {
		return animation.length;
	}

	public ImageIcon getSprite(int i) {
		return animation[i%animation.length];
	}

}
