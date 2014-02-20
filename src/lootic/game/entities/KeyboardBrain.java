package lootic.game.entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import lootic.game.interfaces.Brain;
import lootic.game.interfaces.Movable;

public class KeyboardBrain implements KeyListener, Brain<Movable> {
	private short leftRight;
	private short upDown;
	private short attack;
	private short jump;
	private short frameLock;
	private Movable controlled;
	private final int ATTACK = KeyEvent.VK_A;
	private final int JUMP = KeyEvent.VK_S;

	@Override
	public void setControlled(Movable controllable) {
		controlled = controllable;
	}

	@Override
	public void makeDecision() {
		if (frameLock > 0) {
			--frameLock;
			return;
		}
		walk();
	}

	private void walk() {
		controlled.setHorizontalAcceleration(leftRight * 0.4f);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			if (upDown < 1) {
				++upDown;
			}
			break;
		case KeyEvent.VK_UP:
			if (upDown > -1) {
				--upDown;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (leftRight > -1) {
				--leftRight;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (leftRight < 1) {
				++leftRight;
			}
			break;
		case ATTACK:
			if (frameLock == 0) {
				++attack;
				attack();
			}
			break;
		case JUMP:
			System.out.println("jump");
			if (frameLock == 0) {
				++jump;
				jump();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			--upDown;
			break;
		case KeyEvent.VK_UP:
			++upDown;
			break;
		case KeyEvent.VK_LEFT:
			++leftRight;
			break;
		case KeyEvent.VK_RIGHT:
			--leftRight;
			break;
		case JUMP:
			jump = 0;
		}
	}

	private void attack() {

	}

	private void jump() {
		System.out.println("jump");
		if(jump > 0 && jump < 15) {
			controlled.setVerticalAcceleration(0f);
			controlled.setVerticalSpeed(-5f);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
