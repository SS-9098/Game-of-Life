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
    int size;

    void Initialize()
    {
        main = new Timer(100, this);

        PlayButton obj = new PlayButton();
        size = new Board().getGridSize();
        frame = new MyFrame();
        board = new Board();
        cells = new JButton[size][size];
        state = new Boolean[size][size];
        media = obj.play();
        prev = obj.prev();
        play = obj.playButton();
        next = obj.next();

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
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

    public int[][] getNeighbours(int i, int j)
    {
        int[][] neighbours = new int[8][2];
        int k = 0;

        for (int x = i - 1; x <= i + 1; x++)
        {
            for (int y = j - 1; y <= j + 1; y++)
            {
                if (!(x == i && y == j))
                {
                    neighbours[k][0] = x;
                    neighbours[k][1] = y;
                    k++;
                }
            }
        }
        return neighbours;
    }

    public boolean ChangeState(int i, int j) // Cell should be Alive/Dead
    {
        int[][] neighbours = getNeighbours(i, j);
        int LiveCount = 0; // Number of live neighbours
        for(int k = 0; k < 8; k++) // Counting the number of live neighbours
        {
            try {
                if (state[neighbours[k][0]][neighbours[k][1]]) {
                    LiveCount++;
                }
            } catch (ArrayIndexOutOfBoundsException _) {
            }
        }
        if (state[i][j]) { // Cell is alive
            return LiveCount == 2 || LiveCount == 3;
        } else { // Cell is dead
            return LiveCount == 3;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < size; i++) // Setting the state of the cells
        {
            for (int j = 0; j < size; j++)
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

                if (main.isRunning()) // Game is running
                {
                    if (ChangeState(i, j)) {
                        cells[i][j].setBackground(Colors.getAliveColor());
                        state[i][j] = true;
                    } else {
                        cells[i][j].setBackground(Colors.getDeadColor());
                        state[i][j] = false;
                    }
                }
            }
        }

        if (e.getSource() == next) // Move 1 generation forward
        {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (ChangeState(i, j)) {
                        cells[i][j].setBackground(Colors.getAliveColor());
                        state[i][j] = true;
                    } else {
                        cells[i][j].setBackground(Colors.getDeadColor());
                        state[i][j] = false;
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
