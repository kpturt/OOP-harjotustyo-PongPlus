package PongPacket;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class P1 extends PlayerBase {
    public P1(int posX, int posY, int boardHeight){
        super(posX, posY, boardHeight);
    }

    @Override
    public void move(){
        // movement logic
        //p1.posY += 50;
        //p1.posY -= 50;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_UP) {
//            if(p1.posY < 10) {
//                p1.posY = 10;
//            }
//            else {
//                moveP1_Down();
//            }
//        }
//
//        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
//            if(p1.posY + p1.boardHeight >= 500) {
//                p1.posY = 495 - p1.boardHeight;
//            }
//            else {
//                moveP1_Up();
//            }
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
