package org.yourcompany.yourproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class Snake implements Runnable {

    private LinkedList<Point> body;
    private Point direction;
    private boolean growing;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Point(5, 5));
        direction = new Point(1, 0); // initially moving right
        growing = false;
    }

    @Override
    public void run() {
        while (true) {
            move();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void move() {
        Point head = body.getFirst();
        Point newHead = new Point(head.x + direction.x, head.y + direction.y);

        body.addFirst(newHead);

        if (!growing) {
            body.removeLast(); // remove the tail to simulate movement
        } else {
            growing = false; // reset growing flag
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                if (direction.x != 1) {
                    direction = new Point(-1, 0);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction.x != -1) {
                    direction = new Point(1, 0);
                }
                break;
            case KeyEvent.VK_UP:
                if (direction.y != 1) {
                    direction = new Point(0, -1);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction.y != -1) {
                    direction = new Point(0, 1);
                }
                break;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point point : body) {
            g.fillRect(point.x * 10, point.y * 10, 10, 10);
        }
    }

    public void grow() {
        growing = true;
    }

    public boolean checkCollisionWithWalls() {
        Point head = body.getFirst();
        return head.x < 0 || head.x >= 40 || head.y < 0 || head.y >= 40;
    }

    public boolean checkCollisionWithItself() {
        Point head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionWithEnemies(List<Point> enemies) {
        Point head = body.getFirst();
        for (Point enemy : enemies) {
            if (head.equals(enemy)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionWithTreat(Point treat) {
        Point head = body.getFirst();
        return head.equals(treat);
    }
}
