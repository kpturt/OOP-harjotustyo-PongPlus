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
		cpu = new CPU(870, 210, 181);
		ball = new Ball(425, 275, 0, 0);
		ball.restartBall();
	}

	public void update(){

		// P1 paddle movement
		rectP1 = new Rectangle(p1.posX, p1.posY, 31, p1.boardHeight);
		rectP1.setLocation(p1.posX, p1.posY);

		// update CPU paddle position
		rectCPU = new Rectangle(cpu.posX, cpu.posY, 31, cpu.boardHeight);
		((CPU) cpu).move(ball.posY);
		rectCPU.setLocation(cpu.posX, cpu.posY);

		// predict ball's next position (for better collision detection)
		int nextBallPosX = ball.posX + ball.dirX;
		int nextBallPosY = ball.posY + ball.dirY;

		// update ball's vertical position and handle floor and ceiling collision
		if (nextBallPosY < 0 || nextBallPosY > 550) {
			ball.dirY = -ball.dirY; // Bounce off the top or bottom walls
		}

		// handle ball-paddle collisions
		Rectangle nextBallRect = new Rectangle(nextBallPosX, nextBallPosY, 50, 50);
		if (nextBallRect.intersects(rectP1)) {
			handlePaddleCollision(rectP1);
		} else if (nextBallRect.intersects(rectCPU)) {
			handlePaddleCollision(rectCPU);
		}

		// update ball's horizontal position
		ball.posX += ball.dirX;
		ball.posY += ball.dirY;
		// update ball speed over time
		ball.updateSpeed();

		// score updates if goal occurs
		if (ball.posX > 950 || ball.posX < -50) {
			goalScored = true;
			if(ball.posX > 950){
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

	private void handlePaddleCollision(Rectangle paddleRect) {

		Rectangle ballRect = new Rectangle(ball.posX, ball.posY, 50, 50);

		if (ballRect.intersects(paddleRect)) {
			// Calculate paddle bounds
			int paddleTop = paddleRect.y;
			int paddleBottom = paddleRect.y + paddleRect.height;

			// Ball center coordinates
			int ballCenterY = ball.posY + 25;
			int ballCenterX = ball.posX + 25;

			if (ballCenterY <= paddleTop) {
				// Ball hits the top of the paddle
				ball.dirY = -ball.dirY; // Ensure upward bounce
				ball.posY = paddleTop - 50; // Correct ball position
			} else if (ballCenterY >= paddleBottom) {
				// Ball hits the bottom of the paddle
				ball.dirY = -ball.dirY; // Ensure downward bounce
				ball.posY = paddleBottom; // Correct ball position
			} else {
				// Ball hits the paddle's face
				ball.dirX = -ball.dirX;

				// Adjust ball position to avoid overlap
				if (paddleRect == rectP1) {
					ball.posX = rectP1.x + rectP1.width; // Position to the right of P1 paddle
				} else if (paddleRect == rectCPU) {
					ball.posX = rectCPU.x - 50;         // Position to the left of CPU paddle
				}
			}
		}
	}

	public void newGame(){
		points.restartPoints();
		ball.restartBall();
		p1.restartBoard();
		cpu.restartBoard();
	}
}