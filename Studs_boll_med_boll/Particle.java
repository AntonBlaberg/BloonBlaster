package Studs_boll_med_boll;

import java.awt.Graphics2D;
import java.awt.Color;


public class Particle {
	
	private double vx;
	private double vy;
	private double r;
	private Color color;
	private double x;
	public double getX() {
		return x;
	}
	private double m;
	private double y;
	public double getY() {
		return y;
	}

	public Particle(double x, double y, double r, Color color, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
		this.vx = vx;
		this.vy = vy;
		this.m = Math.pow(r, 1.3);
	}
	/*
	public void krock() {
		vx *= -1;
		vy *= -1;
	}
	*/
	public double getR() { return r; }
	
	public double getVx() { return vx; }
	
	public double getM() { return m; }
	
	public void setVx(double vx){
		this.vx = vx;
	}
	
	public void setVy(double vy){
		this.vy = vy;
	}
	
	public void update() {
		x += vx;
		y += vy;
		
		if (x<r&&vx<0) {
			vx *= -1;
		}
		if (y<r&&vy<0) {
			vy *= -1;
		}
		if((x>PhysicsCanvas.WIDTH-r)&&vx>0) {
			vx *= -1;
		}
		if((y>PhysicsCanvas.HEIGHT-r)&&vy>0) {
			vy *= -1;
		}
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)Math.round(x-r), (int)Math.round(y-r), (int)Math.round(2*r), (int)Math.round(2*r));
	}
}
