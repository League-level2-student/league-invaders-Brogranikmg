import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	int speed;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	public Rocketship (int x, int y, int width, int height, boolean isAlive) {
		super(x, y, width, height, isAlive);
		speed = 5;
	}
	
	void update() {
		if (up) {
			y -= speed;
		}
		if (down) {
			y += speed;
		}
		if (left) {
			x -= speed;
		}
		if (right) {
			x += speed;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
