package PongPaketti;

//tässä importataan kaikki mahdollinen java diibadaaba jotta saadaan asiota (ehkä) toimimaan
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
	
	// MUUTTUJIEN ALUSTUSTA
	
	public static pelaaja pel = new pelaaja();
	public static tietokone tie = new tietokone();
	public static Pisteet p = new Pisteet();
	Random rnd = new Random();

	// ?
	private boolean play = false;
	
	// ?
	private Timer time;
	private int delay = 8;
	
	// pallo
	public int ballPosX = 500;
	public int ballPosY = 250;
	private int ballDirX = 2;
	private int ballDirY = -4;
	private int ballDim = 30;
	
	// maalit
	public int maaliVas = 0;
	public int maaliOik = 1000;
	
	// muuttuja jonka suuretessa pallo pomppaa laudasta tietyn verran, jotta peli pysyy refresh -raten mukana
	// eikä pallo mene laudan läpi, kun "intersects" ei kerkeä rekisteröimään osumaa.
	public int bounceBack = 0;
	
	// muuttuja joka muuttuu kun jompi kumpi pelaajista voittaa
	public boolean uusiPeli = false; 

	// tekee ?
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
	

//---------------------------------------------------------
	
	// ruutu ja sen grafiikka
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
		g.fillRect(pel.playerVasX, pel.playerVasY, 20, pel.playerVasPituus);
		
		// oikea lauta
		g.setColor(Color.blue);
		g.fillRect(tie.playerOikX, tie.playerOikY, 20, tie.playerOikPituus);
		
		//pallo
		g.setColor(Color.white);
		g.fillOval(ballPosX, ballPosY, ballDim, ballDim);
		
		// kokeilin tehdä pelaajien pisteet graafisesti pelinäyttöön mutta se osoittautui
		// sekä vaikeaksi, että epätoimivaksi
		
		/*if(p.scoreVas == 0) {
			g.setColor(Color.white);
			g.fillRect(400, 50, 50, 50);
			g.setColor(Color.black);
			g.fillRect(405, 55, 40, 40);
		}
		
		if(p.scoreVas == 0) {
			g.setColor(Color.white);
			g.fillRect(600, 50, 50, 50);
			g.setColor(Color.black);
			g.fillRect(605, 55, 40, 40);
		}
		
		if(p.scoreVas == 1) {
			g.setColor(Color.white);
			g.fillRect(400, 50, 10, 50);
		}
		
		if(p.scoreOik == 1) {
			g.setColor(Color.white);
			g.fillRect(600, 50, 10, 50);
		} */
	
		
		g.dispose();
		
	}
	
	
//-----------------------------------------------------------------
	
	// pallon liikkuminen ja muuta peliin liittyvää
	public void actionPerformed(ActionEvent e) {
		time.start();
		
		Rectangle rect1 = new Rectangle(pel.playerVasX, pel.playerVasY, 20, pel.playerVasPituus);
		Rectangle rect2 = new Rectangle(tie.playerOikX, tie.playerOikY, 20, tie.playerOikPituus);
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
			
			// jos tulee maali, restarttaa peli
			if(ballPosX < maaliVas || ballPosX > maaliOik) {
				restartGame();	
			}
			
		}
		repaint();
		
	}
//------------------------------------------------------------------
	
	// metodi jota kutsutaan kun pelissä tehdään maali. Se muuttaa lautojen kokoa pistetilanteen mukaan
	// sekä muuttaa kaikki tarvittavat muuttujat ( kuten pallon sijainti ja nopeus ) takaisin oletusarvoonsa.
	public void restartGame() {
		
			// tulostaa pelitilanteen pallon mennessä oikeaan maaliin
			if (ballPosX >= maaliOik) {
				uusiPeli = true;
				bounceBack = 0;
				p.updateScoreVas();
				System.out.println("Pelin tilanne: "+p.returnScoreVas()+" - "+p.returnScoreOik());
			}	
			
			// tulostaa pelitilanteen pallon mennessä vasempaan maaliin
			else if(ballPosX <= maaliVas){
				uusiPeli = true;
				bounceBack = 0;
				p.updateScoreOik();
				System.out.println("Pelin tilanne: "+p.returnScoreVas()+" - "+p.returnScoreOik());
		
			}
			
			//pallon sijainnin nollaaminen
			ballPosX = 500;
			ballPosY = 250;
			ballDirX = -2;
			ballDirY = -4;
			
			// muuttavat laudan kokoja pelitilanteen mukaan
			if(p.scoreVas == p.scoreOik && p.scoreVas != 2 && p.scoreOik != 2) {
				tie.playerVasPituus = 100;
				tie.playerOikPituus = 100;
			}
			
			if(p.scoreVas == 2 && p.scoreOik == 2) {
				pel.playerVasPituus = 50;
				tie.playerOikPituus = 50;
			}
			
			if(p.scoreVas < p.scoreOik) {
				pel.playerVasPituus = 170;
			}
			
			if(p.scoreVas > p.scoreOik) {
				tie.playerOikPituus = 170;
			}
			
			// pelaajan voittaessa tulostaa voittajan sekä aloittaa uuden pelin.
			if(p.scoreVas == 3) {
				System.out.println("Vasen pelaaja voitti pelin!");
				p.scoreOik = 0;
				p.scoreVas = 0;
				pel.playerVasPituus = 100;
				tie.playerOikPituus = 100;
				System.out.println("------------------------------------------------------");
				restartGame();
			}
			if(p.scoreOik == 3) {
				System.out.println("Oikea pelaaja voitti pelin!");
				p.scoreOik = 0;
				p.scoreVas = 0;

				pel.playerVasPituus = 100;
				tie.playerOikPituus = 100;
				System.out.println("------------------------------------------------------");
				restartGame();
			}
			
		}
		
	// metodit lautojen liikkumiseen, 1 = vasen, 2 = oikea.
	public void moveRight1() {
		play = true;
		pel.playerVasY += 50;
	}
	
	public void moveLeft1() {
		play = true;
		pel.playerVasY -= 50;
	}
	
	public void moveRight2() {
		play = true;
		tie.playerOikY += 50;
	}
	
	public void moveLeft2() {
		play = true;
		tie.playerOikY -= 50;
	}
	
//------------------------------------------------------------------
	
	// metodit jotka tunnistavat näppäinten painalluksen ja reagoivat sen mukaan
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			if(pel.playerVasY + pel.playerVasPituus >= 500) {
				pel.playerVasY = 495 - pel.playerVasPituus;
			}
			else {
				moveRight1();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			if(pel.playerVasY < 10) {
				pel.playerVasY = 10;
			}
			else {
				moveLeft1();
			}
		}
		
				//-------------
		
		if(e.getKeyCode() == KeyEvent.VK_M) {
			if(tie.playerOikY + tie.playerOikPituus >= 500) {
				tie.playerOikY = 495 - tie.playerOikPituus;
			}
			else {
				moveRight2();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_K) {
			if(tie.playerOikY < 10) {
				tie.playerOikY = 10;
			}
			else {
				moveLeft2();
			}
		}
	}
			  //-------------
	
	public void keyReleased(KeyEvent e) {
		
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}
//-----------------------------------------------------
	
}