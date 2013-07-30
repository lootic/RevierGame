package lootic.game.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import lootic.game.interfaces.Controllable;

/**
 * Abstraction layer for computer input on Windows
 **/
public class Input implements KeyListener {
	private static Controllable controllable;

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("hello keyPressed");
		if (controllable == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			controllable.inputPressed(Actions.LEFT);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			controllable.inputPressed(Actions.RIGHT);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			controllable.inputPressed(Actions.UP);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controllable.inputPressed(Actions.DOWN);
		} else if (e.getKeyCode() == KeyEvent.VK_X) {
			controllable.inputPressed(Actions.JUMP);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("hello keyReleased");
		if (controllable == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			controllable.inputReleased(Actions.LEFT);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			controllable.inputReleased(Actions.RIGHT);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			controllable.inputReleased(Actions.UP);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controllable.inputReleased(Actions.DOWN);
		} else if (e.getKeyCode() == KeyEvent.VK_X) {
			controllable.inputReleased(Actions.JUMP);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void setControllable(Controllable controllable) {
		Input.controllable = controllable;
	}
}
