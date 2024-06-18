/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

/**
 *
 * @author zet
 */

import javax.swing.JFrame;


public class SnakeGame extends JFrame {
    public SnakeGame() {
        initUI();
    }

    private void initUI() {
        add(new Menu(this));

        setResizable(false);
        pack();

        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            SnakeGame ex = new SnakeGame();
            ex.setVisible(true);
        });
    }
}
