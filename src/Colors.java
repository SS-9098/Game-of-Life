package src;

import java.awt.*;

public class Colors
{
    static Color board = Color.gray;
    static Color border = Color.darkGray;
    static Color dead = Color.gray;
    static Color alive = Color.black;
    static Color button = Color.black;

    public static Color getBoardColor() {
        return board;
    }

    public static Color getBorderColor() {
        return border;
    }

    public static Color getDeadColor() {
        return dead;
    }

    public static Color getAliveColor() {
        return alive;
    }

    public static Color getButtonColor() {
        return button;
    }
}
