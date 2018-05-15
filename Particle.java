package Studs_boll_med_boll;

import java.awt.Graphics2D;
import java.awt.Color;

public class Particle {

	private double vx;
	private double vy;
	private double r;
	private Color color;
	private double x;
	private double m;
	private double y;
	private int i = 0;
	private double r_origin;
	private double spawnX;
	
	public Particle(double x, double y, double r, Color color, double vx,
			double vy) {
		this.x = x;
		this.y = y;
		this.r = r;
		r_origin = r;
		this.color = color;
		this.vx = vx;
		this.vy = vy;
		this.m = Math.pow(r, 3);	//Massan ökar jämfört med radien.
		spawnX = x/PhysicsCanvas.WIDTH;
	}

	//Getters. För att hämta värden utifrån andra klasser på en specifik instans av Particle
	public double getY() { return y; }
	public double getX() { return x; }
	public double getR() { return r; }
	public double getVx() {	return vx; }
	public double getVy() { return vy; }
	public double getM() { return m; }

	//Setters. För att sätta värden utifrån andra klasser på en specifik instans av Particle
	public void setVx(double vx) {	this.vx = vx; }
	public void setVy(double vy) {	this.vy = vy; }

	public void update() {
		x += vx;
		y += vy;
		vy += 0.06;

		//vägg och tak krock
		if ((x < r && vx < 0) || ((x > PhysicsCanvas.WIDTH - r) && vx > 0)) {
			vx *= -0.9;
		}
		if ((y < r && vy < 0) || ((y > PhysicsCanvas.HEIGHT - r) && vy > 0)) {
			vy *= -0.9;
		}

		if(r<r_origin)
			r+=2;	//bollen morphas in, mest för skoj.
			
		//När bollen har varit trött och lite inaktiv i 200 tidsenheter och sedan studsar 
		//en sista gång i backen kommer dö ut och spawna om o taket.
		if ((vx + vy) < 1.5) {
			i++;
			if (i > 200) {
				//sista studsen i marken = ny position i taket.
				if ((y > PhysicsCanvas.HEIGHT - r - 1)) {
					y = -100;
					x = spawnX * PhysicsCanvas.WIDTH;
					vy = 0;
					i = 0;
					vx = (vx * 0.2) + 5*rnd();
					r = 0;	//bollen krymps till 0 i radie.
				}
			}
		}
	}

	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) Math.round(x - r), (int) Math.round(y - r),
				(int) Math.round(2 * r), (int) Math.round(2 * r));
	}

	static private double rnd() {
		return Math.random();
	}

	public boolean colliding(Particle ball) {
		double Dx = x - ball.getX();
		double Dy = y - ball.getY();

		double sumRadius = r + ball.getR();
		double sqrRadius = sumRadius * sumRadius;

		double distSqr = (Dx * Dx) + (Dy * Dy);

		if (distSqr <= sqrRadius) 
			return true;
		
		return false;
	}
}
