package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener
{
    JFrame frame;
    JPanel board;
    JButton[][] cells;
    Boolean[][] state;
    void Initialize()
    {
        frame = new MyFrame();
        board = new Board();
        cells = new JButton[100][100];
        state = new Boolean[100][100];
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                cells[i][j] = new MyButton();
                state[i][j] = false;
                cells[i][j].addActionListener(this);
                board.add(cells[i][j]);
            }
        }
        frame.add(board);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                if (e.getSource() == cells[i][j])
                {
                    if (state[i][j])
                    {
                        cells[i][j].setBackground(java.awt.Color.gray);
                        state[i][j] = false;
                    } else
                    {
                        cells[i][j].setBackground(java.awt.Color.black);
                        state[i][j] = true;
                    }
                }
            }
        }
    }
}
