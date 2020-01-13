package com.cops.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    // 1 for X and 0 for O
    int Player=0;
    //gameState the field that did't filled
    int[]gameState={2,2,2,2,2,2,2,2,2};
    //this 's a winning positions
    int[][]winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsOActive=true;

//onTapped it 's checking if the field didn't played and if the game is active and thin modify it
    public void dropIn(View v){
        ImageView counter=(ImageView) v;
        int tapped=Integer.parseInt(counter.getTag().toString());
        if(gameState[tapped]==2&&gameIsOActive) {
            gameState[tapped]=Player;

            if(Player==0){
            Player=1;
            counter.setImageResource(R.drawable.o);
         }else{
            counter.setImageResource(R.drawable.x);
            Player=0;
        }

        for(int []winp:winPos){
            if(gameState[winp[0]]==gameState[winp[1]]&&
               gameState[winp[1]]==gameState[winp[2]]&&
                gameState[winp[0]]!=2){
                gameIsOActive=false;
                String win="X";
                if(gameState[winp[0]]==0)win="O";

                TextView winner=(TextView)findViewById(R.id.winner);
                winner.setText(win +" has won!");

                LinearLayout lay=(LinearLayout)findViewById(R.id.lay);
                lay.setVisibility(View.VISIBLE);


            }else {
                 boolean gameOver=true;
                for(int count:gameState)
                    if(count==2)gameOver=false;
                if(gameOver){
                    TextView message=(TextView)findViewById(R.id.winner);
                    message.setText("It's a draw!");

                    LinearLayout lay=(LinearLayout)findViewById(R.id.lay);
                    lay.setVisibility(View.VISIBLE);
                }

            }
        }}

    }
    public void playAgain(View v){

        gameIsOActive=true;
        LinearLayout lay=(LinearLayout)findViewById(R.id.lay);
        lay.setVisibility(View.INVISIBLE);
        Player=0;
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;
       // GridLayout grid=(GridLayout)findViewById(R.id.gridLay);
        androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridLay);


        //grid.setVisibility(View.VISIBLE);
        for(int i=0;i<grid.getChildCount();i++)
        ((ImageView)grid.getChildAt(i)).setImageResource(0);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
