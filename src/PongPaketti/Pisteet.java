package PongPaketti;

public class Pisteet extends Pelattavuus {
	
	// pelaajien pistetilanne
	public int scoreVas = 0;
	public int scoreOik = 0;

//---------------------------------
	
	// tarkistaa onko maali tehty,
	// metodi ei kuitenkaan k�yt�ss� t�ll� hetkell�
	public void checkIfScore() {
		System.out.println("score checked");
	}
	
	public void update() {
		this.checkIfScore();
	}

//---------------------------------
	
	// lis�� vasemmalle pelaajalle pisteen
	public void updateScoreVas(){
			scoreVas += 1;
	}
	
	// lis�� oikealle pelaajalle pisteen
	public void updateScoreOik(){
			scoreOik += 1;
		
	}
//----------------------------------
	
	// palauttaa vasemman pelaajan pisteet
		public int returnScoreVas(){
			return scoreVas;
		}
		
	// palauttaa oikean pelaajan pisteet
	public int returnScoreOik(){
		return scoreOik;
	}
	
}