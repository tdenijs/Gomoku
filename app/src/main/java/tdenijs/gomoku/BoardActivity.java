package tdenijs.gomoku;

/**
 * Created by Tristan de Nijs on 3/31/17.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
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
        ai = new AI();
        board = new Board(size, typeEnum);
        boardView = new ImageView[size][size];
        context = this;
        drawing[0] = ResourcesCompat.getDrawable(getResources(), R.drawable.game_box2, null);
        drawing[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.whitepiece, null);
        drawing[2] = ResourcesCompat.getDrawable(getResources(), R.drawable.blackpiece, null);

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

        LinearLayout boardL = (LinearLayout) findViewById(R.id.Board);
        boardL.setBackgroundColor(Color.BLACK);
        boardL.setVisibility(View.VISIBLE);
        //boardL.setBackgroundDrawable(drawing[0]);
        drawBoard();
    }


    private void drawBoard() {
        DisplayMetrics dis = getResources().getDisplayMetrics();
        int screenWidth = dis.widthPixels - 100;
        int cellWidth = Math.round(screenWidth / size);
        context = this;

        LinearLayout.LayoutParams cellSize = new LinearLayout.LayoutParams(cellWidth, cellWidth);
        LinearLayout.LayoutParams rowSize = new LinearLayout.LayoutParams(cellWidth * size, cellWidth);
        LinearLayout boardL = (LinearLayout) findViewById(R.id.Board);

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
                row.addView(boardView[i][j], cellSize);
            }
            boardL.addView(row, rowSize);
        }
    }


    private void playPiece(int x, int y) {
        currentPlayer = 2;
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
