package PongPacket;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CPU extends PlayerBase{
    public CPU(int posX, int posY, int boardHeight){
        super(posX, posY, boardHeight);
    }

    public void move(int ballY) {
        int centerY = posY + boardHeight / 2; // CPU paddle's center position

        if (centerY < ballY) {
            posY += Math.min(5, ballY - centerY); // Move down towards ball
        } else if (centerY > ballY) {
            posY -= Math.min(5, centerY - ballY); // Move up towards ball
        }

        // Ensure paddle stays within the screen bounds
        if (posY < 0) {
            posY = 0;
        } else if (posY + boardHeight > 600) { // Assuming screen height = 600
            posY = 600 - boardHeight;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
