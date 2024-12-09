package src;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame
{
    public MyFrame()
    {
        this.setTitle("Game of Life");
        this.setSize(900, 800);
        this.setLayout(new BorderLayout(0,0));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Colors.getBoardColor());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
