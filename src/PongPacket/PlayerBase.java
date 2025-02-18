package PongPacket;

import java.awt.event.ActionListener;

public abstract class PlayerBase implements ActionListener {
	protected int posX;
	protected int posY;
	protected int boardHeight;

	public PlayerBase(int posX, int posY, int boardHeight) {
		this.posX = posX;
		this.posY = posY;
		this.boardHeight = boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	// abstract method for movement
	public abstract void move(int ballY);

	// restarts board to its original position and height
	public void restartBoard() {
		posY = 210;
		boardHeight = 181;
	}
}
