import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
	private static double lastUpdate;

	public static void main(String[] args) {
		final Game game = new Game();
		JFrame frame = new JFrame("BloonBlaster");
		final Component component = new Component(game);
		frame.setLayout(new BorderLayout()); // Add standard layout to frame
		frame.add(component, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Set action to exit button
		frame.pack(); // Set frame size to the minimum size that can contain its components
		lastUpdate = System.currentTimeMillis();

		AbstractAction doOneStep = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double currentTime = System.currentTimeMillis();
				game.update(currentTime - lastUpdate);
				lastUpdate = currentTime;
				component.repaint();
			}
		};

		Timer timer = new Timer(17, doOneStep); // 17 updates/second is approx. 60 Hz
		timer.setCoalesce(true); // Skip a tick if last tick isn't finished
		timer.start();

		frame.setVisible(true);

	}

}
