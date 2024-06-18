package org.yourcompany.yourproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Treats implements Runnable {

    private Point treat;

    public Treats() {
        spawnTreat();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void spawnTreat() {
        Random rand = new Random();
        treat = new Point(rand.nextInt(40), rand.nextInt(40));
    }

    public Point getTreat() {
        return treat;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(treat.x * 10, treat.y * 10, 10, 10);
    }
}
