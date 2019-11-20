import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	int w = 400;
	int h = 600;
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
		frame.getContentPane().setPreferredSize(new Dimension(w, h));
        frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamer.startGame();
	}
}
