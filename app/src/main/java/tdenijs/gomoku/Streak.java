package tdenijs.gomoku;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Tristan de Nijs on 4/4/17.
 */

public class Streak implements Comparable<Streak>{

    private ArrayList<Point> points;
    protected StreakType type;

    public Streak() {
        points = new ArrayList<>();
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public int size() {
        return points.size();
    }

    public boolean containsPoint(Point p) {
        return points.contains(p);
    }

    public Point checkConnecting(Point p) {
        return new Point(0,0);
    }

    public String toString() {
        String s = "";
        for(Point p : points) {
            s += ("Point in streak: " + p.toString() + "\n");
        }
        return s;
    }

    /**
     * Returns one adjacent Point to the streak
     * Tries to return a valid Point from the front and back of the points list
     * @param boardSize : The size of the board, to make sure we don't return an invalid point.
     * @return Point : returns a Point object that is adjacent to the streak
     */
    public Point[] getAdjacent(int boardSize) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        int size = points.size();
        Point[] ret = new Point[2];
        ret[0] = null;
        ret[1] = null;
        switch(type) {
            case HOR:
                p1.x = (points.get(0).x) - 1;
                p1.y = (points.get(0).y);
                p2.x = (points.get(size - 1).x) + 1;
                p2.y = (points.get(size - 1).y);
                if (p1.x >= 0)
                    ret[0] = p1;
                if (p2.x < boardSize)
                    ret[1] = p2;
                break;
            case VERT:
                p1.x = (points.get(0).x);
                p1.y = (points.get(0).y) - 1;
                p2.x = (points.get(size - 1).x);
                p2.y = (points.get(size - 1).y) + 1;
                if (p1.y >= 0)
                    ret[0] = p1;
                if (p2.y < boardSize)
                    ret[1] = p2;
                break;
            case DIAGD:
                p1.x = (points.get(0).x) - 1;
                p1.y = (points.get(0).y) - 1;
                p2.x = (points.get(size - 1).x) + 1;
                p2.y = (points.get(size - 1).y) + 1;
                if (p1.x >= 0 && p1.y >= 0)
                    ret[0] = p1;
                if (p2.x < boardSize && p2.y < boardSize)
                    ret[1] = p2;
                break;
            case DIAGU:
                p1.x = (points.get(0).x) + 1;
                p1.y = (points.get(0).y) - 1;
                p2.x = (points.get(size - 1).x) - 1;
                p2.y = (points.get(size - 1).y) + 1;
                if (p1.x < boardSize && p1.y >= 0)
                    ret[0] = p1;
                if (p2.x >= 0 && p2.y < boardSize)
                    ret[1] = p2;
                break;
        }
        return ret;
    }

    public int comparePoints(Streak s) {

        if((s.points.size() != this.points.size() || s.type != this.type))
            return 1; //Not the same
        for(int i = 0; i < s.points.size(); i++) {
            if((s.points.get(i).x != this.points.get(i).x)
                    || (s.points.get(i).y != this.points.get(i).y)) {
                    return 1; //Not the same
            }
        }
        return 0; //same
    }

    @Override
    public int compareTo(Streak s) {
        if(s.size() < this.size())
            return 1; //this one is larger
        else if(s.size() > this.size())
            return -1; //this one is smaller
        else
            return 0; //The two are the same length
    }
}
