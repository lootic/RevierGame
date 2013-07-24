package lootic.game.models;

import javax.swing.ImageIcon;

public class AnimationHandler {
	
	private Animation animation;
	private int frameCount;
	private int frameUpdateInterval;
	
	public void setAnimation(Animation animation){
		frameCount = 0;
	}
	
	public ImageIcon getSprite(){
		return animation.getSprite(frameCount/frameUpdateInterval);
	}
	
	public void setFrameUpdateInterval(int frameUpdateInterval){
		this.frameUpdateInterval = frameUpdateInterval;
	}
}
