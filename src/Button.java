package src;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton
{
    Button()
    {
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setOpaque(true);
        this.setVisible(true);
    }
}
