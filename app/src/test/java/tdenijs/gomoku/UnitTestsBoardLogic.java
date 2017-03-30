package tdenijs.gomoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tristan de Nijs on 3/29/17.
 */


public class UnitTestsBoardLogic {

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
        assertTrue(board.checkWin(1,0,0));
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
    public void diagDownWin_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(1, 0, 3);
        board.playPiece(1, 1, 4);
        board.playPiece(1, 2, 5);
        board.playPiece(1, 3, 6);
        board.playPiece(1, 4, 7);
        assertTrue(board.checkWin(1,1,4));
    }

    @Test
    public void diagDownWinLow_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(1, 0, 0);
        board.playPiece(1, 1, 1);
        board.playPiece(1, 2, 2);
        board.playPiece(1, 3, 3);
        board.playPiece(1, 4, 4);
        assertTrue(board.checkWin(1,4,4));
    }

    @Test
    public void diagDownWinHigh_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(1, 0, 3);
        board.playPiece(1, 1, 4);
        board.playPiece(1, 2, 5);
        board.playPiece(1, 3, 6);
        board.playPiece(1, 4, 7);
        assertTrue(board.checkWin(1,4,7));
    }

    @Test
    public void diagDownWinInMiddle_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(1, 0, 3);
        board.playPiece(1, 1, 4);
        board.playPiece(1, 2, 5);
        board.playPiece(1, 3, 6);
        board.playPiece(1, 4, 7);
        assertTrue(board.checkWin(1,2,5));
    }

    @Test
    public void diagDownUp_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(1, 5, 5);
        board.playPiece(1, 6, 4);
        board.playPiece(1, 7, 3);
        board.playPiece(1, 8, 2);
        board.playPiece(1, 9, 1);
        assertTrue(board.checkWin(1,5,5));
    }

    @Test
    public void diagUpWinLow_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(1, 5, 5);
        board.playPiece(1, 6, 4);
        board.playPiece(1, 7, 3);
        board.playPiece(1, 8, 2);
        board.playPiece(1, 9, 1);
        assertTrue(board.checkWin(1,9,1));
    }


    @Test
    public void diagUpWinInMiddle_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(1, 5, 5);
        board.playPiece(1, 6, 4);
        board.playPiece(1, 7, 3);
        board.playPiece(1, 8, 2);
        board.playPiece(1, 9, 1);
        assertTrue(board.checkWin(1,7,3));
    }

    @Test
    public void horizontalWinInMiddleWithBlockage_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(2, 0, 0);
        board.playPiece(1, 1, 0);
        board.playPiece(1, 2, 0);
        board.playPiece(1, 3, 0);
        board.playPiece(1, 4, 0);
        board.playPiece(1, 5, 0);
        board.playPiece(2, 6, 0);
        assertTrue(!board.checkWin(1,2,0));
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
        assertTrue(!board.checkWin(1,0,2));
    }

    @Test
    public void diagDownWinInMiddleWithBlockage_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(2, 0, 3);
        board.playPiece(1, 1, 4);
        board.playPiece(1, 2, 5);
        board.playPiece(1, 3, 6);
        board.playPiece(1, 4, 7);
        board.playPiece(1, 5, 8);
        board.playPiece(2, 6, 9);
        assertTrue(!board.checkWin(1,2,5));
    }

    @Test
    public void diagUpWinInMiddleWithBlockage_isCorrect() throws Exception {
        Board board = new Board(10);
        board.playPiece(2, 3, 7);
        board.playPiece(1, 4, 6);
        board.playPiece(1, 5, 5);
        board.playPiece(1, 6, 4);
        board.playPiece(1, 7, 3);
        board.playPiece(1, 8, 2);
        board.playPiece(2, 9, 1);
        assertTrue(!board.checkWin(1,7,3));
    }


}
