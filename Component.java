import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Component extends JComponent {
	public Game game;
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	public BufferedImage player;

	public Component(Game game) {
		this.game = game;
		setInput();
		player = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.fillRect(0, 0, WIDTH, HEIGHT); // Draw background
		Point pos = game.getPosition();
		g2.drawImage(player, pos.x - game.radius, pos.y - game.radius, game.radius * 2, game.radius * 2, null); // Draw
																												// player
	}

	private void setInput() {
		final String[] directions = { "FLY", "LEFT", "RIGHT" };
		for (final String direction : directions) {
			getInputMap().put(KeyStroke.getKeyStroke(direction), direction);
			getActionMap().put(direction, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game.move(direction, true);
				}
			});
			getInputMap().put(KeyStroke.getKeyStroke("released " + direction), "no " + direction);
			getActionMap().put("no " + direction, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game.move(direction, false);
				}
			});
		}
	}
}