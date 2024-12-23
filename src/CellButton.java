package src;

import javax.swing.*;

public class CellButton extends JButton
{
    CellButton()
    {
        super();
        this.setBackground(Colors.getBoardColor());
        this.setBorder(BorderFactory.createLineBorder(Colors.getBorderColor(), 1));
        this.setOpaque(true);
        this.setVisible(true);
    }
}
