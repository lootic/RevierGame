import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
	private static float up;
	private static float down;
	private static float left;
	private static float right;

	// competing actions are using a common variable so that we can choose which
	// to do next
	private static InputState inputState;
	private static InputState prevInputState;

	// private static InputState prevInputState;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = 1;
		}

		if (inputState == null) { // inputState not yet set for this frame
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				inputState = InputState.JUMP;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (prevInputState == InputState.JUMP) {
				prevInputState = null;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static float getLeftRight() {
		return right - left;
	}

	public static float getUpDown() {
		return down - up;
	}

	public static void reset() {
		// prevInputState = inputState;
		inputState = null;
	}

	public static InputState getState() {
		return inputState;
	}

	public static InputState getContinuedState() {
		return prevInputState;
	}
}
