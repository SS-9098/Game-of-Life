package src;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton
{
    MyButton()
    {
        super();
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        this.setOpaque(true);
        this.setVisible(true);
    }
}
