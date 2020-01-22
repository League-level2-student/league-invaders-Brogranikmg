import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	boolean isAlive = true;
	
	public Alien (int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	void update() {
		y++;
	}
	
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
	}
}
