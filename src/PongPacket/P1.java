package PongPacket;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class P1 extends PlayerBase {
    public P1(int posX, int posY, int boardHeight){
        super(posX, posY, boardHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(posY > 0){
                posY -= 30;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(posY < (600-boardHeight)){
                posY += 30;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
