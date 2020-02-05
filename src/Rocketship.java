import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	int speed;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	boolean isAlive = true;
	public static BufferedImage rocket;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	public Rocketship (int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
		rocket = loadImage("rocket.png");
	}
	
	void update() {
		super.update();
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
		g.drawImage(rocket, x, y, width, width, null);
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
