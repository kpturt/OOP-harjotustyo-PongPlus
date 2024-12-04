package PongPacket;

import javax.swing.*;
import java.awt.Rectangle;

public class GameLogic {
	public PlayerBase p1;
	public PlayerBase cpu;
	public Ball ball;
	public Points points;
	public boolean goalScored = false;
	public Rectangle rectP1;
	public Rectangle rectCPU;
	public Rectangle rectBall;

	public GameLogic() {
		points = new Points();

		p1 = new P1(0, 210, 181);
		rectP1 = new Rectangle(p1.posX, p1.posY, 31, p1.boardHeight);

		cpu = new CPU(870, 210, 181);
		rectCPU = new Rectangle(cpu.posX, cpu.posY, 31, cpu.boardHeight);

		ball = new Ball(425, 275, 0, 0);
		rectBall = new Rectangle(ball.posX, ball.posY, 30, 30);
		ball.restartBall();
	}

	public void update(){

		// P1 paddle movement
		rectP1.setLocation(p1.posX, p1.posY);

		// update CPU paddle position to follow the ball
		((CPU) cpu).move(ball.posY);

		// predict ball's next position (for better collision detection)
		int nextBallPosX = ball.posX + ball.dirX;
		int nextBallPosY = ball.posY + ball.dirY;

		// update CPU paddle position first (to follow the ball)
//		int targetCPUY = ball.posY + ball.dirY - (cpu.getBoardHeight() / 2);
//		cpu.posY = Math.max(0, Math.min(550 - cpu.getBoardHeight(), targetCPUY));
		rectCPU.setLocation(cpu.posX, cpu.posY);

		// handle ball-paddle collisions
		if (new Rectangle(nextBallPosX, ball.posY, 50, 50).intersects(rectP1)) {
			ball.dirX = Math.abs(ball.dirX); // Ensure ball bounces to the right
			ball.posX = rectP1.x + rectP1.width; // Correct position to paddle's edge
		} else if (new Rectangle(nextBallPosX, ball.posY, 50, 50).intersects(rectCPU)) {
			ball.dirX = -Math.abs(ball.dirX); // Ensure ball bounces to the left
			ball.posX = rectCPU.x - 50; // Correct position to paddle's edge
		}



		// update ball's vertical position
		if (nextBallPosY < 0 || nextBallPosY > 550) {
			ball.dirY = -ball.dirY; // Bounce off the top or bottom walls
		} else {
			ball.posY = nextBallPosY;
		}

		// update ball's horizontal position (if no paddle collision occurred)
		ball.posX += ball.dirX;

		// update ball's rectangle for collision checks
		rectBall.setLocation(ball.posX, ball.posY);

		// update ball speed over time
		ball.updateSpeed();

		// score updates
		if (ball.posX > 930 || ball.posX < -30) {
			goalScored = true;
			if(ball.posX > 930){
				points.updateScoreP1();
			} else {
				points.updateScoreCPU();
			}
			ball.restartBall();
			System.out.println("POINTS P1: "+ points.returnScoreP1()+" - CPU: "+ points.returnScoreCPU());
		}

		// win checking and board size updates based on score
		if(goalScored){
			if(points.scoreP1 == 3 || points.scoreCPU == 3) {
				if(points.scoreP1 > points.scoreCPU){
					System.out.println("P1 WINS!");
				} else {
					System.out.println("CPU WINS!");
				}
				System.out.println("------------------------------------------------------");
				newGame();
			}
			if(points.scoreP1 == 2 && points.scoreCPU == 2) {
				p1.setBoardHeight(121);
				p1.setPosY(240);
				cpu.setBoardHeight(121);
				cpu.setPosY(240);
			}
			if(points.scoreP1 < points.scoreCPU) {
				p1.setBoardHeight(241);
				p1.setPosY(180);
				cpu.setBoardHeight(181);
				cpu.setPosY(210);
			}
			if(points.scoreP1 > points.scoreCPU) {
				p1.setBoardHeight(181);
				p1.setPosY(210);
				cpu.setBoardHeight(241);
				cpu.setPosY(180);
			}

			goalScored = false;
		}
	}

	public void newGame(){
		points.restartPoints();
		ball.restartBall();
		p1.restartBoard();
		cpu.restartBoard();
	}
}