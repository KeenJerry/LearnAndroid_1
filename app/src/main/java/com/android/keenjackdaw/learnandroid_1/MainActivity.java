package com.android.keenjackdaw.learnandroid_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button mTrueButton;
    Button mFalseButton;
    Button mNextButton;
    TextView mQuestionTextView;
    int currentIndex = 0;
    Question[] questions;
    Button mPrevButton;
    Button mCheatButton;

    static final String KEYINDEX = "currentIndex";
    static final String TAG = "MainActivity";

    @Override
    // Bundle: A mapping from String values to various Parcelable types, used to transfer info from act to act
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Method onCreate(Bundle) is called.");
        setContentView(R.layout.activity_main);

        // receive data
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEYINDEX, 0);
        }

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mPrevButton = findViewById(R.id.prev_button);
        mCheatButton = findViewById(R.id.cheat_button);

        questions = new Question[]{
                new Question(R.string.question_aus, true, false),
                new Question(R.string.question_oceans, true, false),
                new Question(R.string.question_mideast, false, false)
        };

        updateQuestion(currentIndex);

        // Listeners
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                questions[currentIndex].setmAnswered(true);
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                questions[currentIndex].setmAnswered(true);
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex++;
                updateQuestion(Math.abs((currentIndex) % questions.length));
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex--;
                updateQuestion(Math.abs((currentIndex) % questions.length));

            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex++;
                updateQuestion(Math.abs((currentIndex) % questions.length));
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // start another act using intent, intent is a midware to connect to the android os
                boolean answer = questions[currentIndex].ismAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answer);
                startActivity(intent);
            }
        });

    }

    protected void updateQuestion(int currentIndex){
        mQuestionTextView.setText(questions[currentIndex].getmTextResId());
        if(questions[currentIndex].ismAnswered()){
            Log.d(TAG, "Question number " + currentIndex + " is answered.");
            disableChooseButton(mTrueButton);
            disableChooseButton(mFalseButton);
        }
        else{
            enableChooseButton(mTrueButton);
            enableChooseButton(mFalseButton);
        }
    }

    protected void disableChooseButton(Button button){
        button.setEnabled(false);
    }
    protected void enableChooseButton(Button button){
        button.setEnabled(true);
    }

    protected void checkAnswer(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].ismAnswerTrue();
        int messageId = 0;
        if(userAnswer == correctAnswer){
            messageId = R.string.correct_toast;
        }
        else{
            messageId = R.string.incorrect_toast;
        }
        Toast toast = Toast.makeText(MainActivity.this, messageId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0, 300);
        toast.show();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Method onStart() is called.");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Method onResume() is called.");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Method onPause() is called.");
    }

    @Override
    // Invoked before OnStop() to Save data
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEYINDEX, currentIndex);
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Method onStop() is called.");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Method onDestroy() is called.");
    }
}
