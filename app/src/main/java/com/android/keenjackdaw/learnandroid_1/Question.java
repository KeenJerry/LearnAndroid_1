package com.android.keenjackdaw.learnandroid_1;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mAnswered;

    public Question(int textResId, boolean answerTrue, boolean answered){
        mAnswerTrue = answerTrue;
        mTextResId = textResId;
        mAnswered = answered;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public void setmAnswered(boolean mAnswered){
        this.mAnswered = mAnswered;
    }

    public boolean ismAnswered() {
        return mAnswered;
    }
}
