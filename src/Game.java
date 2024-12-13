package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener
{
    JFrame frame;
    JPanel board;
    JPanel media;
    JButton play, next, prev;
    JButton[][] cells;
    Boolean[][] state;
    Timer main;

    void Initialize()
    {
        main = new Timer(10, this);

        PlayButton obj = new PlayButton();
        frame = new MyFrame();
        board = new Board();
        cells = new JButton[100][100];
        state = new Boolean[100][100];
        media = obj.play();
        prev = obj.prev();
        play = obj.playButton();
        next = obj.next();

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

        prev.addActionListener(this);
        play.addActionListener(this);
        next.addActionListener(this);

        media.add(play, "Center");
        media.add(next, "East");
        media.add(prev, "West");
        frame.add(media, "South");
        frame.add(board, "Center");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < 100; i++) // Setting the state of the cells
        {
            for (int j = 0; j < 100; j++)
            {
                if (e.getSource() == cells[i][j] && !main.isRunning()) // Cell is clicked while the game is paused
                {
                    if (state[i][j])
                    {
                        cells[i][j].setBackground(Colors.getDeadColor());
                        state[i][j] = false;
                    } else
                    {
                        cells[i][j].setBackground(Colors.getAliveColor());
                        state[i][j] = true;
                    }
                }
            }
        }

        if (e.getSource() == play) // Play button is clicked
        {
            if (main.isRunning()) {
                main.stop();
            } else {
                main.start();
            }
        }

    }
}
