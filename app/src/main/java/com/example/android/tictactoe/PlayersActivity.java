package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Espen on 29/04/2017.
 */
public class PlayersActivity extends AppCompatActivity {
    private final String TAG = "PlayersActivity";
    private EditText p1Field;
    private EditText p2Field;
    private EditText roundsField;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        p1Field = (EditText) findViewById(R.id.player1Field);
        p2Field = (EditText) findViewById(R.id.player2Field);
        roundsField = (EditText) findViewById(R.id.roundsField);
    }

    public void start(View v){
        String p1Name = p1Field.getText().toString().trim();
        String p2Name = p2Field.getText().toString().trim();
        String roundsInput = roundsField.getText().toString().trim();

        if(p1Name.isEmpty() || p2Name.isEmpty() || roundsInput.isEmpty() ){
            toast.makeText(PlayersActivity.this, R.string.write_names_to_play_toast, Toast.LENGTH_SHORT).show();
        } else if(Integer.valueOf(roundsInput) < 1 || p1Name.equals(p2Name)) {
            toast.makeText(PlayersActivity.this, R.string.rounds_must_be_positive_toast, Toast.LENGTH_SHORT).show();
        } else {
            int rounds = Integer.valueOf(roundsInput);
            Intent i = new Intent("ebru.ticTacToeActivity");
            Bundle extras = new Bundle();
            extras.putInt("rounds",rounds);
            extras.putString("p1Name", p1Name);
            extras.putString("p2Name", p2Name);
            i.putExtras(extras);
            startActivity(i);
            finish();
        }
    }
}
