import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Canvas canvas = new Canvas();
		Physics physics = new Physics();
		Player player = new Player();
		Platform platform = new Platform();
		JFrame frame = new JFrame();
		
		frame.add(canvas);
		frame.addKeyListener(player);
		player.moveX(40);
		
		canvas.registerDrawable(platform);
		canvas.registerDrawable(player);
		canvas.addKeyListener(player);
		canvas.setDrawingRegions(true);
		
		Region r1 = new Region(10,10,20,20);
		Region r2 = new Region(600,100,2000,2000);
		Region r3 = new Region(0,500,2000,200);
		
		player.addCollisionBox(r1);

		platform.addCollisionBox(r2);
		platform.addCollisionBox(r3);
		
		physics.registerCreature(player);
		physics.registerPlatform(platform);
		physics.registerMovable(player);
		physics.registerWeighing(player);
		
		System.out.println(System.getProperty("user.dir"));

		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		physics.setPaused(false);
		
		while(true) {
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canvas.update();
			physics.update();
		}
		
	}
}
