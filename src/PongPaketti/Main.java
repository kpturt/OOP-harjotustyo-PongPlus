package PongPaketti;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		System.out.println("Tervetuloa Extreme Pong -peliin!");
		System.out.println("Aloita peli ja liikuta vasenta lautaa nuolinäppäimillä.");
		System.out.println("------------------------------------------------------");

		// pelilogiikka
		Pelattavuus pelattavuus = new Pelattavuus();

		// pUeliruudun alustus
		JFrame obj = new JFrame();
		obj.setBounds(10, 10, 1011, 540);
		obj.setTitle("Extreme Pong");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(pelattavuus);
	}
}
