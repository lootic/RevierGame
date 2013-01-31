import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener {	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			up = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			movementSpeedMultiplier = 7500;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			up = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			movementSpeedMultiplier = 1000;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
