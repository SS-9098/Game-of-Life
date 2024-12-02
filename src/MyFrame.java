package src;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame
{
    public MyFrame()
    {
        setTitle("Game of Life");
        setSize(800, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        MyFrame frame = new MyFrame();
    }
}
