package PongPacket;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class GameGraphics extends JPanel {

    //private GameLogic gameLogic;

    public GameGraphics(GameLogic gameLogic) {
        //this.gameLogic = gameLogic;
    }

    public void paint(Graphics g){
        g.setColor(Color.yellow);
		g.fillRect(0, 500, 1000, 5);
    }

}
