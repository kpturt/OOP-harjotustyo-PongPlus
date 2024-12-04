package PongPacket;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CPU extends PlayerBase{
    public CPU(int posX, int posY, int boardHeight){
        super(posX, posY, boardHeight);
    }

    public void move(int ballY) {
        int cpuCenterY = (posY+boardHeight)/2;
        int ballCenterY = ballY+15;

        if (ballCenterY > cpuCenterY) { // move up towards ball
            posY -= cpuCenterY - ballCenterY;
        } else if (ballCenterY < cpuCenterY) { // move down towards ball
            posY += ballCenterY - cpuCenterY;
        }

        if(posY < 0){
            posY = 0;
        } else if (posY + boardHeight > 600){
            posY = 600-boardHeight;
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
