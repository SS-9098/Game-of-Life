package src;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
{

    int GridSize = 100;

    Board()
    {
        //this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(new GridLayout(GridSize,GridSize));
        //this.setBorder(BorderFactory.createLineBorder(Color.white, 20));
        this.setBackground(Colors.getBoardColor());
        this.setOpaque(true);
        this.setVisible(true);
    }

    public int getGridSize() {
        return GridSize;
    }
}
