package PongPacket;

import java.awt.event.ActionListener;
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

	public int getBoardHeight(){
		return boardHeight;
	}

	public abstract void move(); // polymorphic behaviour for movement

	public void restartBoard(){
		posY = 300;
		boardHeight = 100;
	}
}
