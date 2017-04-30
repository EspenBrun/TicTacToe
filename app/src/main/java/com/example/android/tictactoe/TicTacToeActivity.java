package com.example.android.tictactoe;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Espen on 29/04/2017.
 */
public class TicTacToeActivity extends AppCompatActivity {
    /** Constants */
    private final String TAG = "TicTacToeActivity";
    private final String X = "X";
    private final String O = "O";
    private final int NUM_WINS_COMB = 8;
    private final int REQUEST_CODE = 1;

    /** three and three indicates a possible winning combination on a 3x3 board numbered from 0-8 */
    private final ArrayList<Integer> WIN_COMB = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,0,3,6,1,4,7,2,5,8,0,4,8,2,4,6));

    /** initialize views */
    private TextView p1View;
    private TextView p2View;
    private TextView roundsView;
    private TextView winnerView;
    private Button nextRound;
    private Button b;

    /** Other inits and decls */
    private Resources res;
    private boolean playerTurn = true;
    private int[] btnIds;
    private ArrayList<Integer> p1Btns = new ArrayList<>();
    private ArrayList<Integer> p2Btns = new ArrayList<>();
    private ArrayList<Integer> winCombIds = new ArrayList<>();
    private boolean p1Won = false;
    private boolean p2Won = false;
    private String p1Name;
    private String p2Name;
    private int rounds;
    private int currentRound = 1;
    private int XWins = 0;
    private int OWins = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        initializeGame();
        Bundle extras = getIntent().getExtras();
        String lol = extras.getString("p1Name","virker ikke");
        Log.i(TAG,"Viker det? " + lol);
    }



    public void initializeGame(){
        p1View = (TextView) findViewById(R.id.p1View);
        p2View = (TextView) findViewById(R.id.p2View);
        roundsView = (TextView) findViewById(R.id.roundsView);
        winnerView = (TextView) findViewById(R.id.winnerView);
        nextRound = (Button) findViewById(R.id.nextRound);
        res = getResources();
        getBtnIds();
        getPossibleWins();
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            rounds = extras.getInt("rounds", 1);
            p1Name = extras.getString("p1Name", res.getString(R.string.p1_name_default));
            p2Name = extras.getString("p2Name", res.getString(R.string.p2_name_default));
            p1View.setText(X + ": " + p1Name);
            p2View.setText(O + ": " + p2Name);
            roundsView.setText(res.getString(R.string.rounds) + ": " + currentRound + "/" + rounds);
        }
    }

    public boolean hasWon(ArrayList pBtns){
        int i = 0;
        boolean hasWon = false;
        ArrayList<Integer> subList = new ArrayList<Integer>();
        subList.add(0);
        subList.add(0);
        subList.add(0);
        Log.i(TAG,"pBtns:" + pBtns.toString());
        while(!hasWon && i<NUM_WINS_COMB){
            subList.set(0, winCombIds.get(3*i));
            subList.set(1, winCombIds.get(3*i+1));
            subList.set(2, winCombIds.get(3*i+2));
            hasWon = pBtns.containsAll(subList);
            i++;
        }
        return hasWon;
    }

    public void getBtnIds(){
        btnIds = new int[9];
        Log.i(TAG,"length " + btnIds.length);
        for (int i = 0; i < btnIds.length; i++) {
            btnIds[i] = res.getIdentifier("b"+i, "id", getPackageName());
        }
    }

    public void clearButtons(){
        for(int id : btnIds) {
            b = (Button) findViewById(id);
            b.setText("");
        }
    }

    public void getPossibleWins(){
        for(int tile : WIN_COMB){
            winCombIds.add(btnIds[tile]);
        }
    }

    public void btnClick(View v){
        int id = v.getId();
        b = (Button) v;
        if(p1Won || p2Won) { }
        else if (spotTaken(id)) { }
        else {
            playerMove(id);
        }
        if (p1Won){
            winnerView.setText("P1 won!");
            winnerView.setVisibility(View.VISIBLE);
            nextRound.setVisibility(View.VISIBLE);
            XWins++;
        } else if(p2Won){
            winnerView.setText("P2 won!");
            winnerView.setVisibility(View.VISIBLE);
            nextRound.setVisibility(View.VISIBLE);
            OWins++;
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
            p1Won = hasWon(p1Btns);
            playerTurn = !playerTurn;
        } else {
            b.setText("O");
            b.setTextColor(res.getColor(R.color.colorPlayerTwo));
            p2Btns.add(id);
            p2Won = hasWon(p2Btns);
            playerTurn = !playerTurn;
        }
    }

    public void nextRound(View v){
        if(currentRound < rounds) {
            clearButtons();
            p1Won = false;
            p2Won = false;
            p1Btns.clear();
            p2Btns.clear();
            winnerView.setVisibility(View.GONE);
            nextRound.setVisibility(View.GONE);
            currentRound++;
            roundsView.setText(res.getString(R.string.rounds) + ": " + currentRound + "/" + rounds);
        }
    }
}
