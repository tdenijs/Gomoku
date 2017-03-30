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
    private GameType gType;


    /**
     * Constructor
     * @param size : The width/length of the board. Board is always a square.
     */
    public Board(int size) {
        if(size < 0)
            size = 10;
        this.movesMade = 0;
        this.size = size;
        this.maxMoves = size * size;
        this.board = new int[size][size];
        this.gType = GameType.STANDARD; //Default game type
    }

    /**
     * Constructor
     * @param size : The width/length of the board. Board is always a square.
     * @param gType : The game type chosen.
     */
    public Board(int size, GameType gType) {
        if(size < 0)
            size = 10;
        this.movesMade = 0;
        this.size = size;
        this.maxMoves = size * size;
        this.board = new int[size][size];
        this.gType = gType;
    }


    /**
     * Makes a turn for one of the players
     * @param player : The player number. White or Black. 1 if white, 2 if black.
     * @param x : The x coordinate they are playing at.
     * @param y : The y coordinate they are playing at.
     */
    public void playPiece(int player, int x, int y) {
        if(player < 1 || player > 2) {
            //Invalid input for player color.
            return;
        }

        if(board[x][y] != 0) {
            //That spot is already in use by another piece!
            return;
        }

        board[x][y] = player;
        movesMade++;
    }

    /**
     * Checks if the location (x,y) is a streak that is a winning streak
     * @param player : The player number. White or Black. 1 if white, 2 if black.
     * @param x : The x coordinate they are playing at.
     * @param y : The y coordinate they are playing at.
     * @return : Returns the value of the check.
     */
    public boolean checkWin(int player, int x, int y) {
        if(checkWinHorizontal(player, x, y)
                || checkWinVertical(player, x, y )
                || checkWinDiagonalDown(player, x, y)
                || checkWinDiagonalUp(player, x, y)){
            return true;
        }else
            return false;
    }

    /**
     * Checks if the location (x,y) is a streak that is a winning streak horizontally
     * @param player : The player number. White or Black. 1 if white, 2 if black.
     * @param x : The x coordinate they are playing at.
     * @param y : The y coordinate they are playing at.
     * @return : Returns the value of the check.
     */
    private boolean checkWinHorizontal(int player, int x, int y) {
        int numberOfBlocks = 0;
        int streak = 1;
        for(int i = x+1; i < size && streak < 7; i++) {
            //Check right of current coordinate
            if(board[i][y] == player)
                streak++;
            else if(board[i][y] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        for(int i = x-1; i >= 0 && streak < 7; i--) {
            //Check left of the current coordinate
            if (board[i][y] == player)
                streak++;
            else if (board[i][y] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            } else
                break;
        }
        return checkStreak(streak, numberOfBlocks);
    }

    /**
     * Checks if the location (x,y) is a streak that is a winning streak vertically
     * @param player : The player number. White or Black. 1 if white, 2 if black.
     * @param x : The x coordinate they are playing at.
     * @param y : The y coordinate they are playing at.
     * @return : Returns the value of the check.
     */
    private boolean checkWinVertical(int player, int x, int y) {
        int numberOfBlocks = 0;
        int streak = 1;
        for(int i = y+1; i < size && streak < 7; i++) {
            //Check down from current coordinate
            if(board[x][i] == player)
                streak++;
            else if(board[x][i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        for(int i = y-1; i >= 0 && streak < 7; i--) {
            //Check up from the current coordinate
            if(board[x][i] == player)
                streak++;
            else if(board[x][i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        return checkStreak(streak, numberOfBlocks);
    }

    /**
     * Checks if the location (x,y) is a streak that is a winning streak diagonal DOWN as in: Backslash \
     * @param player : The player number. White or Black. 1 if white, 2 if black.
     * @param x : The x coordinate they are playing at.
     * @param y : The y coordinate they are playing at.
     * @return : Returns the value of the check.
     * */
    private boolean checkWinDiagonalDown(int player, int x, int y) {
        int numberOfBlocks = 0;
        int streak = 1;
        for(int i = 1; x+i < size && y+i < size && streak < 7; i++) {
            //Check down and right from current coordinate
            if(board[x+i][y+i] == player)
                streak++;
            else if(board[x+i][y+i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        for(int i = 1; x-i >= 0 && y-i >= 0 && streak < 7; i++) {
            //Check up and left from the current coordinate
            if(board[x-i][y-i] == player)
                streak++;
            else if(board[x-i][y-i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }

        return checkStreak(streak, numberOfBlocks);
    }

    /**
     * Checks if the location (x,y) is a streak that is a winning streak diagonal UP as in: Forwardslash /
     * @param player : The player number. White or Black. 1 if white, 2 if black.
     * @param x : The x coordinate they are playing at.
     * @param y : The y coordinate they are playing at.
     * @return : Returns the value of the check.
     */
    private boolean checkWinDiagonalUp(int player, int x, int y) {
        int numberOfBlocks = 0;
        int streak = 1;
        for(int i = 1; x+i < size && y-i >= 0 && streak < 7; i++) {
            //Check down and left from current coordinate
            if(board[x+i][y-i] == player)
                streak++;
            else if(board[x+i][y-i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }
        for(int i = 1; x-i >= 0 && y+i < size && streak < 7; i++) {
            //Check up and right from the current coordinate
            if(board[x-i][y+i] == player)
                streak++;
            else if(board[x-i][y+i] == oppositePlayer(player)) {
                numberOfBlocks++;
                break;
            }else
                break;
        }

        return checkStreak(streak, numberOfBlocks);
    }

    /**
     * Checks if the streak amount is enough
     * @param streak : The number of pieces in the streak
     * @param numberOfBlocks : How many sides of the streak is being blocked by the other player
     * @return : Returns the value of the check.
     */
    private boolean checkStreak(int streak, int numberOfBlocks) {
        switch(gType) {
            case STANDARD:
                if (streak == 5 && numberOfBlocks < 2)
                    return true;
                break;
            case FREESTYLE:
                if (streak > 4 && numberOfBlocks < 2)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }

    /**
     * Returns the opposite player of the current player
     * @param player : The current player number
     * @return : The opposite player number
     */
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

    /**
     * Checks if the game is a draw by having all pieces be played.
     * @return : Returns the value of the check.
     */
    private boolean gameDraw() {
        if(movesMade >= maxMoves) {
            System.out.println("Game is a draw!");
            return true;
        }else
            return false;
    }

    /**
     * Prints the board to System out.
     */
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


}
