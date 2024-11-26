package PongPacket;

import javax.swing.*;
import java.awt.*;

public class GameGraphics extends JPanel {

    private GameLogic gameLogic;

    public GameGraphics(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // background
        g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 600);

        // for drawing a background grid
        int contentWidth = getWidth();
        int contentHeight = getHeight();
        int spacing = 30;
        g.setColor(Color.red);
        for(int i = 1; i <= contentWidth / spacing; i++){
            int xstart = i*spacing;
            g.drawLine(xstart, 0, xstart, contentHeight);
        }
        for(int j = 1; j <= contentHeight / spacing; j++){
            int ystart = j*spacing;
            g.drawLine(0, ystart, contentWidth, ystart);
        }

        // centering line
        g.setColor(Color.yellow);
        g.drawLine(450, 0, 450, 600);
        g.drawLine(0, 300, 900, 300);

        // players and ball
        g.setColor(Color.blue);
        g.fillRect(gameLogic.p1.posX, gameLogic.p1.posY, 31, gameLogic.p1.boardHeight);

        g.setColor(Color.green);
        g.fillRect(gameLogic.cpu.posX, gameLogic.cpu.posY, 31, gameLogic.cpu.boardHeight);

        g.setColor(Color.red);
        g.fillOval(gameLogic.ball.posX, gameLogic.ball.posY, 50, 50);
    }

}
