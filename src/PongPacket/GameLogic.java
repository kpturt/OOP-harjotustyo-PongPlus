package PongPacket;

import java.awt.Rectangle;

public class GameLogic{
	private PlayerBase p1;
	private PlayerBase cpu;
	private Ball ball;
	private Points points;
	private boolean goalScored = false;

	public GameLogic() {
		p1 = new P1(10, 200, 100);
		cpu = new CPU(975, 200, 100);
		ball = new Ball(500, 300, 0, 0);
	}

	Rectangle rectP1 = new Rectangle(p1.posX, p1.posY, 20, p1.boardHeight);
	Rectangle rectCPU = new Rectangle(cpu.posX, cpu.posY, 20, cpu.boardHeight);
	Rectangle rectBall = new Rectangle(ball.posX, ball.posY, 30, 30);

	public void update(){
		// ball movement
		ball.posX += ball.dirX;
		ball.posY += ball.dirY;

		// ball bounce from P1 board
		if(rectBall.intersects(rectP1)) {
			ball.dirX = -ball.dirX;
		}

		// ball bounce from CPU board
		if(rectBall.intersects(rectCPU)) {
			ball.dirX = -ball.dirX;
		}

		// ball bounce from ceiling and floor
		if(ball.posY < 0 || ball.posY > 500) {
			ball.dirY = -ball.dirY;
		}

		// score updates
		if (ball.posX > 1000 || ball.posX < 0) {
			goalScored = true;
			if(ball.posX > 1000){
				points.updateScoreP1();
			} else {
				points.updateScoreCPU();
			}
			System.out.println("POINTS P1: "+ points.returnScoreP1()+" - CPU: "+ points.returnScoreCPU());
		}

		// win checking and board size updates
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
			if(points.scoreP1 == points.scoreCPU && points.scoreP1 != 2 && points.scoreCPU != 2) {
				p1.setBoardHeight(100);
				cpu.setBoardHeight(100);
			}
			if(points.scoreP1 == 2 && points.scoreCPU == 2) {
				p1.setBoardHeight(50);
				cpu.setBoardHeight(50);
			}
			if(points.scoreP1 < points.scoreCPU) {
				p1.setBoardHeight(170);
			}
			if(points.scoreP1 > points.scoreCPU) {
				cpu.setBoardHeight(170);
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