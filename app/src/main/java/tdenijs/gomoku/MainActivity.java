package tdenijs.gomoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int players;
    private int size;
    private String style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        players = 1;
        size = 15;
        style = "Standard";
        setContentView(R.layout.activity_main);
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
        style = "Standard";
    }

    public void onFreestyleClick(View v) {
        style = "Freestyle";
    }


}
