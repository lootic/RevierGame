import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Canvas canvas = new Canvas();
		Physics physics = new Physics();
		Player player = new Player();
		Creature creature = new Creature();
		Platform platform = new Platform();
		JFrame frame = new JFrame();

		frame.add(canvas);
		frame.addKeyListener(new Input());
		player.moveX(40);

		canvas.registerDrawable(platform);
		canvas.registerDrawable(player);
		canvas.setDrawingRegions(true);

		Region<Player> r1 = new Region<Player>(10, 10, 20, 20);
		Region<Creature> r6 = new Region<Creature>(10, 10, 20, 20);
		Region<Creature> r7 = new Region<Creature>(10, 10, 20, 20);

		Region<Platform> r2 = new Region<Platform>(600, 100, 2000, 2000);
		Region<Platform> r3 = new Region<Platform>(0, 480, 2000, 200);
		Region<Platform> r4 = new Region<Platform>(-5, 80, 20, 2000);
		Region<Platform> r5 = new Region<Platform>(400, 270, 50, 20);

		creature.addCollisionBox(r6);
		creature.addHurtBox(r7);
		player.addCollisionBox(r1);

		platform.addCollisionBox(r2);
		platform.addCollisionBox(r3);
		platform.addCollisionBox(r4);
		platform.addCollisionBox(r5);
		
		physics.registerCreature(player);
		physics.registerCreature(creature);
		physics.registerPlatform(platform);
		
		canvas.registerDrawable(creature);

		System.out.println(System.getProperty("user.dir"));

		frame.setSize(800, 520);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		physics.setPaused(false);

		while (true) {
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canvas.update();
			physics.update();
			Input.reset();
		}

	}
}
