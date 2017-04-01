package tdenijs.gomoku;

/**
 * Created by Tristan de Nijs on 3/31/17.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import java.util.Timer;
import android.util.DisplayMetrics;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BoardActivity extends AppCompatActivity{

    private int size;
    private int players = 1;
    private int xPos, yPos;
    private Board board;
    private ImageView[][] boardView;
    private AI ai;
    private String player2Type;
    private int[] numberOfWins = new int[3]; //0 is ties, 1 is white wins, 2 is black wins
    private Drawable[] drawing = new Drawable[3];
    private String typeString;
    private GameType typeEnum = GameType.STANDARD;
    private Context context;
    private int currentPlayer = 1;
    private boolean gameIsOver = false;
    //private Timer[] timers = new Timer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        LinearLayout boardL = (LinearLayout) findViewById(R.id.Board);
        boardL.setBackgroundColor(Color.BLACK);
        boardL.setVisibility(View.VISIBLE);
        initializeGame();
        drawBoard();
    }

    private void initializeGame() {
        Bundle extras = getIntent().getExtras();
        typeString = extras.getString("gameType");
        if(typeString.equals("FREESTYLE")) {
            typeEnum = GameType.FREESTYLE;
        }else {
            typeEnum = GameType.STANDARD;
        }
        size = extras.getInt("size");
        ai = new AI();
        board = new Board(size, typeEnum);
        boardView = new ImageView[size][size];
        context = this;
        numberOfWins[0] = 0;
        numberOfWins[1] = 0;
        numberOfWins[2] = 0;
        drawing[0] = ResourcesCompat.getDrawable(getResources(), R.drawable.game_box2, null);
        drawing[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.whitepiece, null);
        drawing[2] = ResourcesCompat.getDrawable(getResources(), R.drawable.blackpiece, null);
        player2Type = extras.getString("player2");
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
                        if(!gameIsOver) {
                            playPiece(x, y);
                        }
                    }
                });
                row.addView(boardView[i][j], cellSize);
            }
            boardL.addView(row, rowSize);
        }
    }


    private void playPiece(int x, int y) {
        if(board.board[xPos][yPos] != 0) {
            return;
        }
        boardView[xPos][yPos].setImageDrawable(drawing[currentPlayer]);
        board.playPiece(currentPlayer, xPos, yPos);
        if(board.checkWin(currentPlayer, xPos, yPos)) {
            //Player won the game!
            playerWin();
            return;
        }

        if(board.gameDraw()) {
            //The game is a draw!
            gameDraw();
            return;
        }

        if(player2Type.equals("AI")) {
            swapCurrentPlayer();
            Point p = AIplayPiece();
            boardView[p.x][p.y].setImageDrawable(drawing[currentPlayer]);
            board.playPiece(currentPlayer, p.x, p.y);
            if(board.checkWin(currentPlayer, p.x, p.y)) {
                //AI Player won the game!
                playerWin();
                return;
            }
        }

        swapCurrentPlayer();

    }

    private void playerWin() {
        numberOfWins[currentPlayer] = numberOfWins[currentPlayer] + 1;
        TextView t = (TextView) findViewById(R.id.winner);
        t.setText(getCurrentPlayerString() + " wins the game!");
        updateWins();
        gameIsOver = true;

    }

    private void gameDraw() {
        numberOfWins[0] = numberOfWins[0] + 1;
        TextView t = (TextView) findViewById(R.id.winner);
        t.setText("Game is a draw!");
        updateWins();
        gameIsOver = true;
    }

    private void swapCurrentPlayer() {
        TextView t = (TextView) findViewById(R.id.currentPlayer);
        if(currentPlayer == 1) {
            currentPlayer = 2;
            t.setText("Current player: " + getCurrentPlayerString());
        }else {
            currentPlayer = 1;
            t.setText("Current player: " + getCurrentPlayerString());
        }
    }

    private String getCurrentPlayerString() {
        if(currentPlayer == 1) {
            return "White ";
        }else {
            return "Black ";
        }
    }

    private void updateWins() {
        TextView t = (TextView) findViewById(R.id.wins);
        t.setText("White Wins: " + numberOfWins[1] +
                  " Black Wins:  " + numberOfWins[2] +
                  " Ties: " + numberOfWins[0]);
    }

    private Point AIplayPiece() {
        return ai.playPiece(board, size);
    }

    public void onResetClick(View v) {
        board.resetBoard();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                boardView[i][j].setImageDrawable(null);
            }
        }
        gameIsOver = false;
        currentPlayer = 1;
    }

}
