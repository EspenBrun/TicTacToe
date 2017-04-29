package com.example.android.tictactoe;

import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
    private int[] btnIds;
    private ArrayList<Integer> p1Btns = new ArrayList<>();
    private ArrayList<Integer> p2Btns = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        res = getResources();
        getBtnIds();

    }

    public void getBtnIds(){
        btnIds = new int[9];
        Log.i(TAG,"length " + btnIds.length);
        for (int i = 0; i < btnIds.length; i++) {
            btnIds[i] = res.getIdentifier("b"+i, "id", getPackageName());
            Log.i(TAG,"id: " + btnIds[i]);
        }
    }
    public void btnClick(View v){
        int id = v.getId();
        b = (Button) v;
        if(spotTaken(id)){

        } else {
            playerMove(id);
        }
    }

    public boolean spotTaken(int id){
        return p1Btns.contains(id) || p2Btns.contains(id);
    }

    public void playerMove(int id){
        if(playerTurn){
            b.setText("X");
            b.setTextColor(res.getColor(R.color.colorPlayerOne));
            p1Btns.add(id);
            playerTurn = !playerTurn;
        } else {
            b.setText("O");
            b.setTextColor(res.getColor(R.color.colorPlayerTwo));
            p2Btns.add(id);
            playerTurn = !playerTurn;
        }
    }
}
