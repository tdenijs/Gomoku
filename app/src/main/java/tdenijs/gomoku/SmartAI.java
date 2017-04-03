package tdenijs.gomoku;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tristan de Nijs on 4/3/17.
 */

public class SmartAI extends AI {

    private int playerNumber;

    public SmartAI(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * TO DO: Actually implement this
     * @param board : The board that we need to play on.
     * @param size : The size of the board
     * @return
     */
    public Point playPiece(Board board, int size) {
        Random randomGenerator = new Random();
        int randomChance;
        randomChance = randomGenerator.nextInt(100);

        //There is a 5/100 or 5% chance that the AI is stupid and does a random move
        //Think of this as it rolled a critical fail (Natural 1) and messed up horribly
        if(randomChance < 5) {
            return super.playPiece(board, size);
        }
        //Object [] move = minimax.mmab(board, 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        //return (Point) move[1];
        return super.playPiece(board,size);
    }
}
