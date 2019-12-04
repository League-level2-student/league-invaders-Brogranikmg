import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	final static int WIDTH = 400;
	final static int HEIGHT = 600;
	static JFrame frame;
	GamePanel gamer = new GamePanel();
	
	public LeagueInvaders() {
		frame = new JFrame();
	}
	
	public static void main(String[] args) {
		LeagueInvaders invader = new LeagueInvaders();
		invader.setup();
	}
	
	void setup() {
		frame.add(gamer);
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(gamer);
		gamer.startGame();
	}
}
