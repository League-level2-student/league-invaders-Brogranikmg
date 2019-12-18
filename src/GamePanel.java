import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	int fps = 60;
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font generalFont;
	Rocketship rocket = new Rocketship(175, 500, 50, 50);
	
	
	public GamePanel() {
		timer = new Timer(1000 / fps, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		generalFont = new Font("Arial", Font.PLAIN, 24);
	}
	
	void startGame() {
		timer.start();
	}
	
	void updateMenuState() {
		
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setColor(Color.ORANGE);
		g.setFont(titleFont);
		g.drawString("LEG EVADERS", 30, 75);
		g.setFont(generalFont);
		g.drawString("Press ENTER to begin.", 70, 150);
		g.drawString("Press SPACE for instructions.", 32, 180);
	}
	
	void updateGameState() {
		rocket.update();
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		rocket.draw(g);
		g.setColor(Color.WHITE);
	}
	
	void updateEndState() {
		
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME OVER!", 45, 150);
		g.setFont(generalFont);
		g.drawString("You have died.", 110, 180);
		g.drawString("You killed $ enemies.", 70, 255);
		g.drawString("Press ENTER to try again.", 50, 330);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState++;
			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			rocket.up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			rocket.up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rocket.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
