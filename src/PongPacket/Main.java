package PongPacket;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to Extreme Pong!");
		System.out.println("Start the game and move your board using UP and DOWN arrows on your keyboard.");
		System.out.println("------------------------------------------------------");

		// Game logic, graphics, etc.
		GameLogic gameLogic = new GameLogic();
		GameGraphics gameGraphics = new GameGraphics(gameLogic);

		// Initialization of game frame
		JFrame frame = new JFrame("Extreme Pong");
		frame.setSize(1000, 600); // graphics implemented on increments of 10
		frame.setTitle("Extreme Pong");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//frame.add(gameLogic);
		frame.add(gameGraphics);

		while(true){
			gameLogic.update();
			gameGraphics.repaint();
			try{
				Thread.sleep(16); // roughly 60 fps or 16ms per frame
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
