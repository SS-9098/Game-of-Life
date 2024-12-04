package src;

import javax.swing.*;

public class MyFrame extends JFrame
{
    public MyFrame()
    {
        this.setTitle("Game of Life");
        this.setSize(900, 800);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Colors.getBoardColor());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
