package PongPacket;

import javax.swing.*;
import java.awt.*;

public class GameGraphics extends JPanel {

    private GameLogic gameLogic;

    public GameGraphics(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // background
        g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 600);

        // dotted center line
        g.setColor(Color.white);
        int lineX = 450;         // center of the screen
        int segmentHeight = 10;  // height of each dot
        int gap = 10;            // gap between the dots
        for (int y = 0; y < getHeight(); y += segmentHeight + gap) {
            g.fillRect(lineX, y, 5, segmentHeight);
        }

        // players
        g.setColor(Color.white);
        g.fillRect(gameLogic.p1.posX, gameLogic.p1.posY, 31, gameLogic.p1.boardHeight);
        g.fillRect(gameLogic.cpu.posX, gameLogic.cpu.posY, 31, gameLogic.cpu.boardHeight);

        // ball
        g.setColor(Color.green);
        g.fillOval(gameLogic.ball.posX, gameLogic.ball.posY, 50, 50);

        // background grid for better positional calculating
//        int contentWidth = getWidth();
//        int contentHeight = getHeight();
//        int spacing = 30;
//        g.setColor(Color.white);
//        for (int i = 1; i <= contentWidth / spacing; i++) {
//            int xstart = i*spacing;
//            g.drawLine(xstart, 0, xstart, contentHeight);
//        }
//        for (int j = 1; j <= contentHeight / spacing; j++) {
//            int ystart = j*spacing;
//            g.drawLine(0, ystart, contentWidth, ystart);
//        }

        // centering lines for better positional calculating
//        g.setColor(Color.white);
//        g.drawLine(450, 0, 450, 600);
//        g.drawLine(0, 300, 900, 300);
    }
}
