package tdenijs.gomoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tristan de Nijs on 3/29/17.
 */


public class UnitTests {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void playPiece_works() throws Exception {
        int testArray[][] = new int[5][1];
        for(int i = 0; i < 5; ++i) {
            testArray[i][0] = 0;
        }
        Board board = new Board(5);
        board.playPiece(1, 0, 0);
        assertEquals(board.board[1][0], testArray[1][0]);
    }

    @Test
    public void horizontalWin_isCorrect() throws Exception {
        Board board = new Board(5);
        board.playPiece(1, 0, 0);
        board.playPiece(1, 1, 0);
        board.playPiece(1, 2, 0);
        board.playPiece(1, 3, 0);
        board.playPiece(1, 4, 0);
        assertTrue(board.checkWin(1,4,0));
    }

    @Test
    public void horizontalWinInMiddle_isCorrect() throws Exception {
        Board board = new Board(5);
        board.playPiece(1, 0, 0);
        board.playPiece(1, 1, 0);
        board.playPiece(1, 2, 0);
        board.playPiece(1, 3, 0);
        board.playPiece(1, 4, 0);
        assertTrue(board.checkWin(1,2,0));
    }

    @Test
    public void verticalWin_isCorrect() throws Exception {
        Board board = new Board(5);
        board.playPiece(1, 0, 0);
        board.playPiece(1, 0, 1);
        board.playPiece(1, 0, 2);
        board.playPiece(1, 0, 3);
        board.playPiece(1, 0, 4);
        assertTrue(board.checkWin(1,0,4));
    }

    @Test
    public void verticalWinInMiddle_isCorrect() throws Exception {
        Board board = new Board(5);
        board.playPiece(1, 0, 0);
        board.playPiece(1, 0, 1);
        board.playPiece(1, 0, 2);
        board.playPiece(1, 0, 3);
        board.playPiece(1, 0, 4);
        assertTrue(board.checkWin(1,0,1));
    }

    @Test
    public void verticalWinInMiddleWithBlockage_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(2, 0, 0);
        board.playPiece(1, 0, 1);
        board.playPiece(1, 0, 2);
        board.playPiece(1, 0, 3);
        board.playPiece(1, 0, 4);
        board.playPiece(1, 0, 5);
        board.playPiece(2, 0, 6);
        assertTrue(!board.checkWin(1,0,3));
    }
}
