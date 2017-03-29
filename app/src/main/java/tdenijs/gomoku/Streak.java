package tdenijs.gomoku;

/**
 * Created by Tristan de Nijs on 3/29/17.
 */

public class Streak {
    private enum Type {
        HOR, VERT, DIAGDOWN, DIAGUP
    }

    private Point start;
    private Point end;
    private int length;
    private int player;
    private Type type;

    public Streak(Point start, Point end, int length, Type type, int player) {
        this.start = start;
        this.end = end;
        this.length = length;
        this.type = type;
        this.player = player;
    }
}
