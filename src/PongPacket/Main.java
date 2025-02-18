package PongPacket;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to Pong+!");
		System.out.println("Move your board using UP and DOWN arrows on your keyboard.");
		System.out.println("------------------------------------------------------");

		// create player and CPU objects using PlayerBase references
		PlayerBase p1 = new P1(0, 210, 181);
		PlayerBase cpu = new CPU(870, 210, 181);

		// initialization of game logic and graphics
		GameLogic gameLogic = new GameLogic(p1, cpu);
		GameGraphics gameGraphics = new GameGraphics(gameLogic);

		// initialization of game frame and its settings
		JFrame frame = new JFrame("Pong+");
		frame.setSize(916, 639); // 30x20 grid
		frame.setTitle("Pong+");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// for getting frame size (900x600)
//		Insets insets = frame.getInsets();
//		int contentWidth = frame.getWidth() - insets.left - insets.right;
//		int contentHeight = frame.getHeight() - insets.top - insets.bottom;
//		System.out.println("Content width: " + contentWidth + ", Content height: " + contentHeight);

		frame.add(gameGraphics);
		frame.addKeyListener((P1) p1);

		new Thread(() -> {
			while (true) {
				gameLogic.update();      // update game state
				gameGraphics.repaint(); // refresh screen graphics

				try {
					Thread.sleep(16);  // approx. 60 FPS
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
