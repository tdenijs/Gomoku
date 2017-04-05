package tdenijs.gomoku;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tristan de Nijs on 4/4/17.
 */

public class AIBoard {
    private int size;
    public int[][] board;
    public ArrayList<Streak>[] streaks;
    private boolean[][][] boardType; //Holds on to whether a point is already added to that type of streak
                                     //In other words, if boardType[0][0][0] is true, it has already been added to a HORIZONTAL streak.
                                     //0 is Horizontal. 1 is Vertical. 2 is DiagDown, 3 is DiagUp

    public AIBoard(int size) {
        this.size = size;
        board = new int[size][size];
        boardType = new boolean[4][size][size];
        streaks = new ArrayList[3];
        streaks[0] = new ArrayList<>(); //0 is empty, 1 is white, 2 is black
        streaks[1] = new ArrayList<>();
        streaks[2] = new ArrayList<>();
    }

    public AIBoard(Board b) {
        this.size = b.size;
        this.board = new int[size][size];
        this.board = b.board;
        boardType = new boolean[4][size][size];
        streaks = new ArrayList[3];
        streaks[0] = new ArrayList<>(); //0 is empty, 1 is white, 2 is black
        streaks[1] = new ArrayList<>();
        streaks[2] = new ArrayList<>();
    }

    public void buildStreaks(){
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[i][j] == 0) {
                    //Do nothing
                }else {
                    buildStreak(i,j,board[i][j]);
                }
            }
        }
    }

    public void buildStreak(int x, int y, int player) {
        //Build a horizontal streak
        if(boardType[0][x][y] == false)
            buildHoriStreak(x, y, player);
        //Build a vertical streak
        if(boardType[1][x][y] == false)
            buildVertStreak(x, y, player);
        //Build a diagonal up streak
        if(boardType[2][x][y] == false)
            buildDiagDownStreak(x, y, player);
        //Build a diagonal down streak
        if(boardType[3][x][y] == false)
            buildDiagUpStreak(x, y, player);
    }

    protected void buildHoriStreak(int x, int y, int player) {
        Streak s = new Streak();

        //For each point in the horizontal line starting at (x,y)
        //Check if it's the same player's piece, if so, add it to the
        //Streak, and then set the boardType[0] (horizontal) of that point to TRUE,
        //Since that piece now belongs to a horizontal streak, so we never have to check
        //it's horizontal streak-ness ever again.
        for(int i = x; i < size; i++) {
            if(board[i][y] == player) {
                s.addPoint(new Point(i, y));
                boardType[0][i][y] = true;
            }else
                break;
        }

        s.type = StreakType.HOR;
        streaks[player].add(s);
    }

    protected void buildVertStreak(int x, int y, int player) {
        Streak s = new Streak();

        //For each point in the vertical line starting at (x,y)
        //Check if it's the same player's piece, if so, add it to the
        //Streak, and then set the boardType[1] (vertical) of that point to TRUE,
        //Since that piece now belongs to a vertical streak, so we never have to check
        //it's vertical streak-ness ever again.
        for(int i = y; i < size; i++) {
            if(board[x][i] == player) {
                s.addPoint(new Point(x, i));
                boardType[1][x][i] = true;
            }else
                break;
        }
        s.type = StreakType.VERT;
        streaks[player].add(s);
    }

    protected void buildDiagDownStreak(int x, int y, int player) {
        Streak s = new Streak();

        //For each point in the diagonal down line starting at (x,y)
        //Check if it's the same player's piece, if so, add it to the
        //Streak, and then set the boardType[2] (diag down) of that point to TRUE,
        //Since that piece now belongs to a diag down streak, so we never have to check
        //it's diag down streak-ness ever again.
        for(int i = 0; x+i < size && y+i < size; i++) {
            if(board[x+i][y+i] == player) {
                s.addPoint(new Point(x+i, y+i));
                boardType[2][x+i][y+i] = true;
            }else
                break;
        }
        s.type = StreakType.DIAGD;
        streaks[player].add(s);
    }

    protected void buildDiagUpStreak(int x, int y, int player) {
        Streak s = new Streak();

        //For each point in the diagonal up line starting at (x,y)
        //Check if it's the same player's piece, if so, add it to the
        //Streak, and then set the boardType[3] (diag up) of that point to TRUE,
        //Since that piece now belongs to a diag up streak, so we never have to check
        //it's diag up streak-ness ever again.

        //NOTE: This function does not actually check "Up". It actually only checks down and left.
        //This is because the piece at (x,y) is actually the last piece of the chain, but we can
        //add it first!
        for(int i = 0; x-i >= 0 && y+i < size; i++) {
            if(board[x-i][y+i] == player) {
                s.addPoint(new Point(x-i, y+i));
                boardType[2][x-i][y+i] = true;
            }else
                break;
        }
        s.type = StreakType.DIAGU;
        streaks[player].add(s);
    }

    /**
     * Finds a play to make based on the current streaks
     * Attacks first, then tries to defend
     * @param player : The player trying to find a play. White = 1, Black = 2.
     * @return
     */
    public Point findPlay(int player) {
        ArrayList<Streak>[] allStreaks = new ArrayList[4];
        allStreaks[0] = new ArrayList<>();
        allStreaks[1] = new ArrayList<>();
        allStreaks[2] = new ArrayList<>();
        allStreaks[3] = new ArrayList<>();
        Point bestMove = new Point(0,0);
        int valueBest = Integer.MIN_VALUE;
        int oppositePlayer;
        if(player == 1)
            oppositePlayer = 2;
        else
            oppositePlayer = 1;

        //Place all of YOUR streaks into the lists
        for(Streak s : streaks[player]) {
            switch(s.size()) {
                case 1:
                    allStreaks[0].add(s);
                    break;
                case 2:
                    allStreaks[1].add(s);
                    break;
                case 3:
                    allStreaks[2].add(s);
                    break;
                case 4:
                    allStreaks[3].add(s);
                    break;
            }
        }
        //Place all of ENEMY'S streaks into the lists
        for(Streak s : streaks[oppositePlayer]) {
            System.out.print("Adding streak: " + s.toString() + "\n");
            switch(s.size()) {
                case 1:
                    allStreaks[0].add(s);
                    break;
                case 2:
                    allStreaks[1].add(s);
                    break;
                case 3:
                    allStreaks[2].add(s);
                    break;
                case 4:
                    allStreaks[3].add(s);
                    break;
            }
        }

        //Go through each list and find a place to put a piece
        for(int i = 3; i >= 0; i--) {
            for(Streak s : allStreaks[i]) {
                Point[] possibles = s.getAdjacent(this.size);
                if(possibles[0] != null) {
                    if(board[possibles[0].x][possibles[0].y] == 0)
                        return possibles[0];
                }
                if(possibles[1] != null) {
                    if(board[possibles[1].x][possibles[1].y] == 0)
                        return possibles[1];
                }
            }
        }


        return new Point(0,0);
    }



}
