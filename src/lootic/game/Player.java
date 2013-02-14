package lootic.game;

import lootic.game.controllers.Input;
import lootic.game.controllers.InputState;

public class Player extends Creature {
	private int numOfFramesLock; // number of frames we are locked in the
									// current animation
	private InputState inputState;
	private JumpState jumpState;

	private void readInput() {
		if (numOfFramesLock > 0) {
			--numOfFramesLock;
		} else {
			inputState = Input.getPreviousInputState();
			if (inputState == null) {
				inputState = Input.getInputState();
			}
			if (inputState == InputState.JUMP) {
				if (jumpState == JumpState.CAN_LEFT_WALLJUMP)
					walljumpLeft();
				else if (jumpState == JumpState.CAN_RIGHT_WALLJUMP) {
					walljumpRight();
				} else if (jumpState == JumpState.CAN_JUMP) {
					jump();
				} else {
					// double jump
				}
			}

			movementSpeed += Input.getLeftRight() * movementSpeedAcceleration;
		}
	}

	@Override
	public void updatePosition() {
		readInput();
		if (Math.abs(movementSpeed) > maxMovementSpeed) {
			if (movementSpeed > 0)
				movementSpeed = maxMovementSpeed;
			else
				movementSpeed = -maxMovementSpeed;
		}
		decX += movementSpeed;
		decY += fallSpeed;
		if (Math.abs(decX) >= 1000 || Math.abs(decY) >= 1000) {
			prevX = x;
			prevY = y;
			x += decX / 1000;
			y += decY / 1000;
			decX %= 1000;
			decY %= 1000;
		}
	}

	private void jump() {
		System.out.println("jump");
		fallSpeed = -12000;
		jumpState = JumpState.CAN_NOT_JUMP;
	}

	private void walljumpLeft() {
		movementSpeed = -10000;
		numOfFramesLock = 6;
		fallSpeed = -8800;
		jumpState = JumpState.CAN_NOT_JUMP;
	}

	private void walljumpRight() {
		movementSpeed = 10000;
		numOfFramesLock = 6;
		fallSpeed = -8800;
		jumpState = JumpState.CAN_NOT_JUMP;
	}

	public JumpState getJumpState() {
		return jumpState;
	}

	public void setJumpState(JumpState jumpState) {
		this.jumpState = jumpState;
	}

	@Override
	public void addCollisionBox(Region r) {
		super.addCollisionBox(r);
		// r.setCollisionAction(Region.MOVE_BACK);
	}
}
