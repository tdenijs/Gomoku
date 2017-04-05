package tdenijs.gomoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tristan de Nijs on 4/4/17.
 */

public class UnitTestsStreakLogic {

    @Test
    public void checkStreakCompare() throws Exception {
        Streak s1 = new Streak();
        Streak s2 = new Streak();
        s1.addPoint(new Point(0,0));
        s1.addPoint(new Point(1,0));
        s1.addPoint(new Point(2,0));
        s2.addPoint(new Point(0,0));
        s2.addPoint(new Point(1,0));
        s2.addPoint(new Point(2,0));
        assertEquals(s2.comparePoints(s1), 0);
    }


    @Test
    public void horizontalStreak_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[0][0] = 1;
        board.board[1][0] = 1;
        board.board[2][0] = 1;
        Streak s = new Streak();
        s.addPoint(new Point(0,0));
        s.addPoint(new Point(1,0));
        s.addPoint(new Point(2,0));
        s.type = StreakType.HOR;
        board.buildHoriStreak(0,0,1);
        assertEquals(board.streaks[1].get(0).comparePoints(s), 0);
    }

    @Test
    public void verticalStreak_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[0][0] = 1;
        board.board[0][1] = 1;
        board.board[0][2] = 1;
        Streak s = new Streak();
        s.addPoint(new Point(0,0));
        s.addPoint(new Point(0,1));
        s.addPoint(new Point(0,2));
        s.type = StreakType.VERT;
        board.buildVertStreak(0,0,1);
        assertEquals(board.streaks[1].get(0).comparePoints(s), 0);
    }


    @Test
    public void diagDownStreak_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[0][0] = 1;
        board.board[1][1] = 1;
        board.board[2][2] = 1;
        Streak s = new Streak();
        s.addPoint(new Point(0,0));
        s.addPoint(new Point(1,1));
        s.addPoint(new Point(2,2));
        s.type = StreakType.DIAGD;
        board.buildDiagDownStreak(0,0,1);
        assertEquals(board.streaks[1].get(0).comparePoints(s), 0);
    }


    @Test
    public void diagUpStreak_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[2][0] = 1;
        board.board[1][1] = 1;
        board.board[0][2] = 1;
        Streak s = new Streak();
        s.addPoint(new Point(2,0));
        s.addPoint(new Point(1,1));
        s.addPoint(new Point(0,2));
        s.type = StreakType.DIAGU;
        board.buildDiagUpStreak(2,0,1);
        assertEquals(board.streaks[1].get(0).comparePoints(s), 0);
    }

    @Test
    public void getAdjacentHorizontal_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[0][0] = 1;
        board.board[1][0] = 1;
        board.board[2][0] = 1;
        board.buildHoriStreak(0,0,1);
        Point[] adjs = new Point[2];
        adjs[0] = null;
        adjs[1] = new Point(3,0);
        Point[] adjs2 = board.streaks[1].get(0).getAdjacent(5);
        assertEquals(adjs[1].compareTo((adjs2[1])), 0);
    }

    @Test
    public void getAdjacentVertical_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[0][0] = 1;
        board.board[0][1] = 1;
        board.board[0][2] = 1;
        board.buildVertStreak(0,0,1);
        Point[] adjs = new Point[2];
        adjs[0] = null;
        adjs[1] = new Point(0,3);
        Point[] adjs2 = board.streaks[1].get(0).getAdjacent(5);
        assertEquals(adjs[1].compareTo((adjs2[1])), 0);
    }

    @Test
    public void getAdjacentDiagDown_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[0][0] = 1;
        board.board[1][1] = 1;
        board.board[2][2] = 1;
        board.buildDiagDownStreak(0,0,1);
        Point[] adjs = new Point[2];
        adjs[0] = null;
        adjs[1] = new Point(3,3);
        Point[] adjs2 = board.streaks[1].get(0).getAdjacent(5);
        assertEquals(adjs[1].compareTo((adjs2[1])), 0);
    }

    @Test
    public void getAdjacentDiagUp_isCorrect() throws Exception {
        AIBoard board = new AIBoard(5);
        board.board[2][1] = 1;
        board.board[1][2] = 1;
        board.board[0][3] = 1;
        board.buildDiagUpStreak(2,1,1);
        Point[] adjs = new Point[2];
        adjs[0] = new Point(3,0);
        adjs[1] = null;
        Point[] adjs2 = board.streaks[1].get(0).getAdjacent(5);
        System.out.println(adjs[0].toString());
        System.out.println(board.streaks[1].get(0).type);
        System.out.println(adjs2[0]);
        assertEquals(adjs[0].compareTo((adjs2[0])), 0);
    }
}
