package tdenijs.gomoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int players;
    private int size;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        players = 1;
        size = 10;
        type = "STANDARD";
        setContentView(R.layout.activity_main);
    }

    public void onPlayClick(View v) {
        Intent intent = new Intent(MainActivity.this, BoardActivity.class);
        Bundle boardVariables = new Bundle();
        boardVariables.putInt("size", size);
        boardVariables.putString("gameType", type);
        switch(players) {
            case 1:
                boardVariables.putString("player2", "AI");
                break;
            case 2:
                boardVariables.putString("player2", "human");
                break;
        }
        intent.putExtras(boardVariables);
        startActivity(intent);
    }



    public void on1PlayerClick(View v) {
        players = 1;
    }

    public void on2PlayerClick(View v) {
        players = 2;
    }

    public void on10XClick(View v) {
        size = 10;
    }

    public void on15XClick(View v){
        size = 15;
    }

    public void on20XClick(View v){
        size = 20;
    }

    public void onStandardClick(View v) {
        type = "STANDARD";
    }

    public void onFreestyleClick(View v) {
        type = "FREESTYLE";
    }


}
