import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Canvas canvas = new Canvas();
		Player player = new Player();
		Entity entity = new Entity();
		Physics physics = new Physics();
		JFrame frame = new JFrame();
		
		frame.add(canvas);
		frame.addKeyListener(player);
		
		canvas.registerDrawable(entity);
		canvas.registerDrawable(player);
		canvas.addKeyListener(player);
		canvas.setDrawingCollisionBoxes(true);
		
		Region r1 = new Region(10,10,20,20);
		Region r2 = new Region(50,300,2000,200);
		
		player.addCollisionBox(r1);

		entity.addCollisionBox(r2);
		
		physics.registerCollidable(r1);
		physics.registerCollidable(r2);
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
				Thread.sleep(34);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canvas.update();
			physics.update();
		}
		
	}
}
