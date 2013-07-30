package lootic.game.models;

public class Player extends Creature {
	private int numOfFramesLock; // number of frames we are locked in the
									// current animation
	private float leftRightFactor;
	private float upDownFactor;
	private boolean isAbleToAirDash;

	private int airDashFrames = 0;
	private int jumpFrames = 0;
	private int wallJumpFrames = 0;

	private void calculateMovement() {
		if (numOfFramesLock > 0) {
			--numOfFramesLock;
		} else {
			if (airDashFrames > 0) {
				System.out.println("airDashing");
				if (Math.abs(upDownFactor) + Math.abs(leftRightFactor) == 2) {
					this.moveDecX((int) (leftRightFactor * 10000 * 0.7));
					this.moveDecY((int) (upDownFactor * 17000 * 0.7));
				} else {
					this.moveDecX((int) leftRightFactor * 10000);
					this.moveDecY((int) upDownFactor * 17000);
				}
				this.setFallSpeed(-1000);
				--airDashFrames;
				if (airDashFrames <= 0) {
					this.isAbleToAirDash = false;
				}
				return;
			}
			movementSpeed += leftRightFactor * movementSpeedAcceleration;
			if (jumpFrames > 0) {
				--jumpFrames;
				this.setFallSpeed(-10000);
			}
			if (wallJumpFrames > 0) {
				--wallJumpFrames;
				this.setFallSpeed(-5000);
			}
		}
	}

	@Override
	public void setOnGround(boolean b) {
		super.setOnGround(b);
		if (b) {
			isAbleToAirDash = b;
		}
	}

	@Override
	public void setBumpingLeft(boolean b) {
		super.setBumpingLeft(b);
		if (b) {
			isAbleToAirDash = b;
		}
	}

	@Override
	public void setBumpingRight(boolean b) {
		super.setBumpingRight(b);
		if (b) {
			isAbleToAirDash = b;
		}
	}

	@Override
	public void updatePosition() {
		calculateMovement();
		super.updatePosition();
	}

	public void setJumpFrames(int numOfFrames) {
		this.jumpFrames = numOfFrames;
	}

	public void setLeftRightFactor(float leftRightFactor) {
		if (airDashFrames > 0) {
			return;
		}
		this.leftRightFactor = leftRightFactor;
	}

	public void setUpDownFactor(float upDownFactor) {
		if (airDashFrames > 0) {
			return;
		}
		this.upDownFactor = upDownFactor;
	}

	public void setWallJumpFrames(int numOfFrames) {
		wallJumpFrames = numOfFrames;
	}

	public boolean isAbleToAirDash() {
		return isAbleToAirDash;
	}

	public void setAirDashFrames(int numOfFrames) {
		this.airDashFrames = numOfFrames;
	}

	public void setAbleToAirdash(boolean b) {
		this.isAbleToAirDash = b;
	}
}
