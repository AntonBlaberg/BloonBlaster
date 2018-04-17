package BloonBlaster;
import java.awt.Point;

import javax.swing.text.Position;

import sun.awt.WindowIDProvider;

public class Game {

	private Point position;
	public int radius;
	private boolean jetpack;
	private boolean movingLeft;
	private boolean movingRight;
	private int speed;
	private double dx = 0;
	private double dy = 0;

	public Game() {
		this.position = new Point(50, 300);
		this.radius = 20;
		this.jetpack = false;
		this.movingLeft = false;
		this.movingRight = false;
		this.speed = 200;
	}

	public void update(double updateTime) {
		dx = dx * 0.95;		//luftmotstånd
		dy = dy + 0.1;		//gravitation
		
		double movement = this.speed * updateTime * 0.001; // Convert milliseconds to seconds. Exempel movement = 8;
		/*
		if ((movingLeft || movingRight) && (!movingLeft || !movingRight)) {
			dx = (movingLeft ? -movement : movement);
		}
		*/
		//försök på att byta ut den ovanför
		if ((movingLeft || movingRight) && (!movingLeft || !movingRight)) {
			if((movingLeft)&&(Math.abs(dx) < 10)){
				dx = dx - 1;
			}
			else if(Math.abs(dx) < 10)
			dx = dx + 1;	
		}
		//fixa så att det inte finns oändligt med jetpack
		if (jetpack) {
			dy = dy - 0.6;
		}
		if(position.getX()>Component.WIDTH-radius)
			dx = -1;
		if(position.getY()>Component.HEIGHT-radius)
			dy = -1;
		if(position.getX()<radius)
			dx = 1;
		if(position.getY()<radius)
			dy = 1;
		
		System.out.println("X: " + position.getX() + " Y: " + position.getY());
		this.position.translate((int) dx, (int) dy);
	}

	public void move(String dir, boolean val) {
		switch (dir) {
		case "SPACE":
			jetpack = val;
			break;
		case "LEFT":
			movingLeft = val;
			break;
		case "RIGHT":
			movingRight = val;
			break;
		}
	}

	public Point getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
}