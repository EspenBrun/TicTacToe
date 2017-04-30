package com.example.android.tictactoe;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
            if(XWins > OWins){
                winnerViewSummary.setText(getString(R.string.name_won, p1Name));
                looserView.setText(getString(R.string.name_lost, p2Name));
                summaryView.setText(getString(R.string.won_rounds_out_of, p1Name, XWins, rounds));
            } else if (OWins > XWins) {
                winnerViewSummary.setText(getString(R.string.name_won, p2Name));
                looserView.setText(getString(R.string.name_lost, p1Name));
                summaryView.setText(getString(R.string.won_rounds_out_of, p2Name, OWins, rounds));
            } else {
                winnerViewSummary.setText(R.string.draw);
                looserView.setVisibility(View.GONE);
                summaryView.setText(getString(R.string.both_won_rounds, p1Name, p2Name, rounds/2, rounds));
            }
            movesList();
        }
    }

    public void movesList(){
        ArrayList<String> allTheMoves = new ArrayList<>();
        int currentRound;
        for (int i = 0; i < movesList.size(); i++) {
            currentRound = i + 1;
            allTheMoves.add(getString(R.string.round_moves, currentRound, movesList.get(i)));
        }
        ArrayAdapter<String> a = new ArrayAdapter<>(SummaryActivity.this, android.R.layout.simple_list_item_1, allTheMoves);
        movesListView.setAdapter(a);
    }

    public void newGame(View v){
        newGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ingame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.new_game){
            newGame();
        } else if (id == R.id.menu_exit){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Exit me", true);
            startActivity(intent);
            finish();
        }
        return true;
    }

    public void newGame(){
        Intent i = new Intent("ebru.playersActivity");
        startActivity(i);
        finish();
    }
}
