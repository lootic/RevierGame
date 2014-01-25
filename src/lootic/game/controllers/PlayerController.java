package lootic.game.controllers;

import lootic.game.interfaces.Controllable;
import lootic.game.models.Player;

public class PlayerController implements Controllable {

	private Player player;
	private boolean right, left, jump, up, down, airdash;

	public PlayerController(Player player) {
		this.player = player;
	}

	@Override
	public void inputPressed(Actions action) {
		if (action == Actions.LEFT) {
			left = true;
			player.setLeftRightFactor(-1);
		}
		if (action == Actions.RIGHT) {
			right = true;
			player.setLeftRightFactor(1);
		}
		if (action == Actions.UP) {
			up = true;
			player.setUpDownFactor(-1);
		}
		if (action == Actions.DOWN) {
			down = true;
			player.setUpDownFactor(1);
		}

		if (action == Actions.JUMP) {
			if (player.isOnGround()) {
				player.setOnGround(false);
				player.setJumpFrames(15);
				jump = true;
			} else if (player.isBumpingLeft()) {
				player.setWallJumpFrames(3);
				player.setMovementSpeed(10000);
			} else if (player.isBumpingRight()) {
				player.setWallJumpFrames(3);
				player.setMovementSpeed(-10000);
			} else if (player.isAbleToAirDash() && !airdash && !jump) {
				if (up || down || left || right) {
					airdash = true;
					player.setAirDashFrames(11);
					player.setMovementSpeed(0);
				}
			}
		}
	}

	@Override
	public void inputReleased(Actions action) {

		if (action == Actions.LEFT) {
			left = false;
			if (right) {
				player.setLeftRightFactor(1);
			} else {
				player.setLeftRightFactor(0);
			}
		}

		if (action == Actions.RIGHT) {
			right = false;
			if (left) {
				player.setLeftRightFactor(-1);
			} else {
				player.setLeftRightFactor(0);
			}
		}
		if (action == Actions.UP) {
			up = false;
			if (down) {
				player.setUpDownFactor(1);
			} else {
				player.setUpDownFactor(0);
			}
		}
		if (action == Actions.DOWN) {
			down = false;
			if (up) {
				player.setUpDownFactor(-1);
			} else {
				player.setUpDownFactor(0);
			}
		}

		if (action == Actions.JUMP && jump) {
			player.setJumpFrames(0);
			if (player.getFallSpeed() < 0) {
				player.setFallSpeed(0);
			}
			jump = false;
		}

		if (action == Actions.JUMP && airdash) {
			player.setAirDashFrames(0);
			player.setAbleToAirdash(false);
			airdash = false;
		}
	}

}
