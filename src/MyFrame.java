package src;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame
{
    public MyFrame()
    {
        this.setTitle("Game of Life");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.gray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        MyFrame frame = new MyFrame();
    }
}
