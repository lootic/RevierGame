package lootic.game.models;

import lootic.game.controllers.Input;
import lootic.game.controllers.Actions;

public class Player extends Creature {
	private int numOfFramesLock; // number of frames we are locked in the
									// current animation
	private int leftRightFactor;
	
	private int jumpFrames = 0;

	private void readInput() {
		if (numOfFramesLock > 0) {
			--numOfFramesLock;
		} else {
			movementSpeed += leftRightFactor * movementSpeedAcceleration;
			if(jumpFrames > 0){
				--jumpFrames;
				System.out.println(jumpFrames);
				this.setFallSpeed(-8000);
			}
		}
	}

	@Override
	public void updatePosition() {
		readInput();
		super.updatePosition();
	}

	public void setJumpFrames(int numOfFrames) {
		this.jumpFrames = numOfFrames;
	}

	private void walljumpLeft() {
		movementSpeed = -10000;
		numOfFramesLock = 6;
		fallSpeed = -8800;
	}

	private void walljumpRight() {
		movementSpeed = 10000;
		numOfFramesLock = 6;
		fallSpeed = -8800;
	}
	
	public void setLeftRightFactor(int leftRightFactor) {
			this.leftRightFactor = leftRightFactor;
	}
}
