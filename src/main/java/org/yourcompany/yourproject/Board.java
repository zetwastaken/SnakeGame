package org.yourcompany.yourproject;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JFrame;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Snake snake;
    private Enemies enemies;
    private Treats treats;
    private boolean inGame;
    private JFrame frame;

    public Board(JFrame frame) {
        this.frame = frame;
        initBoard();
    }

    private void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400, 400));

        snake = new Snake();
        enemies = new Enemies();
        treats = new Treats();

        addKeyListener(new TAdapter());

        timer = new Timer(100, this);
        timer.start();

        inGame = true;

        new Thread(snake).start();
        new Thread(enemies).start();
        new Thread(treats).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            drawObjects(g);
        } else {
            drawGameOver(g);
            timer.stop(); // Stop the timer when game is over

            // Navigate to the menu after a delay
            Timer gameOverTimer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new Menu(frame));
                    frame.revalidate();
                    frame.repaint();
                }
            });
            gameOverTimer.setRepeats(false); // Ensure the timer only runs once
            gameOverTimer.start();
        }
    }

    private void drawObjects(Graphics g) {
        snake.draw(g);
        enemies.draw(g);
        treats.draw(g);
    }

    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (400 - metr.stringWidth(msg)) / 2, 400 / 2);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow(); // Request focus when the component is added to the container
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkCollisions();
            checkTreats();
            repaint();
        }
    }

    private void checkCollisions() {
        if (snake.checkCollisionWithWalls() || snake.checkCollisionWithItself() || snake.checkCollisionWithEnemies(enemies.getEnemies())) {
            inGame = false;
        }
    }

    private void checkTreats() {
        if (snake.checkCollisionWithTreat(treats.getTreat())) {
            snake.grow();
            treats.spawnTreat();
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            snake.keyPressed(e);
        }
    }
}
