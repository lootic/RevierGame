
public class Input {
	private static float upDown;
	private static float leftRight;
	private static boolean jump;
	private static boolean attack;
	private static boolean sheild;
	private static boolean dash;
	private static boolean dodgeLeft;
	private static boolean dodgeRight;
	private static boolean rollLeft;
	private static boolean rollRight;
	
	public static void reset(){
		jump = false;
		attack = false;
		sheild = false;
		dash = false;
		dodgeLeft = false;
		dodgeRight = false;
		rollLeft = false;
		rollRight = false;
	}

	public static float getUpDown() {
		return upDown;
	}
	
	public static float getLeftRight() {
		return leftRight;
	}
	
	public boolean getJump() {
		return jump;
	}
	
	public boolean getAttack() {
		return attack;
	}
	
	public boolean getSheild() {
		return sheild;
	}
	
	public boolean getDash() {
		return dash;
	}
	
	public boolean getDodgeLeft() {
		return dodgeLeft;
	}
	
	public boolean getDodgeRight() {
		return dodgeRight;
	}
	
	public boolean getRollLeft() {
		return rollLeft;
	}
	
	public boolean getRollRight() {
		return rollRight;
	}
}
