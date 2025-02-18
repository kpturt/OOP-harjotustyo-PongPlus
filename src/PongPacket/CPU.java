package PongPacket;

import java.awt.event.ActionEvent;

public class CPU extends PlayerBase {
    public CPU(int posX, int posY, int boardHeight) {
        super(posX, posY, boardHeight);
    }

    private int movementCooldown = 5; // frames between each move
    private int cooldownCounter = 0; // tracks cooldown

    @Override
    public void move(int ballY) {
        // movement cooldown for simple CPU difficulty handling
        if (cooldownCounter < movementCooldown) {
            cooldownCounter++;
            return; // skip movement
        }
        cooldownCounter = 0; // reset movement cooldown

        int cpuCenterY = (posY+boardHeight)/2;
        int ballCenterY = ballY+25;

        // CPU movement
        if (ballCenterY > cpuCenterY) {
            posY -= cpuCenterY - ballCenterY; // move up towards ball
        } else if (ballCenterY < cpuCenterY) {
            posY += ballCenterY - cpuCenterY; // move down towards ball
        }

        // prevent paddle from going out of bounds
        if (posY < 0) {
            posY = 0;
        } else if (posY + boardHeight > 600) {
            posY = 600-boardHeight;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
