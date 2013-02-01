import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Creature implements KeyListener {
	private int jumpFrames = 10;
	private int jumpFramesLeft = 10;
	private boolean canWallJump;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_Z) {
			movementSpeed = -movementSpeed;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_Z) {
		}
	}

	@Override
	public void updatePosition() {
		super.updatePosition();
		if (up == 1) {
			if(canWallJump){
				wallJump();
				canWallJump = false;
			}
			else {
				jump();
			}
		} else {
			jumpFramesLeft = 0;
		}
	};

	private void jump() {
		if(jumpFramesLeft > 0){
			--jumpFramesLeft;
			fallSpeed = -10000;
		}
	}
	
	private void wallJump() {
		fallSpeed = -10000;
		movementSpeed = -10000;
	}
	
	protected void groundCollision() {
		super.groundCollision();
		jumpFramesLeft = jumpFrames;
	}
	
	protected void wallCollision() {
		fallSpeed = 4000;
		canWallJump = true;
	}

	public void keyTyped(KeyEvent e) {
	}
}
