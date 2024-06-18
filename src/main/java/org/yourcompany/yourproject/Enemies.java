package org.yourcompany.yourproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemies implements Runnable {

    private List<Point> enemies;
    private Random random;

    public Enemies() {
        enemies = new ArrayList<>();
        random = new Random();
        spawnEnemies();
    }

    private void spawnEnemies() {
        enemies.add(new Point(random.nextInt(40), random.nextInt(40)));
        enemies.add(new Point(random.nextInt(40), random.nextInt(40)));
        enemies.add(new Point(random.nextInt(40), random.nextInt(40)));
        enemies.add(new Point(random.nextInt(40), random.nextInt(40)));
        enemies.add(new Point(random.nextInt(40), random.nextInt(40)));
        enemies.add(new Point(random.nextInt(40), random.nextInt(40)));
        enemies.add(new Point(random.nextInt(40), random.nextInt(40)));
        enemies.add(new Point(random.nextInt(40), random.nextInt(40))); // Initial enemy spawn
    }

    @Override
    public void run() {
        while (true) {
            moveEnemies();
            try {
                Thread.sleep(500); // Adjust enemy movement speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Point enemy = enemies.get(i);
            int moveX = random.nextInt(3) - 1; // Random movement in x direction (-1, 0, 1)
            int moveY = random.nextInt(3) - 1; // Random movement in y direction (-1, 0, 1)

            // Ensure enemy stays within bounds (0-39 for a 40x40 board)
            int newX = Math.max(0, Math.min(39, enemy.x + moveX));
            int newY = Math.max(0, Math.min(39, enemy.y + moveY));

            enemies.set(i, new Point(newX, newY));
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        for (Point enemy : enemies) {
            g.fillRect(enemy.x * 10, enemy.y * 10, 10, 10);
        }
    }

    public List<Point> getEnemies() {
        return enemies;
    }
}
