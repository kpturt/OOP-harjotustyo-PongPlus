package PongPacket;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyListener;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to Pong+!");
		System.out.println("Start the game and move your board using UP and DOWN arrows on your keyboard.");
		System.out.println("------------------------------------------------------");

		// game logic, graphics, etc.
		GameLogic gameLogic = new GameLogic();
		GameGraphics gameGraphics = new GameGraphics(gameLogic);

		// initialization of game frame
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
		frame.addKeyListener(gameLogic.p1);

		new Thread(() -> {
			while (true) {
				gameLogic.update();      // Update game state
				gameGraphics.repaint(); // Refresh the screen

				try {
					Thread.sleep(16);  // Approx. 60 FPS
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
