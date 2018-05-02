package Studs_boll_med_boll;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

public class PhysicsCanvas extends Canvas implements Runnable {

	Collision col = new Collision();
	private boolean running;
	private Particle p1;
	private Particle p2;
	private Particle p3;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public PhysicsCanvas() {

		Dimension d = new Dimension(800, 600);
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);

		p1 = new Particle(200, 300, 20, Color.RED, 0, 0);
		p2 = new Particle(500, 300, 120, Color.BLUE, -2, 0);
		p3 = new Particle(100, 300, 10, Color.GREEN, 17, 0);
	}

	@Override
	public void run() {
		while (running) {
			update();
			render();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}
		}
	}

	public void start() {
		if (!running) {
			Thread t = new Thread(this);
			createBufferStrategy(3);
			running = true;
			t.start();
		}
	}

	private void render() {
		BufferStrategy strategy = getBufferStrategy();
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		p1.render(g);
		p2.render(g);
		p3.render(g);

		if (Math.sqrt((Math.pow((p1.getX() - p2.getX()), 2) + Math.pow(
				p1.getY() - p2.getY(), 2))) < p1.getR() + p2.getR()) 
		{
			col.krock(p1,p2);
		}
		if (Math.sqrt((Math.pow((p1.getX() - p3.getX()), 2) + Math.pow(
				p1.getY() - p3.getY(), 2))) < p1.getR() + p3.getR()) 
		{
			col.krock(p1,p3);
		}
		if (Math.sqrt((Math.pow((p2.getX() - p3.getX()), 2) + Math.pow(
				p2.getY() - p3.getY(), 2))) < p2.getR() + p3.getR()) 
		{
			col.krock(p1,p3);
		}
		
		strategy.show();
	}


	private void update() {
		p1.update();
		p2.update();
		p3.update();
	
		// kallar pŒ collision-metoder. (alla individuellt /oscar)
		// boll mot boll, boll mot vŠgg
	}

	public static void main(String[] args) {
		JFrame myFrame = new JFrame("My physics canvas");
		PhysicsCanvas physics = new PhysicsCanvas();
		myFrame.add(physics);
		myFrame.pack();
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		physics.start();
		myFrame.setLocationRelativeTo(null);
	}

}
