package com.example.android.tictactoe;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Espen on 30/04/2017.
 */
public class SummaryActivity extends AppCompatActivity {
    private final String TAG = "SummaryActivity";
    private TextView winnerViewSummary;
    private TextView looserView;
    private TextView summaryView;
    private ListView movesListView;
    private String p1Name;
    private String p2Name;
    private int rounds;
    private int XWins;
    private int OWins;
    private ArrayList<Integer> movesList = new ArrayList<>();
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        res = getResources();
        winnerViewSummary = (TextView) findViewById(R.id.winnerViewSummary);
        looserView = (TextView) findViewById(R.id.looserView);
        summaryView = (TextView) findViewById(R.id.summaryView);
        movesListView = (ListView) findViewById(R.id.movesListView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            p1Name = extras.getString("p1Name", res.getString(R.string.p1_name_default));
            p2Name = extras.getString("p2Name", res.getString(R.string.p2_name_default));
            rounds = extras.getInt("rounds", -1);
            XWins = extras.getInt("XWins", -1);
            OWins = extras.getInt("OWins", -1);
            movesList = extras.getIntegerArrayList("movesList");
            Log.i(TAG,"movesList: " + movesList.toString());
            if(XWins > OWins){
                winnerViewSummary.setText(p1Name + getString(R.string.won));
                looserView.setText(p2Name + getString(R.string.lost));
                summaryView.setText(p1Name + " won " + XWins + " out of " + rounds + " matches.");
            } else if (OWins > XWins) {
                winnerViewSummary.setText(p2Name + getString(R.string.won));
                looserView.setText(p1Name + getString(R.string.lost));
                summaryView.setText(p2Name + " won " + OWins + " out of " + rounds + " matches.");
            } else {
                winnerViewSummary.setText(R.string.draw);
                looserView.setVisibility(View.GONE);
                summaryView.setText("Both " + p1Name + " and " + p2Name + " won "  + rounds/2 + " out of " + rounds + " matches");
            }
            movesList();
        }
    }

    public void movesList(){
        ArrayList<String> allTheMoves = new ArrayList<>();
        for (int i = 0; i < movesList.size(); i++) {
            allTheMoves.add("Round " + i + ": " + movesList.get(i) + " moves");
        }
        ArrayAdapter<String> a = new ArrayAdapter<>(SummaryActivity.this, android.R.layout.simple_list_item_1, allTheMoves);
        movesListView.setAdapter(a);
    }

    public void newGame(View v){
        finish();
    }

}
