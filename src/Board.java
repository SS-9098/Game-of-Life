package src;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
{
    Board()
    {
        this.setSize(800, 700);
        this.setLayout(new GridLayout(100,100));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.gray);
        this.setOpaque(true);
        this.setVisible(true);

    }
}
