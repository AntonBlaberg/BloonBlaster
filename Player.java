package Studs_boll_med_boll;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Player {

	public Point position;
	public int radius;
	public boolean jetpack;
	public boolean movingLeft;
	public boolean movingRight;
	private double x;
	private double y;
	private double w;
	private double h;
	private double vx, vy;
	
	public Player(double x, double y, double w, double h) {
		this.jetpack = false;
		this.movingLeft = false;
		this.movingRight = false;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}
	
	//Getters
	public double getX() { return x; }
	public double getY() { return y; }
	public double getW() { return w; }
	public double getH() { return h; }
	
	//Setters
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	
	
	
	public void update() {

		x += vx;
		y += vy;
		vy += 0.4;	//gravitation pŒ spelare
		vx *= 0.9;	//hur hal Šr isen
		
		if(movingLeft)	//acceleration Œt vŠnster
			vx += -0.6;	
		
		if(movingRight)	//acceleration Œt hšger
			vx += 0.6;
		
		//vŠgg och takstuds
		if(x>PhysicsCanvas.WIDTH-w/2&& vx > 0)
			vx = -0.2;
		if(y>PhysicsCanvas.HEIGHT-h/2 && vy > 0){
			vy = -0.1; 
			y = PhysicsCanvas.HEIGHT-h/2;
		}
		if(x<radius+w/2 && vx < 0)
			vx *= -0.2;
		if(y<radius+h/2 && vy < 0){
			vy *= -0.2;
			y = radius+h/2;
		}
		
		if(jetpack){
			vy -= 0.6;
		}
		
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillOval((int)Math.round(x-(w/2)), (int)Math.round(y-(h/2)), (int)Math.round(w), (int)Math.round(h));
	}
	
	public boolean colliding(Particle ball)
	{
	    double Dx = x - ball.getX();
	    double Dy = y - ball.getY();

	    //Hitbox radien runt spelaren Šr en kvadrat med samma bredd som spelaren.
	    double sumRadius = w/2 + ball.getR();
	    double sqrRadius = sumRadius * sumRadius;

	    double distSqr = (Dx * Dx) + (Dy * Dy);

	    if (distSqr <= sqrRadius)
	        return true;
	    
	    return false;
	}
	
}
