import java.awt.Point;

public class Game {

	private Point position;
	public int radius;
	private boolean jetpack;
	private boolean movingLeft;
	private boolean movingRight;
	private int speed;

	public Game() {
		this.position = new Point(50, 300);
		this.radius = 20;
		this.jetpack = false;
		this.movingLeft = false;
		this.movingRight = false;
		this.speed = 200;
	}

	public void update(double updateTime) {
		double dx = 0;
		double dy = 0;
		double movement = this.speed * updateTime * 0.001; // Convert milliseconds to seconds
		if ((movingLeft || movingRight) && (!movingLeft || !movingRight)) {
			dx = (movingLeft ? -movement : movement);
		}
		if (jetpack) {
			dy = -movement;
		}

		this.position.translate((int) dx, (int) dy);
	}

	public void move(String dir, boolean val) {
		switch (dir) {
		case "FLY":
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