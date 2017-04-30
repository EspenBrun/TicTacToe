package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Collection;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void ok(View v){
        Intent i = new Intent("ebru.playersActivity");
        startActivity(i);
        test();

    }

    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
//        int[] win = {1,2,3};
//        int[] list = {0,1,3,0};
       ArrayList<Integer> win = new ArrayList<>();
        win.add(0);
        win.add(1);
        win.add(2);

        list.add(2);
        list.add(1);
        list.add(3);
        list.add(0);

        boolean worked = false;

        worked = list.containsAll(win);
        Log.i("trying", "it worked: " + worked);
    }
}
