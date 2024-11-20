package PongPaketti;

public abstract class PlayerBase {
	protected int posX;
	protected int posY;
	protected int boardHeight;

	public PlayerBase(int posX, int posY, int boardHeight){
		this.posX = posX;
		this.posY = posY;
		this.boardHeight = boardHeight;
	}

	public void setBoardHeight(int boardHeight){
		this.boardHeight = boardHeight;
	}

	public int getBoardHeight(){
		return boardHeight;
	}

	public abstract void move(); // polymorphic behaviour for movement
}

//public class P1 {
//	// laudan sijainti ja pituus
//	public int P1_posY = 200;
//	public int P1_posX = 10;
//	public int P1_boardHeight = 100;
//}
//
//class CPU extends P1 {
//	// Tässä tarkoitus luoda tietokonepelaaja joka perii luokan pelaaja jonka tarkoitus on liikkua automaattisesti.
//	// ei valmis
//
//	// laudan sijainti ja pituus
//	public int CPU_posY = 200;
//	public int CPU_posX = 975;
//	public int CPU_boardHeight = 100;
//}
