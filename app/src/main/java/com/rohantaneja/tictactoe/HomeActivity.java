package com.rohantaneja.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    static int BUTTON_PLAYER_TWO_ID = 2;
    static int BUTTON_PADDING = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        RelativeLayout parentRelativeLayout = new RelativeLayout(this);
        parentRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(parentRelativeLayout, parentRelativeLayout.getLayoutParams());

        //AppName TextView
        TextView appNameTextView = new TextView(this);
        RelativeLayout.LayoutParams appNameTextViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appNameTextViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        appNameTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        appNameTextView.setText(getString(R.string.app_name));
        appNameTextView.setTextColor(Color.BLACK);
        appNameTextView.setTextSize(36f);
        appNameTextView.setTypeface(Typeface.SERIF);
        appNameTextViewParams.setMargins(8, 48, 8, 16);
        appNameTextView.setLayoutParams(appNameTextViewParams);
        parentRelativeLayout.addView(appNameTextView);

        //vs Player2 Button
        Button buttonPlayerTwo = new Button(this);
        buttonPlayerTwo.setId(BUTTON_PLAYER_TWO_ID);
        RelativeLayout.LayoutParams buttonPlayerTwoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonPlayerTwo.setLayoutParams(buttonPlayerTwoParams);
        buttonPlayerTwo.setText("vs Player 2");
        buttonPlayerTwo.setTypeface(Typeface.SERIF);
        buttonPlayerTwo.setAllCaps(false);
        buttonPlayerTwo.setPadding(BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING);
        buttonPlayerTwo.setTextSize(22f);
        buttonPlayerTwoParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        buttonPlayerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, TwoPlayerActivity.class);
                startActivity(i);
            }
        });

        parentRelativeLayout.addView(buttonPlayerTwo);


        //vs Computer Button
        Button buttonComputer = new Button(this);
        RelativeLayout.LayoutParams buttonComputerParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonComputer.setLayoutParams(buttonComputerParams);
        buttonComputer.setTypeface(Typeface.SERIF);
        buttonComputer.setText("vs Computer");
        buttonComputer.setAllCaps(false);
        buttonComputer.setPadding(BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING);
        buttonComputer.setTextSize(22f);
        buttonComputerParams.addRule(RelativeLayout.BELOW, buttonPlayerTwo.getId());
        buttonComputerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        buttonComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ComputerActivity.class);
                startActivity(i);
            }
        });

        parentRelativeLayout.addView(buttonComputer);
    }
}
