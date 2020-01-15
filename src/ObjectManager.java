import java.awt.Graphics;

public class ObjectManager {
	Rocketship rocket;
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	void update() {
		rocket.update();
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
	}
}
