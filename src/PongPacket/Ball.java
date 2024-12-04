package PongPacket;

import java.util.Random;

public class Ball {
    public int posX;
    public int posY;
    public int dirY;
    public int dirX;
    private int baseSpeed = 2; // starting speed
    private int speedMultiplier = 1; // increases over time
    private long lastSpeedIncreaseTime; // tracks when to increase speed
    private final int SPEED_INCREASE_INTERVAL = 5000; // speed increases every 5 seconds

    Random rnd = new Random();

    public Ball(int posX, int posY, int dirX, int dirY){
        this.posX = posX;
        this.posY = posY;
        this.dirX = dirX;
        this.dirY = dirY;
        this.lastSpeedIncreaseTime = System.currentTimeMillis(); // initialize timer
    }

    // position coordinates
    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosX(int newPosX){
        this.posX = newPosX;
    }

    public void setPosY(int newPosY){
        this.posY = newPosY;
    }

    // direction
    public int getDirX(){
        return dirX;
    }

    public int getDirY(){
        return dirY;
    }

    public void setDirX(int newDirX){
        this.dirX = newDirX;
    }

    public void setDirY(int newDirY){
        this.dirY = newDirY;
    }

    // restarting position and direction
    public void restartBall(){
        posX = 425;
        posY = 275;
        speedMultiplier = 1; // reset speed multiplier when round restarts
        lastSpeedIncreaseTime = 0;

        // initializes a random starting direction for the ball on round start
        rnd.nextInt(4);
        if(rnd.nextInt(4) == 0) {
            dirX = -2;
            dirY = 2;
        }
        else if(rnd.nextInt(4) == 1){
            dirX = 2;
            dirY = -2;
        }
        else if(rnd.nextInt(4) == 2){
            dirX = 2;
            dirY = 2;
        }
        else if(rnd.nextInt(4) == 3){
            dirX = -2;
            dirY = -2;
        }
    }

    // update ball speed based on elapsed time
    public void updateSpeed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpeedIncreaseTime >= SPEED_INCREASE_INTERVAL) {
            speedMultiplier++;
            dirX = (dirX > 0 ? 1 : -1) * baseSpeed * speedMultiplier;
            dirY = (dirY > 0 ? 1 : -1) * baseSpeed * speedMultiplier;
            lastSpeedIncreaseTime = currentTime; // reset the timer
        }
    }
}
