package PongPacket;

import java.util.Random;

public class Ball {
    protected int posX;
    protected int posY;
    protected int dirY;
    protected int dirX;
    Random rnd = new Random();

    public Ball(int posX, int posY, int dirX, int dirY){
        this.posX = posX;
        this.posY = posY;
        this.dirX = dirX;
        this.dirY = dirY;
    }

    // positions
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

    // directions
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
        // initializes random starting direction for the ball
        rnd.nextInt(4);
        if(rnd.nextInt(4) == 0) {
            dirX = -1;
            dirY = 1;
        }
        else if(rnd.nextInt(4) == 1){
            dirX = 1;
            dirY = -1;
        }
        else if(rnd.nextInt(4) == 2){
            dirX = 1;
            dirY = 1;
        }
        else if(rnd.nextInt(4) == 3){
            dirX = -1;
            dirY = -1;
        }
    }
}
