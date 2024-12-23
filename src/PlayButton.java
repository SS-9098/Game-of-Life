package src;

import javax.swing.*;
import java.awt.*;

public class PlayButton
{
    public JPanel play() {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.getBoardColor());
        panel.setBorder(BorderFactory.createLineBorder(Colors.getBorderColor(), 1));
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setLayout(new BorderLayout());
        panel.setOpaque(true);
        panel.setVisible(true);
        return panel;
    }

    public JButton playButton() {
        JButton button = new JButton(">");
        return getjButton(button);
    }

    public JButton next() {
        JButton button = new JButton("=>");
        return getjButton(button);
    }

    public JButton prev() {
        JButton button = new JButton("<=");
        return getjButton(button);
    }

    private JButton getjButton(JButton button)
    {
        button.setBackground(Colors.getButtonColor());
        button.setBorder(BorderFactory.createLineBorder(Colors.getBorderColor(), 1));
        button.setPreferredSize(new Dimension(100, 50));
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setOpaque(true);
        button.setVisible(true);
        button.setFocusable(false);
        return button;
    }

    public JCheckBox borderSwitch() {
        JCheckBox checkBox = new JCheckBox("Border", true);
        checkBox.setBackground(Colors.getBoardColor());
        checkBox.setFont(new Font("Arial", Font.BOLD, 20));
        checkBox.setOpaque(false);
        checkBox.setVisible(true);
        checkBox.setFocusable(false);
        return checkBox;
    }
}
