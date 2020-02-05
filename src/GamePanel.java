import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
	ObjectManager manager = new ObjectManager(rocket);
	public static BufferedImage space;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	public GamePanel() {
		timer = new Timer(1000 / fps, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		generalFont = new Font("Arial", Font.PLAIN, 24);
		if (needImage) {
			space = loadImage("space.jpg");
		}
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
		manager.update();
		manager.manageEnemies();
		manager.checkCollision();
		manager.purgeObjects();
		if (rocket.isAlive == false) {
			currentState = END_STATE;
		}
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawImage(space, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		// g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		manager.draw(g);
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
		g.drawString("You killed " + manager.getScore() + " enemies.", 70, 255);
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
			if (currentState == END_STATE) {
				rocket = new Rocketship(175, 500, 50, 50);
				manager = new ObjectManager(rocket);
			}
			currentState++;
			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			manager.addProjectile(new Projectile(rocket.x + 20, rocket.y, 10, 10));
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
	
	BufferedImage loadImage(String imageFile) {
		if (needImage) {
			needImage = false;
			try {
				gotImage = true;
				return ImageIO.read(this.getClass().getResourceAsStream(imageFile));
			} catch (Exception e) {
				System.out.println("You Have,,Failed:" + imageFile);
			}
		}
		return null;
	}
}
