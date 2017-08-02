package com.rohantaneja.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerActivity extends AppCompatActivity {

    Button[] buttonArray;
    static int COMPUTER_TURN_DELAY = 500;
    TextView gameResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);

        gameResultTextView = (TextView) findViewById(R.id.game_result_text_view);
        buttonArray = new Button[9];

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

    boolean isPlayerTurn = true; //X for Player 1; O for Computer;

    List<Integer> player1Boxes = new ArrayList<>();
    List<Integer> computerBoxes = new ArrayList<>();
    List<Integer> totalAvailableButtons = new ArrayList<>();
    List<Integer> availableButtonsForComputer = new ArrayList<>();

    public void performMove(int selectedButtonID, Button selectedButton) {

        selectedButton.setEnabled(false);

        if (isPlayerTurn) {
            totalAvailableButtons.remove(Integer.valueOf(selectedButtonID));
            player1Boxes.add(selectedButtonID);
            selectedButton.setBackgroundColor(Color.parseColor("#27ae60"));
            selectedButton.setText("X");
        } else {
            computerBoxes.add(selectedButtonID);
            selectedButton.setBackgroundColor(Color.parseColor("#16a085"));
            selectedButton.setText("O");
        }

        checkResult();
    }

    private void checkResult() {

        List<Integer> currentPlayerBoxes = new ArrayList<>();

        if (isPlayerTurn) {
            currentPlayerBoxes.addAll(player1Boxes);
        } else {
            currentPlayerBoxes.addAll(computerBoxes);
        }

        //rows
        for (int i = 1; i <= 7; i += 3) {
            if (currentPlayerBoxes.contains(i) && currentPlayerBoxes.contains(i + 1) && currentPlayerBoxes.contains(i + 2)) {
                if (isPlayerTurn) {
                    gameResultTextView.setText(getString(R.string.player_1_wins));
                } else {
                    gameResultTextView.setText(getString(R.string.computer_wins));
                }
                disableButtonClicks();
                return;
            }
        }

        //columns
        for (int i = 1; i <= 3; i++) {
            if (currentPlayerBoxes.contains(i) && currentPlayerBoxes.contains(i + 3) && currentPlayerBoxes.contains(i + 6)) {
                if (isPlayerTurn) {
                    gameResultTextView.setText(getString(R.string.player_1_wins));
                } else {
                    gameResultTextView.setText(getString(R.string.computer_wins));
                }
                disableButtonClicks();
                return;
            }
        }

        //diagonals
        boolean leftDiagonal = (currentPlayerBoxes.contains(1) && currentPlayerBoxes.contains(5) && currentPlayerBoxes.contains(9));
        boolean rightDiagonal = (currentPlayerBoxes.contains(3) && currentPlayerBoxes.contains(5) && currentPlayerBoxes.contains(7));

        if (leftDiagonal || rightDiagonal) {
            if (isPlayerTurn) {
                gameResultTextView.setText(getString(R.string.player_1_wins));
            } else {
                gameResultTextView.setText(getString(R.string.computer_wins));
            }
            disableButtonClicks();
            return;
        }

        if (player1Boxes.size() + computerBoxes.size() == 9) {
            gameResultTextView.setText(getString(R.string.draw_result));
            disableButtonClicks();
        }

        if (isPlayerTurn) {
            isPlayerTurn = false;
            computerTurn();
        } else {
            isPlayerTurn = true;
            enableTotalAvailableButtons();
        }

    }

    public void resetGame(View v) {
        resetGame();
    }

    public void resetGame() {

        resetTotalAvaialableButtons();
        gameResultTextView.setText(getString(R.string.app_name));

        isPlayerTurn = true;

        player1Boxes.clear();
        computerBoxes.clear();
        availableButtonsForComputer.clear();

        for (Button button : buttonArray) {
            button.setText("");
            button.setBackgroundResource(R.drawable.button_custom);
        }

        enableButtonClicks();
    }

    public void computerTurn() {

        disableTotalAvailableButtons();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                performComputerTurn();
            }
        }, COMPUTER_TURN_DELAY);
    }

    public void performComputerTurn() {

        availableButtonsForComputer.clear();

        for (int i = 1; i <= 9; i++) {
            if (!(player1Boxes.contains(i) || computerBoxes.contains(i))) {
                availableButtonsForComputer.add(i);
            }
        }

        Random random = new Random();
        int availableBoxesArrayIndexSelectedByComputer = random.nextInt(availableButtonsForComputer.size() + 0) - 0;
        int selectedButtonID = availableButtonsForComputer.get(availableBoxesArrayIndexSelectedByComputer);
        enableTotalAvailableButtons();
        totalAvailableButtons.remove(Integer.valueOf(selectedButtonID));
        disableTotalAvailableButtons();
        Button selectedButton = buttonArray[selectedButtonID - 1];

        performMove(selectedButtonID, selectedButton);
    }

    public void enableButtonClicks() {
        for (Button button : buttonArray)
            button.setEnabled(true);
    }

    public void disableButtonClicks() {
        for (Button button : buttonArray)
            button.setEnabled(false);
    }

    public void resetTotalAvaialableButtons() {
        for (int i = 1; i <= 9; i++)
            totalAvailableButtons.add(i);
    }

    public void disableTotalAvailableButtons() {
        for (int i : totalAvailableButtons) {
            buttonArray[i - 1].setEnabled(false);
        }
    }

    public void enableTotalAvailableButtons() {
        for (int i : totalAvailableButtons) {
            buttonArray[i - 1].setEnabled(true);
        }
    }
}
