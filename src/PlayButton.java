package src;

import javax.swing.*;
import java.awt.*;

public class PlayButton
{
    public JPanel play() {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.getBoardColor());
        panel.setBorder(BorderFactory.createLineBorder(Colors.getBorderColor(), 1));
        panel.setLayout(new BorderLayout());
        panel.setOpaque(true);
        panel.setVisible(true);
        return panel;
    }

    public JButton playButton() {
        JButton button = new JButton();
        button.setBackground(Colors.getBoardColor());
        button.setBorder(BorderFactory.createLineBorder(Colors.getBorderColor(), 1));
        button.setOpaque(true);
        button.setVisible(true);
        return button;
    }

    public JButton next() {
        JButton button = new JButton();
        button.setBackground(Colors.getBoardColor());
        button.setBorder(BorderFactory.createLineBorder(Colors.getBorderColor(), 1));
        button.setOpaque(true);
        button.setVisible(true);
        return button;
    }
}
