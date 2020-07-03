package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[]{

            new Question(R.string.my_test_question_1,true),
            new Question(R.string.my_test_question_2,false),
            new Question(R.string.my_test_question_3,true),
            new Question(R.string.my_test_question_4,true),
            new Question(R.string.my_test_question_5,true),
            new Question(R.string.my_test_question_6,false),
            new Question(R.string.my_test_question_7,false),
            new Question(R.string.my_test_question_8,false),
            new Question(R.string.my_test_question_9,true)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question question = new Question(R.id.answer_text_view, true);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.answer_text_view);
        prevButton = findViewById(R.id.prev_button);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:
                //go to next question
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length; // we are safe now!
                updateQuestion();
                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    updateQuestion();
                }


        }
    }

    private void updateQuestion() {
        Log.d("Current", "onClick: " + currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }
    private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId = 0;
        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_SHORT)
                .show();

    }


}