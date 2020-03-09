package PongPaketti;


import javax.swing.JFrame;

public class Main {

	
	public static void main(String[] args) {
		
		System.out.println("Tervetuloa Extreme Ping Pong -peliin!");
		System.out.println("Liikuta vasenta lautaa näppäimillä A + Z");
		System.out.println("(ja oikeanpuoleista K + M)");
		System.out.println("------------------------------------------------------");
		
		
		Pelattavuus pelattavuus = new Pelattavuus();
		
		JFrame obj = new JFrame();
		
		obj.setBounds(10, 10, 1011, 540);
		obj.setTitle("Extreme Ping Pong");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(pelattavuus);
		
	}
	
}
