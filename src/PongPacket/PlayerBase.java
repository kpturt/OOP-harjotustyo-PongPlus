package PongPacket;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class PlayerBase implements KeyListener, ActionListener {
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
	public void setPosY(int posY) { this.posY = posY; }

	public int getBoardHeight(){
		return boardHeight;
	}

	public void restartBoard(){
		posY = 210;
		boardHeight = 181;
	}
}
