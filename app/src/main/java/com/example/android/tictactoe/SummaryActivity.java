package com.example.android.tictactoe;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Espen on 30/04/2017.
 */
public class SummaryActivity extends AppCompatActivity {
    private String p1Name;
    private String p2Name;
    private int rounds;
    private int XWins;
    private int OWins;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        res = getResources();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            p1Name = extras.getString("p1Name", res.getString(R.string.p1_name_default));
            p2Name = extras.getString("p2Name", res.getString(R.string.p2_name_default));
            rounds = extras.getInt("rounds", -1);
            XWins = extras.getInt("XWins", -1);
            OWins = extras.getInt("OWins", -1);
            Log.i("lolging", p1Name + p2Name + rounds + XWins + OWins);
        }
    }

}
