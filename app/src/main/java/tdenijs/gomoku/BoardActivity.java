package tdenijs.gomoku;

/**
 * Created by Tristan de Nijs on 3/31/17.
 */

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import java.util.Timer;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BoardActivity extends AppCompatActivity{

    private int size;
    private int players = 1;
    private int xPos, yPos;
    private Board board;
    private ImageView[][] boardView;
    private AI ai;
    private String player2Type;
    private int winsWhite = 0; //Amount of times white won
    private int winsBlack = 0; //Amount of times black won
    private int ties = 0; //Amount of times the game was a tie
    private Drawable[] drawing = new Drawable[3];
    private String typeString = "STANDARD";
    private GameType typeEnum = GameType.STANDARD;
    private Context context;
    private int currentPlayer = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Bundle extras = getIntent().getExtras();
        size = extras.getInt("size");
        typeString = extras.getString("gameType");
        if(typeString.equals("FREESTYLE")) {
            typeEnum = GameType.FREESTYLE;
        }

        player2Type = extras.getString("player2");

        if(player2Type.equals("AI")) {
            //We have a human vs AI game
        }else if(player2Type.equals("human")){
            //We have a 2 player game
        }
    }

    public void initialize() {
        ai = new AI();

        board = new Board(size, typeEnum);
        boardView = new ImageView[size][size];
        loadDrawings();
        drawBoard();

    }

    private void loadDrawings() {
        drawing[0] = ContextCompat.getDrawable(context, R.drawable.game_box2);
        drawing[1] = ContextCompat.getDrawable(context, R.drawable.whitepiece);
        drawing[0] = ContextCompat.getDrawable(context, R.drawable.blackpiece);
    }

    private void drawBoard() {
        DisplayMetrics dis = getResources().getDisplayMetrics();
        int screenWidth = dis.widthPixels - 100;
        int cellSize = Math.round(screenWidth / size);

        LinearLayout.LayoutParams singleCellDimensions = new LinearLayout.LayoutParams(cellSize, cellSize);
        LinearLayout.LayoutParams singleRowDimensions = new LinearLayout.LayoutParams(cellSize * size, cellSize);

        //Create cells
        for(int i = 0; i < size; i++) {
            LinearLayout row = new LinearLayout(context);
            for(int j = 0; j < size; j++) {
                boardView[i][j] = new ImageView(context);
                boardView[i][j].setBackgroundDrawable(drawing[0]);
                final int x = i;
                final int y = j;
                boardView[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        xPos = x;
                        yPos = y;
                        playPiece(x,y);
                    }
                });
            }
        }
    }


    private void playPiece(int x, int y) {
        boardView[xPos][yPos].setImageDrawable(drawing[currentPlayer]);
        board.playPiece(currentPlayer, xPos, yPos);
        if(board.checkWin(currentPlayer, xPos, yPos)) {
            //Player won the game!

        }

        if(board.gameDraw()) {
            //The game is a draw!
        }

    }

    private void AIplayPiece() {
        Point p = ai.playPiece(board, size);
    }

}
