package org.yourcompany.yourproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {

    private JButton startButton;
    private JButton exitButton;
    private JFrame frame;

    public Menu(JFrame frame) {
        this.frame = frame;
        initMenu();
    }

    private void initMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400, 400));

        startButton = new JButton("Start Game");
        exitButton = new JButton("Exit");

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(startButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new Board(frame));
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}