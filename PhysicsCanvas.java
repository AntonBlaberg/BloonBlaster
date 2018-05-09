package Studs_boll_med_boll;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
/**
 * 
 * Här är programmet BloonBlaster!
 * Kommentererna är på svenska vilket kan messa up en hel del. Vi hoppas på det bästa.
 * BG4 VT2018 
 * Fysikaliska Modeller TFYA15
 * @author antonblaberg
 *
 */


public class PhysicsCanvas extends Canvas implements Runnable, KeyListener{

	public void startGame(){
		p1 = new Particle(WIDTH*0.15, 71, WIDTH/15*rnd(), Color.RED, -5, -rnd());
		p2 = new Particle(WIDTH*0.35, 71, WIDTH/15*rnd(), Color.BLUE, 7, -rnd());
		p3 = new Particle(WIDTH*0.50, 71, WIDTH/15*rnd(), Color.GREEN, -rnd(), -rnd());
		p4 = new Particle(WIDTH*0.65, 71, WIDTH/15*rnd(), Color.YELLOW, -rnd(), -rnd());
		p5 = new Particle(WIDTH*0.85, 71, WIDTH/15*rnd(), Color.ORANGE, -rnd(), -rnd());
		game = new Player(WIDTH-30, HEIGHT-60, 30, 60);
	}
	
	private static final long serialVersionUID = 1337L; 	//Måste vara här enligt rad 11.

	public Player game;
	Collision col = new Collision();
	private boolean running;
	private Particle p1, p2, p3, p4 ,p5;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 600;
	public PhysicsCanvas() {

		Dimension d = new Dimension(1200, 600);
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);

		startGame();
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

		//bakgrundsbild i spelet
		g.setColor(Color.gray);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		p1.render(g);
		p2.render(g);
		p3.render(g);
		p4.render(g);
		p5.render(g);
		game.render(g);
		strategy.show();
	}
// kickar igår KeyListenern
	private void update() {
		while(true){
			addKeyListener(this);
			break;
		}
		p1.update();
		p2.update();
		p3.update();
		p4.update();
		p5.update();
		game.update();

		if(p1.colliding(p2))
			col.krock(p1,p2);
		
		if(p1.colliding(p3)) 
			col.krock(p1,p3);
		
		if(p1.colliding(p4)) 
			col.krock(p1,p4);
		
		if(p1.colliding(p5)) 
			col.krock(p1,p5);
		
		if(p2.colliding(p3)) 
			col.krock(p2,p3);
		
		if(p2.colliding(p4)) 
			col.krock(p2,p4);
		
		if(p2.colliding(p5)) 
			col.krock(p2,p5);
		
		if(p3.colliding(p4)) 
			col.krock(p3,p4);
		
		if(p3.colliding(p5)) 
			col.krock(p3,p5);
		
		if(p4.colliding(p5)) 
			col.krock(p4,p5);
		
		
		if(game.colliding(p1)||game.colliding(p2)||game.colliding(p3)||game.colliding(p4)||game.colliding(p5)){
			System.out.println("#######YOU LOST THE GAME#######"); 
			Restart();
		}
		
		// kallar på collision-metoder. (alla individuellt /oscar)
		// boll mot boll, boll mot vägg
	}

	private void Restart() {
		System.out.println("restarting...");
		game.setX(WIDTH-game.getW()/2);
		game.setY(HEIGHT-game.getH()/2);
		startGame();
	}
	
	//slumpar ett decimaltal från 0-1.
	public double rnd(){
		return Math.random();
	}
	
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("BLOON BLASTER");
		PhysicsCanvas physics = new PhysicsCanvas();
		myFrame.add(physics);
		myFrame.pack();
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		physics.start();
		myFrame.setLocationRelativeTo(null);
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent event) {
		System.out.println("Key pressed:"+ event.getKeyCode());
		if(event.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE)
			game.jetpack = true;
		
		if(event.getKeyCode() == event.VK_LEFT)
			game.movingLeft = true;
		
		if(event.getKeyCode() == event.VK_RIGHT)
			game.movingRight = true;
	}
	
	@Override
	public void keyReleased(java.awt.event.KeyEvent event) {
		if(event.getKeyCode() == event.VK_SPACE)
			game.jetpack = false;
		
		if(event.getKeyCode() == event.VK_LEFT)
			game.movingLeft = false;
		
		if(event.getKeyCode() == event.VK_RIGHT)
			game.movingRight = false;
	}

	//Denna metod måste finnas med om Keylistener implementeras.
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {

	}
	
}