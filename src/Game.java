package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener
{
    JFrame frame;
    JPanel board;
    JPanel media;
    JButton play, next, prev;
    JLabel Generation_count;
    JButton[][] cells;
    boolean[][] state;
    Timer main;
    int size, generation;
    GameWorker gameWorker;

    void Initialize()
    {
        main = new Timer(1000, this);

        PlayButton obj = new PlayButton();
        Generation_count = new JLabel("Generation: 0");
        size = new Board().getGridSize();
        frame = new MyFrame();
        board = new Board();
        cells = new JButton[size][size];
        state = new boolean[size][size];
        media = obj.play();
        prev = obj.prev();
        play = obj.playButton();
        next = obj.next();

        Generation_count.setPreferredSize(new Dimension(100, 50));
        Generation_count.setFont(new Font("Arial", Font.BOLD, 20));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new MyButton();
                cells[i][j].addActionListener(this);
                board.add(cells[i][j]);
            }
        }

        reset();

        prev.addActionListener(this);
        play.addActionListener(this);
        next.addActionListener(this);

        media.add(play, "Center");
        media.add(next, "East");
        media.add(prev, "West");
        frame.add(Generation_count, "North");
        frame.add(media, "South");
        frame.add(board, "Center");
        frame.setVisible(true);
    }

    void reset() // Set all cells to Dead
    {
        generation = 0;
        Generation_count.setText("Generation: 0");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                state[i][j] = false;
                cells[i][j].setBackground(Colors.getDeadColor());
            }
        }
    }

    public void BorderIsVisible(boolean b) // Set Border of cells to Visible/Invisible
    {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(b)
                    cells[i][j].setBorder(BorderFactory.createLineBorder(Colors.getBorderColor(), 1));
                else
                    cells[i][j].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    public int[][] getNeighbours(int i, int j) // Returns 8 cells surrounding the cell at (i,j)
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
            } catch (ArrayIndexOutOfBoundsException _) {}
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

        generation++;
        Generation_count.setText("Generation: " + generation);

        return stateCopy;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == play) // Play Button is clicked
        {
            if (main.isRunning()) { // Game is running
                main.stop();
                if (gameWorker != null) {
                    gameWorker.cancel(true); // Stop the game
                }
                BorderIsVisible(true);
            } else {
                main.start();
                gameWorker = new GameWorker(); // Start the game
                gameWorker.execute();
                BorderIsVisible(false);
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
                BorderIsVisible(true);
            }
            reset();
        }
        else // Changing state of cells through user input
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

    private class GameWorker extends SwingWorker<Void, Void> // Thread to move generations
    {
        @Override
        protected Void doInBackground() throws Exception {
            while (!isCancelled()) {
                state = nextGen();
                Thread.sleep(new Main().getPlay_Speed()); // Delay between each generation
            }
            return null;
        }
    }
}