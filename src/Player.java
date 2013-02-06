public class Player extends Creature {
	private int numOfFramesLock; // number of frames we are locked in the
									// current animation
	private InputState inputState;
	private JumpState jumpState;

	private void readInput() {
		if (numOfFramesLock > 0) {
			--numOfFramesLock;
		} else {
			inputState = Input.getContinuedState();
			if (inputState == null) {
				inputState = Input.getState();
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

		/*
		 * System.out.println("ms=" + movementSpeed + " decX=" + decX + " decY="
		 * + decY);
		 */
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

	protected void groundCollision() {
		super.groundCollision();
		jumpState = JumpState.CAN_JUMP;
	}

	protected void leftCollision() {
		super.leftCollision();
		fallSpeed = 2000;
		jumpState = JumpState.CAN_LEFT_WALLJUMP;
	}

	protected void rightCollision() {
		super.rightCollision();
		fallSpeed = 2000;
		jumpState = JumpState.CAN_RIGHT_WALLJUMP;
	}
}
