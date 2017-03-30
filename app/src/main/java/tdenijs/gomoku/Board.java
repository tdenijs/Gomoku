package tdenijs.gomoku;

import java.util.ArrayList;

/**
 * Created by Tristan de Nijs on 3/29/17.
 */

public class Board {
    protected int[][] board; //Grid
    private int size; //The size of the board
    private int maxMoves;
    private int movesMade; //How many moves are made


    public Board(int size) {
        this.movesMade = 0;
        this.size = size;
        this.maxMoves = size * size;
        this.board = new int[size][size];


        newGame();
    }

    public void newGame() {
        printBoard();
    }

    /**
     * Makes a turn for one of the players
     * @param player : White or Black. 1 if white, 2 if black.
     */
    public void playPiece(int player, int x, int y) {
        if(player < 1 || player > 2) {
            //Invalid input for player color.
            return;
        }
        if(movesMade >= maxMoves) {
            //The whole board is filled. It's a draw!
            gameDraw();
            return;
        }

        if(board[x][y] != 0) {
            //That spot is already in use by another piece!
            return;
        }

        board[x][y] = player;

        //checkWin(player, x, y); //Test if that move is a winning move
    }

    /**
     * Checks if the player's played piece at position (x,y) is a winning move
     * @param player
     * @param x
     * @param y
     */
    public boolean checkWin(int player, int x, int y) {
        if(checkWinHorizontal(player, x, y)
                || checkWinVertical(player, x, y )
                || checkWinDiagonalDown(player, x, y)){
            return true;
        }else
            return false;
    }

    private boolean checkWinHorizontal(int player, int x, int y) {
        int numberOfBlocks = 0;
        int streak = 1;
        for(int i = x+1; i < size && streak < 6; i++) {
            //Check right of current coordinate
            if(board[i][y] == player)
                streak++;
            else if(board[i][y] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        for(int i = x-1; i >= 0 && streak < 6; i--) {
            //Check left of the current coordinate
            if (board[i][y] == player)
                streak++;
            else if (board[i][y] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            } else
                break;
        }
        if(streak > 4 && numberOfBlocks < 2)
            return true;
        else
            return false;
    }

    private boolean checkWinVertical(int player, int x, int y) {
        int numberOfBlocks = 0;
        int streak = 1;
        for(int i = y+1; i < size && streak < 6; i++) {
            //Check down from current coordinate
            if(board[x][i] == player)
                streak++;
            else if(board[x][i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        for(int i = y-1; i >= 0 && streak < 6; i--) {
            //Check up from the current coordinate
            if(board[x][i] == player)
                streak++;
            else if(board[x][i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        if(streak > 4 && numberOfBlocks < 2)
            return true;
        else
            return false;
    }

    //Checks diagonal DOWN as in: Backslash \
    private boolean checkWinDiagonalDown(int player, int x, int y) {
        int numberOfBlocks = 0;
        int streak = 1;
        for(int i = 1; x+i < size && y+i < size && streak < 6; i++) {
            //Check down and right from current coordinate
            if(board[x+i][y+i] == player)
                streak++;
            else if(board[x+i][y+i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        for(int i = 1; x-i >= 0 && y-i >= 0 && streak < 6; i--) {
            //Check up and left from the current coordinate
            if(board[x-i][y-i] == player)
                streak++;
            else if(board[x-i][y-i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        System.out.println("Streak: " + streak);
        if(streak > 4 && numberOfBlocks < 2)
            return true;
        else
            return false;
    }

    //Checks diagonal UP as in: Forward Slash /
    private boolean checkWinDiagonalUp(int player, int x, int y) {
        return false;
    }

    private void gameDraw() {
        System.out.println("Game is a draw!");
    }

    public void printBoard() {
        for(int i = 0; i < size; i++) {
            System.out.print("--");
        }
        System.out.print("\n");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        for(int i = 0; i < size; i++) {
            System.out.print("--");
        }
    }

    private int oppositePlayer(int player) {
        if(player == 1)
            return 2;
        else if(player == 2)
            return 1;
        else {
            System.exit(0);
            return 0;
        }
    }

}
