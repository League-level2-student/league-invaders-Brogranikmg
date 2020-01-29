import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	long enemyTimer = 0;
	int enemySpawnTime = 100;
	int score = 0;
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	void update() {
		rocket.update();
		for (Projectile projectile : projectiles) {
			projectile.update();
		}
		for (Alien alien : aliens) {
			alien.update();
		}
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		for (Projectile projectile : projectiles) {
			projectile.draw(g);
		}
		for (Alien alien : aliens) {
			alien.draw(g);
		}
	}
	
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	void addAlien(Alien alien) {
		aliens.add(alien);
	}
	
	void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
            addAlien(new Alien(new Random().nextInt(LeagueInvaders.WIDTH - 50), 0, 50, 50));
            enemyTimer = System.currentTimeMillis();
		}
	}
	
	void purgeObjects() {
		for (int i = projectiles.size() - 1; i >= 0; i--) {
			if (projectiles.get(i).isAlive == false) {
				projectiles.remove(i);
			}
		}
		for (int i = aliens.size() - 1; i >= 0; i--) {
			if (aliens.get(i).isAlive == false) {
				aliens.remove(i);
			}
		}
	}
	
	void checkCollision() {
		for (Alien alien : aliens) {
			if (rocket.collisionBox.intersects(alien.collisionBox)) {
				rocket.isAlive = false;
				alien.isAlive = false;
			}
			for (int i = projectiles.size() - 1; i >= 0; i--) {
				if (projectiles.get(i).collisionBox.intersects(alien.collisionBox)) {
					projectiles.get(i).isAlive = false;
					alien.isAlive = false;
					score++;
				}
			}
		}
	}
	
	int getScore() {
		return score;
	}
}
