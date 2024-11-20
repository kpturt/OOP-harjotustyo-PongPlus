package PongPaketti;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pelattavuus extends JPanel implements KeyListener, ActionListener{

	PlayerBase p1 = new P1(10, 200, 100);
	PlayerBase cpu = new CPU(975, 200, 100);
	//p1.move()
	//cpu.move()

	public static Points points = new Points();
	private boolean play = false;
	private Timer time;
	private int delay = 8;
	Random rnd = new Random();
	
	// pallon muuttujat
	public int ballPosX = 500;
	public int ballPosY = 250;
	private int ballDirX = 2;
	private int ballDirY = -4;
	private int ballDim = 30;
	
	// maalien rajasijainnit
	public int maaliVas = 0;
	public int maaliOik = 1000;
	
	// Muuttuja jonka suuretessa pallo pomppaa laudasta tietyn verran, jotta peli pysyy refresh -raten mukana
	// eikä pallo mene laudan läpi, jos "intersects" ei kerkeä rekisteröimään osumaa.
	public int bounceBack = 0;
	
	// muuttuja joka vastaa uuden pelin luonnista
	public boolean uusiPeli = false; 

	// pelin alustus
	public Pelattavuus() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
		
		// alustetaan pallolle satunnainen liikkumissuunta
		rnd.nextInt(4);
		if(rnd.nextInt(4) == 0) {
			ballDirX = -2;
			ballDirY = 2;
		}
		else if(rnd.nextInt(4) == 1){
			ballDirX = 2;
			ballDirY = -2;
		}
		else if(rnd.nextInt(4) == 2){
			ballDirX = 2;
			ballDirY = 2;
		}
		else if(rnd.nextInt(4) == 3){
			ballDirX = -2;
			ballDirY = -2;
		}
	}
	
	// peliruudun grafiikkapiirto
	public void paint(Graphics g) {
		
		// tausta
		g.setColor(Color.black);
		g.fillRect(1, 1, 1000, 500);
		
		// ylä- ja alaseinä
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 1000, 5); // ylä
		g.fillRect(0, 500, 1000, 5); //ala
		
		// maalit
		g.setColor(Color.RED);
		g.fillRect(0, 0, 5, 505); // vasen
		g.fillRect(1000, 0, 5, 505); // oikea
		
		// keskiviiva
		g.setColor(Color.green);
		g.fillRect(500, 0, 10, 501);
		
		// vasen lauta
		g.setColor(Color.blue);
		g.fillRect(p1.posX, p1.posY, 20, p1.boardHeight);
		
		// oikea lauta
		g.setColor(Color.blue);
		g.fillRect(cpu.posX, cpu.posY, 20, cpu.boardHeight);
		
		//pallo
		g.setColor(Color.white);
		g.fillOval(ballPosX, ballPosY, ballDim, ballDim);

		g.dispose();
	}
	
	// pallon liikkuminen ja muuta pelin logiikkaa
	public void actionPerformed(ActionEvent e) {
		time.start();
		Rectangle rect1 = new Rectangle(p1.posX, p1.posY, 20, p1.boardHeight);
		Rectangle rect2 = new Rectangle(cpu.posX, cpu.posY, 20, cpu.boardHeight);
		Rectangle ballRect = new Rectangle(ballPosX, ballPosY, ballDim, ballDim);
		
		if(play) {
			
			if(uusiPeli == true) {
				// jokaisen maalin jälkeen muutetaan pallolle taas uusi satunnainen liikkumissuunta
				rnd.nextInt(4);
				
				if(rnd.nextInt(4) == 0) {
					ballDirX = -2;
					ballDirX = 2;
				}
				else if(rnd.nextInt(4) == 1){
					ballDirX = 2;
					ballDirX = -2;
				}
				else if(rnd.nextInt(4) == 2){
					ballDirX = 2;
					ballDirX = 2;
				}
				else if(rnd.nextInt(4) == 3){
					ballDirX = -2;
					ballDirX = -2;
				}
				uusiPeli = false;
			}
			
			ballPosX += ballDirX;
			ballPosY += ballDirY;
			
			// jos pallo osuu vasempaan lautaan
			if(ballRect.intersects(rect1)) {
				ballPosX += 15+bounceBack;
				ballDirX = -ballDirX;
				if(ballDirX < 0) {
					ballDirX -= 1;
					bounceBack += 2;
				}
				else if(ballDirX > 0){
					ballDirX += 1;
					bounceBack += 2;
				}
			}
			
			// jos pallo osuu oikeaan lautaan
			if(ballRect.intersects(rect2)) {
				ballPosX -= 15+bounceBack;
				ballDirX = -ballDirX;
				if(ballDirX < 0) {
					ballDirX -= 1;
					bounceBack += 2;
				}
				else if(ballDirX > 0){
					ballDirX += 1;
					bounceBack += 2;
				}
			}

			if(ballPosY < 10) {
				ballDirY = -ballDirY;
			}
			
			if(ballPosY > 500-ballDim) {
				ballDirY = -ballDirY;
			}
			
			// maalin tullessa
			if(ballPosX < maaliVas || ballPosX > maaliOik) {
				restartGame();	
			}
		}
		repaint();
	}
	
	// Metodi jota kutsutaan kun pelissä tehdään maali. Se muuttaa lautojen kokoa pistetilanteen mukaan
	// sekä muuttaa kaikki tarvittavat muuttujat ( kuten pallon sijainti ja nopeus ) takaisin oletusarvoonsa.
	public void restartGame() {
		
			// tulostaa pelitilanteen pallon mennessä oikeaan maaliin
			if (ballPosX >= maaliOik) {
				uusiPeli = true;
				bounceBack = 0;
				points.updateScoreVas();
				System.out.println("Pelin tilanne: "+ points.returnScoreVas()+" - "+ points.returnScoreOik());
			}
			// tulostaa pelitilanteen pallon mennessä vasempaan maaliin
			else if(ballPosX <= maaliVas){
				uusiPeli = true;
				bounceBack = 0;
				points.updateScoreOik();
				System.out.println("Pelin tilanne: "+ points.returnScoreVas()+" - "+ points.returnScoreOik());
			}
			
			// pallon sijainnin nollaaminen
			ballPosX = 500;
			ballPosY = 250;
			ballDirX = -2;
			ballDirY = -4;
			
			// muuttavat laudan kokoja pelitilanteen mukaan
			if(points.scoreVas == points.scoreOik && points.scoreVas != 2 && points.scoreOik != 2) {
				p1.boardHeight = 100;
				cpu.boardHeight = 100;
			}
			if(points.scoreVas == 2 && points.scoreOik == 2) {
				p1.boardHeight = 50;
				cpu.boardHeight = 50;
			}
			if(points.scoreVas < points.scoreOik) {
				p1.boardHeight = 170;
			}
			if(points.scoreVas > points.scoreOik) {
				cpu.boardHeight = 170;
			}
			
			// pelaajan voittaessa tulostaa voittajan sekä aloittaa uuden pelin.
			if(points.scoreVas == 3) {
				System.out.println("Vasen pelaaja voitti pelin!");
				points.scoreOik = 0;
				points.scoreVas = 0;
				p1.boardHeight = 100;
				cpu.boardHeight = 100;
				System.out.println("------------------------------------------------------");
				restartGame();
			}
			if(points.scoreOik == 3) {
				System.out.println("Oikea pelaaja voitti pelin!");
				points.scoreOik = 0;
				points.scoreVas = 0;

				p1.boardHeight = 100;
				cpu.boardHeight = 100;
				System.out.println("------------------------------------------------------");
				restartGame();
			}
		}
		
	// metodit lautojen liikkumiseen, 1 = vasen, 2 = oikea
	public void moveP1_Up() {
		play = true;
		p1.posY += 50;
	}
	
	public void moveP1_Down() {
		play = true;
		p1.posY -= 50;
	}
	
	public void moveCPU_Up() {
		play = true;
		cpu.posY += 50;
	}
	
	public void moveCPU_down() {
		play = true;
		cpu.posY -= 50;
	}
	
	// funktiot jotka tunnistavat näppäinten painallukset ja reagoivat sen mukaan
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(p1.posY < 10) {
				p1.posY = 10;
			}
			else {
				moveP1_Down();
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(p1.posY + p1.boardHeight >= 500) {
				p1.posY = 495 - p1.boardHeight;
			}
			else {
				moveP1_Up();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}