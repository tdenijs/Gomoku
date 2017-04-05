package tdenijs.gomoku;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tristan de Nijs on 4/3/17.
 */

public class SmartAI extends AI {


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

        //There is a 5/100 or 5% chance that the AI is stupid and does a random move
        //Think of this as it rolled a critical fail (Natural 1) and messed up horribly
        //Possible addition for easier AI
        //if(criticalFail() == true) {
        //  super.playPiece(board,size);
        //}

        AIBoard aiboard = new AIBoard(board);
        aiboard.buildStreaks();
        return aiboard.findPlay(playerNumber);
    }

    /**
     * Chance at critical fail and it does a completely RANDOM move from the super class
     * @return true if critical fail, false if not critical fail.
     */
    public boolean criticalFail() {
        Random randomGenerator = new Random();
        int randomChance;
        randomChance = randomGenerator.nextInt(100);

        if(randomChance < 5) {
            return true;
        }else
            return false;
    }
}
