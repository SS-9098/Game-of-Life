package src;

public class Main
{

    int Play_Speed = 30; // Interval between each generation(in Milliseconds)

    public static void main(String[] args) {
        Game obj = new Game();
        obj.Initialize();
    }

    public int getPlay_Speed() {
        return Play_Speed;
    }

}
