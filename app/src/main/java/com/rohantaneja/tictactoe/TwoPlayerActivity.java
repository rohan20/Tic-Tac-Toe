package com.rohantaneja.tictactoe;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoPlayerActivity extends AppCompatActivity {

    Button[] buttonArray;
    TextView gameResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        buttonArray = new Button[9];
        gameResultTextView = (TextView) findViewById(R.id.game_result_text_view);

        for (int i = 1; i <= 9; i++) {
            int id = getResources().getIdentifier("button_" + i, "id", getPackageName());
            buttonArray[i - 1] = (Button) findViewById(id);
        }

        resetGame();
    }

    public void buttonClicked(View view) {

        Button selectedButton = (Button) view;

        int selectedButtonID = 0;

        switch (view.getId()) {
            case R.id.button_1:
                selectedButtonID = 1;
                break;

            case R.id.button_2:
                selectedButtonID = 2;
                break;

            case R.id.button_3:
                selectedButtonID = 3;
                break;

            case R.id.button_4:
                selectedButtonID = 4;
                break;

            case R.id.button_5:
                selectedButtonID = 5;
                break;

            case R.id.button_6:
                selectedButtonID = 6;
                break;

            case R.id.button_7:
                selectedButtonID = 7;
                break;

            case R.id.button_8:
                selectedButtonID = 8;
                break;

            case R.id.button_9:
                selectedButtonID = 9;
                break;

        }
        performMove(selectedButtonID, selectedButton);
    }

    boolean isPlayer1ActivePlayer = true; //X for Player 1; O for Player 2;
    List<Integer> player1Boxes = new ArrayList<>();
    List<Integer> player2Boxes = new ArrayList<>();

    public void performMove(int selectedButtonID, Button selectedButton) {
        selectedButton.setEnabled(false);

        if (isPlayer1ActivePlayer) {
            player1Boxes.add(selectedButtonID);
            selectedButton.setBackgroundColor(Color.parseColor("#27ae60"));
            selectedButton.setText("X");
        } else {
            player2Boxes.add(selectedButtonID);
            selectedButton.setBackgroundColor(Color.parseColor("#16a085"));
            selectedButton.setText("O");
        }

        checkResult();
    }

    private void checkResult() {

        List<Integer> currentPlayerBoxes = new ArrayList<>();

        if (isPlayer1ActivePlayer) {
            currentPlayerBoxes.addAll(player1Boxes);
        } else {
            currentPlayerBoxes.addAll(player2Boxes);
        }

        //rows
        for (int i = 1; i <= 7; i += 3) {
            if (currentPlayerBoxes.contains(i) && currentPlayerBoxes.contains(i + 1) && currentPlayerBoxes.contains(i + 2)) {
                if (isPlayer1ActivePlayer) {
                    gameResultTextView.setText(getString(R.string.player_1_wins));
                } else {
                    gameResultTextView.setText(getString(R.string.player_2_wins));
                }
                disableButtonClicks();
                return;
            }
        }

        //columns
        for (int i = 1; i <= 3; i++) {
            if (currentPlayerBoxes.contains(i) && currentPlayerBoxes.contains(i + 3) && currentPlayerBoxes.contains(i + 6)) {
                if (isPlayer1ActivePlayer) {
                    gameResultTextView.setText(getString(R.string.player_1_wins));
                } else {
                    gameResultTextView.setText(getString(R.string.player_2_wins));
                }
                disableButtonClicks();
                return;
            }
        }

        //diagonals
        boolean leftDiagonal = (currentPlayerBoxes.contains(1) && currentPlayerBoxes.contains(5) && currentPlayerBoxes.contains(9));
        boolean rightDiagonal = (currentPlayerBoxes.contains(3) && currentPlayerBoxes.contains(5) && currentPlayerBoxes.contains(7));

        if (leftDiagonal || rightDiagonal) {
            if (isPlayer1ActivePlayer) {
                gameResultTextView.setText(getString(R.string.player_1_wins));
            } else {
                gameResultTextView.setText(getString(R.string.player_2_wins));
            }
            disableButtonClicks();
            return;
        }

        if (player1Boxes.size() + player2Boxes.size() == 9) {
            gameResultTextView.setText(getString(R.string.draw_result));
            disableButtonClicks();
        }

        isPlayer1ActivePlayer = !isPlayer1ActivePlayer;

    }

    public void resetGame(View v) {
        resetGame();
    }

    public void resetGame() {
        isPlayer1ActivePlayer = true;

        gameResultTextView.setText(getString(R.string.app_name));

        player1Boxes.clear();
        player2Boxes.clear();

        for (Button button : buttonArray) {
            button.setText("");
            button.setBackgroundResource(R.drawable.button_custom);
        }

        enableButtonClicks();
    }

    public void enableButtonClicks() {
        for (Button button : buttonArray)
            button.setEnabled(true);
    }

    public void disableButtonClicks() {
        for (Button button : buttonArray)
            button.setEnabled(false);
    }
}
