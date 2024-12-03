package src;

import javax.swing.*;

public class Game
{
    JFrame frame;
    JPanel board;
    JButton[] cells;
    Game()
    {
        frame = new MyFrame();
        board = new Board();
        cells = new JButton[10000];
        for (int i = 0; i < 10000; i++)
        {
            cells[i] = new Button();
            board.add(cells[i]);
        }
        frame.add(board);
    }
}
