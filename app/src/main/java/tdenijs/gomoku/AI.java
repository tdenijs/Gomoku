package tdenijs.gomoku;

import java.util.Random;

/**
 * Created by Tristan de Nijs on 3/30/17.
 * Default NAIVE AI that just randoms places on the board.
 */

public class AI {

    /**
     * Finds a place to play a piece.
     * @param board : The board that we need to play on.
     * @param size : The size of the board
     * @return : Returns a new Point with coordinates (x,y).
     */
    public Point playPiece(Board board, int size) {
        Random randomGenerator = new Random();
        int randomX;
        int randomY;
        boolean flag = false;
        int tries = 0;
        do {
            randomX = randomGenerator.nextInt(size);
            randomY = randomGenerator.nextInt(size);
            tries++;
            if(board.board[randomX][randomY] == 0)
                flag = true;
        }while(flag == false && tries < 50);

        if(tries == 50) {
            //We tried a ton and never found a valid piece. Just play in the first place we can play
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(board.board[i][j] == 0) {
                        return new Point(i,j);
                    }
                }
            }
        }

        return new Point(randomX, randomY);
    }
}
