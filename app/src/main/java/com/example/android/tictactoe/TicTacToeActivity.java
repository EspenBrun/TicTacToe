package com.example.android.tictactoe;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Espen on 29/04/2017.
 */
public class TicTacToeActivity extends AppCompatActivity {
    private final String TAG = "TicTacToeActivity";
    private Button b;
    private Resources res;
    private boolean playerTurn = true;
    private final String X = "X";
    private final String O = "O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        res = getResources();
    }

    public void btnClick(View v){
        int id = v.getId();
        b = (Button) v;
        if(playerTurn){
            b.setText("X");
            b.setTextColor(res.getColor(R.color.colorPlayerOne));
            playerTurn = !playerTurn;
        } else {
            b.setText("O");
            b.setTextColor(res.getColor(R.color.colorPlayerTwo));
            playerTurn = !playerTurn;
        }

    }
}
