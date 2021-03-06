import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	boolean isAlive = true;
	
	public Alien (int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	void update() {
		super.update();
		y++;
		if (y > LeagueInvaders.HEIGHT) {
			isAlive = false;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
	}
}
