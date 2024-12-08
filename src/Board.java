package src;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
{
    Board()
    {
        //this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(new GridLayout(100,100));
        //this.setBorder(BorderFactory.createLineBorder(Color.white, 20));
        this.setBackground(Colors.getBoardColor());
        this.setOpaque(true);
        this.setVisible(true);
    }
}
