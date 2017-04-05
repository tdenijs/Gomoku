package tdenijs.gomoku;

/**
 * Created by Tristan de Nijs on 3/29/17.
 */

public class Point implements Comparable<Point> {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return ("X: " + x + " Y: " + y);
    }

    @Override
    public int compareTo(Point p) {
        if(this.x == p.x && this.y == p.y) {
            return 0; //Same
        }
        return 1; //Not same
    }

}
