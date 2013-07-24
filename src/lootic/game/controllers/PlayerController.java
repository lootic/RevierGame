package lootic.game.controllers;

import lootic.game.interfaces.Controllable;
import lootic.game.models.Player;

public class PlayerController implements Controllable {

	private Player player;
	private boolean right, left, jump;

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

		if (action == Actions.JUMP) {
			if (player.isOnGround()) {
				player.setOnGround(false);
				player.setJumpFrames(15);
				jump = true;
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

		if (action == Actions.JUMP && jump) {
			player.setJumpFrames(0);
			if(player.getFallSpeed() < 0){
				player.setFallSpeed(0);
			}
			jump = false;
		}
	}

}
