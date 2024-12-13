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
    boolean[][] state;
    Timer main;
    int size;
    GameWorker gameWorker;

    void Initialize()
    {
        main = new Timer(1000, this);

        PlayButton obj = new PlayButton();
        size = new Board().getGridSize();
        frame = new MyFrame();
        board = new Board();
        cells = new JButton[size][size];
        state = new boolean[size][size];
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

    void reset() // Set all cells to Dead
    {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                state[i][j] = false;
                cells[i][j].setBackground(Colors.getDeadColor());
            }
        }
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

    public boolean[][] nextGen() // Moves 1 Generation Forward
    {
        boolean[][] stateCopy = new boolean[size][size];
        for (int i = 0; i < size; i++) { // Copying the state of the cells
            System.arraycopy(state[i], 0, stateCopy[i], 0, size);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stateCopy[i][j] = ChangeState(i, j);
            }
        }

        for (int i = 0; i < size; i++) // Repainting cells after moving 1 generation
        {
            for (int j = 0; j < size; j++)
            {
                if (stateCopy[i][j])
                    cells[i][j].setBackground(Colors.getAliveColor());
                else
                    cells[i][j].setBackground(Colors.getDeadColor());
            }
        }

        return stateCopy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == play) // Play Button is clicked
        {
            if (main.isRunning()) { // Game is running
                main.stop();
                if (gameWorker != null) {
                    gameWorker.cancel(true); // Stop the game
                }
            } else {
                main.start();
                gameWorker = new GameWorker(); // Start the game
                gameWorker.execute();
            }
        }
        else if (e.getSource() == next) // Next Button is clicked
        {
            state = nextGen(); // Move 1 generation forward
        }
        else if (e.getSource() == prev) // Prev Button is clicked
        {
            if (main.isRunning()) { // Game is running
                main.stop();
                if (gameWorker != null) {
                    gameWorker.cancel(true); // Stop the game
                }
            }
            reset();
        }
        else // Setting initial state of cells
        {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (e.getSource() == cells[i][j] && !main.isRunning()) {
                        if (state[i][j]) {
                            cells[i][j].setBackground(Colors.getDeadColor());
                            state[i][j] = false;
                        } else {
                            cells[i][j].setBackground(Colors.getAliveColor());
                            state[i][j] = true;
                        }
                    }
                }
            }
        }
    }

    private class GameWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            while (!isCancelled()) {
                state = nextGen();
                Thread.sleep(30);
            }
            return null;
        }
    }
}