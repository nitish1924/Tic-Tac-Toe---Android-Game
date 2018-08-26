package com.example.nitis.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0:red 1:yellow
    boolean activeGame = true;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int winningPositions[][] ={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int totalMoves=0;
    public void change(View view){
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());
        if(gameState[tag]==2 && activeGame){
            counter.setTranslationY(-1500);
            gameState[tag] = activePlayer;
            if(activePlayer == 0){
                activePlayer = 1;
                counter.setImageResource(R.drawable.red);
                totalMoves++;
            }
            else{
                activePlayer = 0;
                counter.setImageResource(R.drawable.yellow);
                totalMoves++;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    // Somone has won!
                    activeGame = false;
                    android.support.v7.widget.GridLayout grid = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
                    if (activePlayer == 1) {
                        ImageView redWon = findViewById(R.id.redWon);
                        grid.animate().alpha(0).setDuration(1000);
                        redWon.animate().alpha(1).setDuration(1000);
                    }
                    else{
                        ImageView yellowWon = findViewById(R.id.yellowWon);
                        grid.animate().alpha(0).setDuration(1000);
                        yellowWon.animate().alpha(1).setDuration(1000);
                    }
                }
                else if(totalMoves==9){
                    ImageView draw = findViewById(R.id.draw);
                    android.support.v7.widget.GridLayout grid = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
                    grid.animate().alpha(0).setDuration(1000);
                    draw.animate().alpha(1).setDuration(1000);
                }
            }
        }
    }
    public void replay(View view){
        //Toast.makeText(this, "play again", Toast.LENGTH_SHORT).show();
        ImageView draw = findViewById(R.id.draw);
        draw.animate().alpha(0).setDuration(1000);
        ImageView redWon = findViewById(R.id.redWon);
        redWon.animate().alpha(0).setDuration(1000);
        ImageView yellowWon = findViewById(R.id.yellowWon);
        yellowWon.animate().alpha(0).setDuration(1000);
       activeGame =true;
       activePlayer=0;
       totalMoves=0;
       for(int i=0;i<gameState.length;i++){
          gameState[i]=2;
       }
       android.support.v7.widget.GridLayout grid = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
        grid.animate().alpha(1).setDuration(1000);

       for (int i = 0; i < grid.getChildCount(); i++) {
           ImageView counter = (ImageView) grid.getChildAt(i);
           counter.setImageDrawable(null);
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
