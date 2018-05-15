package Studs_boll_med_boll;

//import java.awt.color.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//import javafx.scene.paint.Color;



import java.sql.Time;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Player {

	public Point position;
	public int radius;
	protected boolean jetpack;
	protected boolean movingLeft;
	protected boolean movingRight;
	private double x;
	private double y;
	private double w;
	private double h;
	private double vx, vy;
	private int fuel;
	private Color jetpackColor = new Color(20, 128 , 250, 50);
	private LocalDateTime timeScore;
	private static int highScore, lastScore;

	public Player(double x, double y, double w, double h) {
		this.jetpack = false;
		this.movingLeft = false;
		this.movingRight = false;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}
	// Getters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getW() {
		return w;
	}

	public double getH() {
		return h;
	}

	// Setters
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void update() {

		x += vx;
		y += vy;
		vy += 0.4; // gravitation p� spelare
		vx *= 0.9; // hur hal �r isen
		
		if(fuel>0)
			fuel --;

		if (movingLeft) // acceleration �t v�nster
			vx += -0.6;

		if (movingRight) // acceleration �t h�ger
			vx += 0.6;

		// v�gg och takstuds
		if (x > PhysicsCanvas.WIDTH - w / 2 && vx > 0)
			vx = -0.2;
		if (y > PhysicsCanvas.HEIGHT - h / 2 && vy > 0) {
			vy = -0.1;
			y = PhysicsCanvas.HEIGHT - h / 2;
		}
		if (x < radius + w / 2 && vx < 0)
			vx *= -0.2;
		if (y < radius + h / 2 && vy < 0) {
			vy *= -0.2;
			y = radius + h / 2;
		}

		if (jetpack&&fuel<PhysicsCanvas.HEIGHT) {
			vy -= 0.6;
			fuel += 3;
		}

	}
	//timeAdjust anv�nds f�r att justera s� att dina po�ng blir fr�n 0.
	private int timeAdjust = (int)(System.currentTimeMillis()/1000);
	private BufferedImage image;
	
	//g.drawString raden g�r att programmet startar upp p� 4 sekunder ist�llet f�r 1. Vet inte varf�r.
	public void render(Graphics2D g) {
		
		g.setFont(new Font("Arial", Font.BOLD, 14));
		
		lastScore = (int)(System.currentTimeMillis()/1000) - timeAdjust;
		
		g.drawString(lastScore+" s", 10, 20);
		if(lastScore>highScore)
			highScore = lastScore;
		g.drawString("Best: "+highScore+" s", 10, 10);
		
		//jetpackm�tare
		g.setColor(jetpackColor);
		g.fillRect(PhysicsCanvas.WIDTH-25, fuel, 25, PhysicsCanvas.HEIGHT);
	//	g.fillRect(x, y, width, height);
		
		try {
			image = ImageIO.read(new File(
					"/Users/antonblaberg/Documents/programmering/eclipse/TFYA15/src/Studs_boll_med_boll/Images/ghost.png"));
		} catch (IOException ex) {
			System.out.println("Kunde ej hitta sp�kmodell. \n �nskad v�g: /Users/antonblaberg/Documents/programmering/eclipse/TFYA15/src/Studs_boll_med_boll/Images/ghost.png");
			g.fillOval((int) Math.round(x - (w / 2)),
					(int) Math.round(y - (h / 2)), (int) Math.round(w) + 1,
					(int) Math.round(h) + 1);
		}
		//Bild, x, y, bredd, h�jd, on�digt"observer" = null.
		g.drawImage(image, (int) Math.round(x - (w / 2)),
				(int) Math.round(y - (h / 2)), (int) Math.round(w) + 1,
				(int) Math.round(h) + 1, null);
	}

	public boolean colliding(Particle ball) {
		double Dx = x - ball.getX();
		double Dy = y - ball.getY();

		// Hitbox radien runt spelaren �r en kvadrat med samma bredd som
		// spelaren.
		double sumRadius = w / 2 + ball.getR();
		double sqrRadius = sumRadius * sumRadius;

		double distSqr = (Dx * Dx) + (Dy * Dy);

		if (distSqr <= sqrRadius)
			return true;

		return false;
	}

}
